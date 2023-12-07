package tetris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import blocks.Block;
import blocks.BlockFactory;

public class Game extends Thread implements GameSetting {

	private List<List<GridElement>> grid = (List<List<GridElement>>) Collections.synchronizedList(new ArrayList<List<GridElement>>(WIDTH_BLOCK_NUM));
	{
		for(int i = 0; i<WIDTH_BLOCK_NUM;i++) {
			grid.add((List<GridElement>)(Collections.synchronizedList(new ArrayList<GridElement>(WIDTH_BLOCK_NUM))));
			for(int j = 0; j <HEIGHT_BLOCK_NUM  ; j++) {
				grid.get(i).add(new GridElement());
			}
		}
	}
	//private boolean[][] grid = new boolean[WIDTH_BLOCK_NUM][HEIGTH_BLOCK_NUM];
	private Block curBlock;
	private boolean isGameover = false;

	private Object blockKey = new Object();
	private ExecutorService executor = Executors.newFixedThreadPool(4);

	@Override
	public void run() {
		/*executor.submit(new NewBlock(this));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		executor.submit(new BlockDrop(this));
	}

	public Block getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(Block curBlock) {
		this.curBlock = curBlock;
	}

	public Object getBlockKey() {
		return blockKey;
	}

	public boolean getIsGameover() {
		return isGameover;
	}
	public void fixBlock(Block block) {
		int x = block.getX();
		int y = block.getY();
		boolean[][] area = block.getArea();
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				if(!area[i][j]) continue;
				//grid[x+i][y+j]=true;
				grid.get(x+i).get(y+j).occupy(block);
			}
		}
	}
	public List<List<GridElement>> getGrid() {
		return grid;
	}
	
	
	
	public void breakLine() {
		for(int i = 0; i < HEIGHT_BLOCK_NUM; i++) {
			boolean isLineFull = true;
			for(int j = 0; j < WIDTH_BLOCK_NUM; j++) {
				if(!grid.get(j).get(i).isOccupied()) {
					isLineFull = false;
					break;
				}
			}
			if(isLineFull) {
				for(int j = 0; j < WIDTH_BLOCK_NUM; j++) {
					grid.get(j).get(i).destroy();
				}
				for(int k = i; k >= 1; k--) {
					for(int j = 0; j < WIDTH_BLOCK_NUM; j++) {
						grid.get(j).get(k).copy(grid.get(j).get(k-1)); //clone을 해야함.
					}
				}
			}
		}
		
	}
}

class BlockDrop implements Runnable, GameSetting {
	private Game game;
	private BlockFactory blockFactory = BlockFactory.getBlockFactory();
	
	BlockDrop(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		game.setCurBlock(blockFactory.newBlock(game.getGrid()));
		Object key = game.getBlockKey();
		while (true) {
			Block curBlock = game.getCurBlock();
			if (curBlock.moveDown()) {
				game.fixBlock(curBlock);
				game.breakLine();
				//synchronized (key) {
				//	System.out.println("notify");
				//	key.notify();
				//}
				game.setCurBlock(blockFactory.newBlock(game.getGrid()));
			}
			try {
				Thread.sleep(MILLISECOND_PER_FRAME*5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	
	
}


/*
class NewBlock implements Runnable {
	private Game game;
	private BlockFactory blockFactory = BlockFactory.getBlockFactory();

	NewBlock(Game game) {
		this.game = game;
	}

	public void run() {
		Object key = game.getBlockKey();
		while (!game.getIsGameover()) {
			synchronized (key) {
				game.setCurBlock(blockFactory.newBlock(game.getGrid()));
				try {
					System.out.println("wait");
					key.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
*/
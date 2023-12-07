package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JPanel;

import blocks.Block;

class BlockMoveKeyListener implements KeyListener{
	private Game game;
	public BlockMoveKeyListener(Game game) {
		this.game = game;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Block curBlock = game.getCurBlock();
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			curBlock.moveRight();
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			curBlock.moveLeft();
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			curBlock.rotate();
			System.out.println("rotate");
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(!game.isBlockCollided(curBlock))
			curBlock.moveDown();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}

public class GamePanel extends JPanel implements GameSetting {
	private Game game = new Game();
	private int blockEdge; 
	GamePanel() {
		super();
		setBackground(Color.white);
		setLayout(new GridLayout(20,10));
		setVisible(true);
		setFocusable(true);
		requestFocus();
		addKeyListener(new BlockMoveKeyListener(game));
		
		game.start();
		
		new Thread(()->{
			while(true) {
				try {
					repaint();
					Thread.sleep(MILLISECOND_PER_FRAME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.clearRect(0, 0, getWidth(), getHeight());
		blockEdge=getWidth()/WIDTH_BLOCK_NUM;
		drawBlock(g, game.getCurBlock());
		drawGrid(g, game.getGrid());
	}
	
	private void drawGrid(Graphics g, List<List<Boolean>> grid) {
		g.setColor(Color.RED);
		for(int i = 0; i < grid.size(); i++) {
			for(int j = 0; j < grid.get(i).size(); j++) {
				if(!grid.get(i).get(j)) continue;
				int rectX = i*blockEdge;
				int rectY = j*blockEdge;
				g.fillRect(rectX, rectY, blockEdge, blockEdge);
			}
		}
	}


	private void drawBlock(Graphics g,Block block) {
		g.setColor(Color.CYAN);
		
		int x = block.getX();
		int y = block.getY();
		
		boolean[][] area = block.getArea();
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				if(!area[i][j]) continue;
				int rectX = (x+i)*blockEdge;
				int rectY = (y+j)*blockEdge;
				g.fillRect(rectX, rectY, blockEdge, blockEdge);
			}
		}
	}
}
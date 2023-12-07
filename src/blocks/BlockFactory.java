package blocks;

import java.util.List;

import tetris.GridElement;

public class BlockFactory {
	private static BlockFactory blockFactory = new BlockFactory();
	private BlockFactory(){}
	public static BlockFactory getBlockFactory() {return blockFactory;}
	
	
	public Block newBlock(List<List<GridElement>> grid) {
		int blockKind = (int)(Math.random()*7)+1;
		Block block = null;
		switch(blockKind){
		case 1: block = new Block1(grid); break;
		case 2: block = new Block2(grid); break;
		case 3: block = new Block3(grid); break;
		case 4: block = new Block4(grid); break;
		case 5: block = new Block5(grid); break;
		case 6: block = new Block6(grid); break;
		case 7: block = new Block7(grid); break;
		}
		return block;
	}
	
}

package blocks;

import java.util.List;

public class BlockFactory {
	private static BlockFactory blockFactory = new BlockFactory();
	private BlockFactory(){}
	public static BlockFactory getBlockFactory() {return blockFactory;}
	
	
	public Block newBlock(List<List<Boolean>> grid) {
		int blockKind = (int)(Math.random()*3)+1;
		Block block = null;
		switch(blockKind){
		case 1: block = new Block1(grid); break;
		case 2: block = new Block2(grid); break;
		case 3: block = new Block3(grid); break;
		}
		return block;
	}
	
}

package blocks;

import java.util.List;

public class Block2 extends Block{
	public Block2(List<List<Boolean>> grid) {
		super(grid);
		area = new boolean[][]{{true,true},{true,true}};
	}
}

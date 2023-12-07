package blocks;

import java.util.List;

public class Block1 extends Block{
	public Block1(List<List<Boolean>> grid) {
		super(grid);
		area = new boolean[][]{{true},{true},{true},{true}};
	}
}

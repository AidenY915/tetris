package blocks;

import java.util.List;

public class Block3 extends Block{
	public Block3(List<List<Boolean>> grid) {
		super(grid);
		area = new boolean[][]{{true,true},{true,false},{true,false}};
	}
}

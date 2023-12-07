package blocks;

import java.awt.Color;
import java.util.List;

import tetris.GridElement;

public class Block3 extends Block{
	public Block3(List<List<GridElement>> grid) {
		super(grid);
		area = new boolean[][]{{true,true},{true,false},{true,false}};
		color = Color.green;
	}
}

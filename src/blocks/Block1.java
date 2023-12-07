package blocks;

import java.awt.Color;
import java.util.List;

import tetris.GridElement;

public class Block1 extends Block{
	public Block1(List<List<GridElement>> grid) {
		super(grid);
		area = new boolean[][]{{true},{true},{true},{true}};
		color = Color.BLUE;
	}
}

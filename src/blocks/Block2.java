package blocks;

import java.awt.Color;
import java.util.List;

import tetris.GridElement;

public class Block2 extends Block{
	public Block2(List<List<GridElement>> grid) {
		super(grid);
		area = new boolean[][]{{true,true},{true,true}};
		color = Color.DARK_GRAY;
	}
}

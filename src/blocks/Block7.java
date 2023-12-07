package blocks;

import java.awt.Color;
import java.util.List;

import tetris.GridElement;

public class Block7 extends Block {

	Block7(List<List<GridElement>> grid) {
		super(grid);
		area = new boolean[][]{{false,true},{true,true},{false,true}};
		color = Color.RED;
	}

}

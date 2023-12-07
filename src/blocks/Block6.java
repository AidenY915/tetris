package blocks;

import java.awt.Color;
import java.util.List;

import tetris.GridElement;

public class Block6 extends Block {

	Block6(List<List<GridElement>> grid) {
		super(grid);
		area = new boolean[][]{{false,true,true},{true,true,false}};
		color = Color.yellow;
	}

}

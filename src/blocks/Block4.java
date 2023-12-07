package blocks;

import java.awt.Color;
import java.util.List;

import tetris.GridElement;

public class Block4 extends Block {

	Block4(List<List<GridElement>> grid) {
		super(grid);
		area = new boolean[][]{{true,false},{true,false},{true,true}};
		color = Color.magenta;
	}

}

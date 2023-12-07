package blocks;

import java.awt.Color;
import java.util.List;

import tetris.GridElement;

public class Block5 extends Block {

	Block5(List<List<GridElement>> grid) {
		super(grid);
		area = new boolean[][]{{true,true,false},{false,true,true}};
		color = Color.orange;
	}

}

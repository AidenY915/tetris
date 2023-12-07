package tetris;

import java.awt.Color;

import blocks.Block;

public class GridElement {
	private	Color color;
	private boolean occupied;
	public GridElement() {}
	
	public void occupy(Block block) {
		color = block.getColor();
		occupied = true;
	}
	
	public void destroy() {
		occupied = false;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	public void copy(GridElement other) {
		color = other.color;
		occupied = other.occupied;
	}
	public Color getColor() {
		return color;
	}
	
}

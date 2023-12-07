package blocks;

import java.awt.Color;
import java.util.List;

import tetris.GameSetting;
import tetris.GridElement;

public abstract class Block implements GameSetting { // 4곱 2 boolean 배열로 만들고 좌표는 좌상단 기준으로 해

	protected boolean[][] area;
	protected int x = WIDTH_BLOCK_NUM / 2 - 2;
	protected int y = 0;
	private List<List<GridElement>> grid;
	private boolean rotateToggle = true;
	protected Color color;
	Block(List<List<GridElement>> grid) {
		this.grid = grid;
	}
	
	
	private boolean isBlockCollided() {		//Block에 넣을까 말까
		if(y == HEIGHT_BLOCK_NUM - area[0].length) return true;
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				if(!area[i][j]) continue;
				System.out.println(i + " " + j);
				if(grid.get(x+i).get(y+j+1).isOccupied()) return true;
			}
		}
		return false;
	}

	public boolean moveDown() {
		if(isBlockCollided()) return true;
		y++;
		return false;
		
	}

	public void moveRight() {
		if (x == WIDTH_BLOCK_NUM - area.length)
			return;
		for (int i = area.length - 1; i >= 0; i--) {
			for (int j = 0; j < area[i].length; j++) {
				if (!area[i][j])
					continue;
				if (grid.get(x + i + 1).get(y + j).isOccupied())
					return;
			}
		}
		x++;
	}

	public void moveLeft() {
		if (x == 0)
			return;
		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area[i].length; j++) {
				if (!area[i][j])
					continue;
				if (grid.get(x + i - 1).get(y + j).isOccupied())
					return;
			}
		}
		x--;
	}

	private void rotateRightDown() {
		boolean[][] newArea = new boolean[area[0].length][area.length];
		for (int i = 0; i < area[0].length; i++) { // i는 newArea 기준 x,
			for (int j = 0; j < area.length; j++) { // 대각선 뒤집기
				newArea[newArea.length-1-i][j] = area[j][i];
			}
		}
		for (int i = 0; i < area[0].length; i++) { // 상하 뒤집기
			for (int j = 0; j < area.length; j++) {
				boolean tmp = newArea[i][j];
				newArea[i][j] = newArea[i][newArea[i].length - 1 - j];
				newArea[i][newArea[i].length - 1 - j] = tmp;
			}
		}
		area = newArea;
		//회전시 충돌발생하면 밀기 필요.
		System.out.println("rightDown");
	}
	
	private void rotateRightUp() {
		boolean[][] newArea = new boolean[area[0].length][area.length];
		for (int i = 0; i < area[0].length; i++) { // i는 newArea 기준 x,
			for (int j = 0; j < area.length; j++) { // 대각선 뒤집기
				newArea[newArea.length-1-i][j] = area[j][i];
			}
		}
		area = newArea;
		//회전시 충돌발생하면 밀기 필요.
		System.out.println("rightUp");
	}
	
	public void rotate() {
		if(rotateToggle) rotateRightDown();
		else rotateRightUp();
		rotateToggle = !rotateToggle;
	}

	public boolean[][] getArea() {
		return area;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
}

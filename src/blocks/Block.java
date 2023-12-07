package blocks;

import java.util.List;

import tetris.GameSetting;

public abstract class Block implements GameSetting { // 4곱 2 boolean 배열로 만들고 좌표는 좌상단 기준으로 해

	protected boolean[][] area;
	protected int x = WIDTH_BLOCK_NUM / 2 - 2;
	protected int y = 0;
	private List<List<Boolean>> grid;
	private boolean rotateToggle = false;
	Block(List<List<Boolean>> grid) {
		this.grid = grid;
	}

	public void moveDown() {
		y++;
		
	}

	public void moveRight() {
		if (x == WIDTH_BLOCK_NUM - area.length)
			return;
		for (int i = area.length - 1; i >= 0; i--) {
			for (int j = 0; j < area[i].length; j++) {
				if (!area[i][j])
					continue;
				if (grid.get(x + i + 1).get(y + j))
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
				if (grid.get(x + i - 1).get(y + j))
					return;
			}
		}
		x--;
	}

	private void rotateRightDown() {
		boolean[][] newArea = new boolean[area[0].length][area.length];
		for (int i = 0; i < area[0].length; i++) { // i는 newArea 기준 x,
			for (int j = 0; j < area.length; j++) { // 대각선 뒤집기
				newArea[i][j] = area[j][i];
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
	}
	
	private void rotateRightUp() {
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
}
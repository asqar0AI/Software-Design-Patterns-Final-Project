package figures;

import figurestrategies.MoveStrategy;

public abstract class Piece {
	protected int posY, posX;
	protected MoveStrategy moveStrategy;
	protected int color;
	protected String signature;
	protected String letter;
	
	public Piece(int y, int x) {
		this.posY = y;
		this.posX = x;
	}
	
	public void setColor(int color) {
		this.color = color;
		if (color == 1) {
			letter = "\u001B[36m" + signature + "\u001B[30m";
		} else {
			letter = "\u001B[31m" + signature + "\u001B[30m";
		}
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getPosX() {
		return posX;
	}
	
	@Override
	public String toString() {
		return letter;
	}
}

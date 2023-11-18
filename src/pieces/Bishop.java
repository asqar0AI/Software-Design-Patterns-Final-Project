package pieces;

import piecestrategies.BishopMoveStrategy;

public class Bishop extends Piece {
	public Bishop(int y, int x) {
		super(y, x);
		signature = letter = "B";
		moveStrategy = new BishopMoveStrategy();
	}
	
}

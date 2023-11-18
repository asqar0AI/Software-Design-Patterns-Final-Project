package factory;

import pieces.*;

public class PieceFactory implements PieceFactoryInterface {
	@Override
	public Piece createPiece(String signature, String side, int y, int x) {
		Piece piece = switch (signature.toUpperCase()) {
			case "Q" -> new Queen(y, x);
			case "K" -> new King(y, x);
			case "N" -> new Knight(y, x);
			case "B" -> new Bishop(y, x);
			case "R" -> new Rook(y, x);
			case "P" -> new Pawn(y, x);
			default -> null;
		};
		if (piece != null) {
			piece.setColor(side.equals("white") ? 1 : 0);
		}
		return piece;
	}
}

package factory;

import figures.Piece;

public interface PieceFactoryInterface {
	Piece createPiece(String signature, String side, int y, int x);
}

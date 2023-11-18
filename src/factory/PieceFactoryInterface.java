package factory;

import pieces.Piece;

public interface PieceFactoryInterface {
	Piece createPiece(String signature, String side, int y, int x);
}

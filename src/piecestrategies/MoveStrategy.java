package piecestrategies;

import pieces.Piece;

import java.util.ArrayList;

public interface MoveStrategy
{
    String move(ArrayList<ArrayList<Piece>> board, Piece piece, int destY, int destX);
}

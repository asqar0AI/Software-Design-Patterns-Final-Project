package piecestrategies;

import pieces.Piece;

import java.util.ArrayList;

/*
 * Strategy: Sets its own strategy and avilable moves for each chess piece*/
public interface MoveStrategy
{
    String move(ArrayList<ArrayList<Piece>> board, Piece piece, int destY, int destX);
}

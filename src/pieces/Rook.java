package pieces;

import piecestrategies.RookMoveStrategy;

public class Rook extends Piece
{
    public Rook(int y, int x)
    {
        super(y, x);
        signature = letter = "R";
        moveStrategy = new RookMoveStrategy();
    }
}

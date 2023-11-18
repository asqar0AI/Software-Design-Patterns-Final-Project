package pieces;

import piecestrategies.KnightMoveStrategy;

public class Knight extends Piece
{
    public Knight(int y, int x)
    {
        super(y, x);
        signature = letter = "N";
        moveStrategy = new KnightMoveStrategy();
    }
}

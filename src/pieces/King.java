package pieces;

import piecestrategies.KingMoveStrategy;

public class King extends Piece
{
    public King(int y, int x)
    {
        super(y, x);
        signature = letter = "K";
        moveStrategy = new KingMoveStrategy();
    }
}

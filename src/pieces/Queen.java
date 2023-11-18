package pieces;

import piecestrategies.QueenMoveStrategy;

public class Queen extends Piece
{
    public Queen(int y, int x)
    {
        super(y, x);
        signature = letter = "Q";
        moveStrategy = new QueenMoveStrategy();
    }
    
}

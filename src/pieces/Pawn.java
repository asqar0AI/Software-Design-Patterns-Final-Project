package pieces;

import piecestrategies.BlackPawnMoveStrategy;
import piecestrategies.WhitePawnMoveStrategy;

public class Pawn extends Piece
{
    
    public Pawn(int y, int x)
    {
        super(y, x);
        signature = letter = "P";
    }
    
    @Override
    public void setColor(int color)
    {
        this.color = color;
        if(color == 1)
        {
            letter = "\u001B[36m" + signature + "\u001B[30m";
            moveStrategy = new WhitePawnMoveStrategy();
        }
        else
        {
            letter = "\u001B[31m" + signature + "\u001B[30m";
            moveStrategy = new BlackPawnMoveStrategy();
        }
    }
}

package pieces;

import adapter.CoordinateConverter;
import piecestrategies.MoveStrategy;

import java.util.ArrayList;

public abstract class Piece
{
    public int posY, posX;
    public int color;
    protected MoveStrategy moveStrategy;
    protected String signature;
    protected String letter;
    
    public Piece(int y, int x)
    {
        this.posY = y;
        this.posX = x;
    }
    
    public void setColor(int color)
    {
        this.color = color;
        if(color == 1)
        {
            letter = "\u001B[36m" + signature + "\u001B[30m";
            // cyan
        }
        else
        {
            letter = "\u001B[31m" + signature + "\u001B[30m";
            // red
        }
    }
    
    public boolean move(ArrayList<ArrayList<Piece>> board, int y, int x)
    {
        
        String res = moveStrategy.move(board, this, y, x);
        if(res == null)
        {
            setPos(y, x);
            if(board.get(CoordinateConverter.getDestY()).get(CoordinateConverter.getDestX()) != null)
            {
                board.get(CoordinateConverter.getDestY()).get(CoordinateConverter.getDestX()).setPos(0, 0);
            }
            board.get(CoordinateConverter.getDestY()).set(CoordinateConverter.getDestX(),
                                                          board.get(CoordinateConverter.getDepY())
                                                               .get(CoordinateConverter.getDepX()));
            board.get(CoordinateConverter.getDepY()).set(CoordinateConverter.getDepX(), null);
            return true;
        }
        System.out.println(res);
        return false;
    }
    
    public void setPos(int y, int x)
    {
        posY = y;
        posX = x;
    }
    
    @Override
    public String toString()
    {
        return letter;
    }
}

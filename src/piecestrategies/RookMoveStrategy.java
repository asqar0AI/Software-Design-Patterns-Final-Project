package piecestrategies;

import pieces.Piece;

import java.util.ArrayList;

public class RookMoveStrategy implements MoveStrategy
{
    @Override
    public String move(ArrayList<ArrayList<Piece>> board, Piece piece, int destY, int destX)
    {
        int deltaY, deltaX, curY, curX;
        deltaY = destY - piece.posY;
        deltaX = destX - piece.posX;
        curY = piece.posY;
        curX = piece.posX;
        if(destY > 7 || destX > 7 || destY < 0 || destX < 0)
        {
            return "Piece can't move out of borders, try again.";
        }
        if((Math.abs(deltaX) != 0) == (Math.abs(deltaY) != 0))
        {
            return "Rook can only move rectilinear";
        }
        if(!canPass(board, curY, curX, destY, destX))
        {
            return "Rook can't move through a piece";
        }
        
        return null;
    }
    
    private boolean canPass(ArrayList<ArrayList<Piece>> board, int curY, int curX, int destY, int destX)
    {
        
        boolean pass = false;
        int deltaY, deltaX;
        deltaY = destY - curY;
        deltaX = destX - curX;
        if(deltaY != 0)
        {
            // vertical
            int i = deltaY / Math.abs(deltaY);
            while((curY + i <= 7 && curY + i >= 0))
            {
                curY += i;
                if(curY == destY)
                {
                    pass = true;
                    break;
                }
                if(board.get(curY).get(curX) != null)
                {
                    break;
                }
            }
        }
        else
        {
            // horizontal
            int i = deltaX / Math.abs(deltaX);
            while((curX + i <= 7 && curX + i >= 0))
            {
                curX += i;
                if(curX == destX)
                {
                    pass = true;
                    break;
                }
                if(board.get(curY).get(curX) != null)
                {
                    break;
                }
            }
        }
        return pass;
    }
    
}

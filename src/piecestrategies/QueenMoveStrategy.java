package piecestrategies;

import pieces.Piece;

import java.util.ArrayList;

public class QueenMoveStrategy implements MoveStrategy
{
    @Override
    public String move(ArrayList<ArrayList<Piece>> board, Piece piece, int destY, int destX)
    {
        int deltaY, deltaX, curY, curX;
        deltaY = destY - piece.posY;
        deltaX = destX - piece.posX;
        curY = piece.posY;
        curX = piece.posX;
        
        if(((Math.abs(deltaX) != 0) == (Math.abs(deltaY) != 0)) && Math.abs(deltaX) != Math.abs(deltaY))
        {
            return "Queen can move only rectilinearly or diagonally";
        }
        if(deltaY == 0 && deltaX == 0)
        {
            return "Queen must move";
        }
        if(!canPass(board, curY, curX, destY, destX))
        {
            return "Queen can't move through a piece";
        }
        
        
        return null;
    }
    
    private boolean canPass(ArrayList<ArrayList<Piece>> board, int curY, int curX, int destY, int destX)
    {
        int deltaY, deltaX;
        deltaY = destY - curY;
        deltaX = destX - curX;
        return Math.abs(deltaX) != Math.abs(deltaY) ? canPassRectilinearly(board, curY, curX, destY, destX) :
               canPassDiagonally(board, curY, curX, destY, destX);
        
    }
    
    private boolean canPassRectilinearly(ArrayList<ArrayList<Piece>> board, int curY, int curX, int destY, int destX)
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
    
    private boolean canPassDiagonally(ArrayList<ArrayList<Piece>> board, int curY, int curX, int destY, int destX)
    {
        boolean pass = false;
        int deltaY, deltaX;
        deltaY = destY - curY;
        deltaX = destX - curX;
        int i = deltaY / Math.abs(deltaY), j = deltaX / Math.abs(deltaX);
        while((curY + i <= 7 && curY + i >= 0) && (curX + j <= 8 && curX + j >= 0))
        {
            curY += i;
            curX += j;
            if(curY == destY && curX == destX)
            {
                pass = true;
                break;
            }
            if(board.get(curY).get(curX) != null)
            {
                break;
            }
        }
        return pass;
    }
}

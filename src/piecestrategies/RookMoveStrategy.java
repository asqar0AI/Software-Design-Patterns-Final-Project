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
        if(destY == 0 && destX == 0)
        {
            return "Bishop can't should move";
        }
        if((Math.abs(deltaX) > 0 ? 1 : 0) + (Math.abs(deltaY) > 0 ? 1 : 0) != 1)
        {
            return "Rook can only move horizontally or vertically";
        }
        if(!canPass(board, curY, curX, destY, destX))
        {
            return "Bishop can't move though a piece";
        }
        
        return null;
    }
    
    private boolean canPass(ArrayList<ArrayList<Piece>> board, int curY, int curX, int destY, int destX)
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

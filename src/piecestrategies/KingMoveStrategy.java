package piecestrategies;

import pieces.Piece;

import java.util.ArrayList;

public class KingMoveStrategy implements MoveStrategy
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
        if(((Math.abs(deltaX) != 0) == (Math.abs(deltaY) != 0)) && Math.abs(deltaX) != Math.abs(deltaY))
        {
            return "King can move only rectilinearly or diagonally";
        }
        if(deltaY == 0 && deltaX == 0)
        {
            return "King must move";
        }
        if(Math.abs(deltaY) > 1 || Math.abs(deltaX) > 1)
        {
            return "King can't go that far";
        }
        
        return null;
    }
    
}

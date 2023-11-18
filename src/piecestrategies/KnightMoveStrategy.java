package piecestrategies;

import pieces.Piece;

import java.util.ArrayList;

public class KnightMoveStrategy implements MoveStrategy
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
        if(Math.abs(deltaY) + Math.abs(deltaX) != 3)
        {
            return "Wrong move for Knight";
        }
        if(deltaY == 0 || deltaX == 0)
        {
            return "Knight must move semi-diagonally";
        }
        
        return null;
    }
}

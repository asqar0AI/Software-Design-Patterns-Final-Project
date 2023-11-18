package piecestrategies;

import pieces.Pawn;
import pieces.Piece;

import java.util.ArrayList;

public class BlackPawnMoveStrategy implements MoveStrategy
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
        if(deltaY < 0)
        {
            return "Pawn can't move backwards";
        }
        if(destY == 0)
        {
            return "Pawn can only move forward or 1 field front-diagonally";
        }
        if(deltaY + Math.abs(deltaX) > 2 || Math.abs(deltaX) > 1)
        {
            return "Pawn can't go that far";
        }
        if(deltaY == 2 && board.get(curY + 1).get(destX) != null)
        {
            return "Pawn can't move through a piece";
        }
        if(board.get(destY).get(destX) != null)
        {
            return "Pawn can't beat forward";
        }
        if(deltaX == 1 && !canBeat(board, destY, destX) && !isEnPassant(board, curY, destX))
        {
            return "There's nothing to beat";
        }
        
        return null;
    }
    
    private boolean isEnPassant(ArrayList<ArrayList<Piece>> board, int curY, int destX)
    {
        return isPawnNear(board, curY, destX) && curY == 4;
    }
    
    private boolean isPawnNear(ArrayList<ArrayList<Piece>> board, int curY, int destX)
    {
        return board.get(curY).get(destX).getClass() == Pawn.class;
    }
    
    private boolean canBeat(ArrayList<ArrayList<Piece>> board, int destY, int destX)
    {
        return board.get(destY).get(destX) != null;
    }
}

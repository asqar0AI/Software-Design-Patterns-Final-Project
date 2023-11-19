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
        
        // to check if destination is behind
        if(deltaY < 0)
        {
            return "Pawn can't move backwards";
        }
        // to check if piece is even going to move
        if(deltaY == 0)
        {
            return "Pawn can only move forward or 1 field front-diagonally";
        }
        // to check if piece is moving too far
        if(deltaY + Math.abs(deltaX) > 2 || Math.abs(deltaX) > 1)
        {
            return "Pawn can't go that far";
        }
        if(deltaX == 0)
        {
            // to check double cell move
            if(deltaY == 2)
            {
                // to check if pawn has already been moved
                if(curY > 1)
                {
                    return "This pawn has already made a move";
                }
                // to check if pawn is going to through another piece
                if(board.get(curY + 1).get(destX) != null)
                {
                    return "Pawn can't move through a piece";
                }
            }
            // to check if pawn is going to beat a piece in front of him
            if(board.get(destY).get(destX) != null)
            {
                return "Pawn can't beat forward";
            }
        }
        if(deltaX == 1 && ((!canBeat(board, destY, destX) && !isEnPassant(board, curY, destX)) || !canBeat(board, destY,
                                                                                                           destX)))
        {
            return "There's nothing to beat";
        }
        if(isEnPassant(board, curY, destX))
        {
            return "en passant";
        }
        return null;
    }
    
    private boolean isEnPassant(ArrayList<ArrayList<Piece>> board, int curY, int destX)
    {
        return isEnemyPawnNear(board, curY, destX) && curY == 4 && board.get(curY).get(destX).prevY == 6;
    }
    
    private boolean isEnemyPawnNear(ArrayList<ArrayList<Piece>> board, int curY, int destX)
    {
        return board.get(curY).get(destX) != null && board.get(curY).get(destX).getClass() == Pawn.class && board.get(
                curY).get(destX).color == 1;
    }
    
    private boolean canBeat(ArrayList<ArrayList<Piece>> board, int destY, int destX)
    {
        return board.get(destY).get(destX) != null;
    }
}

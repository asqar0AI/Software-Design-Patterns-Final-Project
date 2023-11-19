package piecestrategies;

import pieces.Piece;
import pieces.Rook;

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
        if(deltaY == 0 && Math.abs(deltaX) == 2 && piece.prevY == curY && curX == piece.prevX)
        {
            if(deltaX < 0 && board.get(curY).get(0).getClass() == Rook.class && isClear(board, curY, curX, 0,
                                                                                        deltaX / 2))
            {
                if(board.get(curY).get(0).prevY == board.get(curY).get(0).posY && board.get(curY)
                                                                                       .get(0).prevX == board.get(curY)
                                                                                                             .get(0).posX)
                {
                    return "castling";
                }
            }
            if(deltaX > 0 && board.get(curY).get(7).getClass() == Rook.class && isClear(board, curY, curX, 7,
                                                                                        deltaX / 2))
            {
                if(board.get(curY).get(7).prevY == board.get(curY).get(7).posY && board.get(curY)
                                                                                       .get(7).prevX == board.get(curY)
                                                                                                             .get(7).posX)
                {
                    return "castling";
                }
            }
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
    
    private boolean isClear(ArrayList<ArrayList<Piece>> board, int curY, int curX, int destX, int i)
    {
        boolean pass = false;
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
        return pass;
    }
    
}

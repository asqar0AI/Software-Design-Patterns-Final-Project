package pieces;

import piecestrategies.MoveStrategy;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Piece
{
    public int prevY, prevX;
    public int posY, posX;
    public int color;
    protected MoveStrategy moveStrategy;
    protected String signature;
    protected String letter;
    
    public Piece(int y, int x)
    {
        this.posY = y;
        this.posX = x;
        prevY = y;
        prevX = x;
    }
    
    // sets side for piece
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
    
    /*
     * checks if move is possible via strategies of each piece type*/
    public boolean move(ArrayList<ArrayList<Piece>> board, int destY, int destX)
    {
        int whiteKingY = -1, whiteKingX = -1;
        int blackKingY = -1, blackKingX = -1;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                Piece cur = board.get(i).get(j);
                if(cur != null && cur.getClass() == King.class)
                {
                    if(cur.color == 1)
                    {
                        whiteKingY = cur.posY;
                        whiteKingX = cur.posX;
                    }
                    else if(cur.color == 0)
                    {
                        blackKingY = cur.posY;
                        blackKingX = cur.posX;
                    }
                }
            }
        }
        if(posY == destY && posX == destX)
        {
            System.out.println("Piece must move");
            return false;
        }
        
        // to check if destination is out of borders
        if(destY > 7 || destX > 7 || destY < 0 || destX < 0)
        {
            System.out.println("Piece can't move out of borders, try again.");
            return false;
        }
        String res = moveStrategy.move(board, this, destY, destX);
        
        if(res == null)
        {
            if(board.get(destY).get(destX) != null && board.get(destY).get(destX).color == this.color)
            {
                System.out.println("You can't beat ally pieces");
                return false;
            }
            int savePrevY = prevY;
            int savePrevX = prevX;
            int savePosY = posY;
            int savePosX = posX;
            Piece saveBeatenCell = board.get(destY).get(destX);
            
            prevY = posY;
            prevX = posX;
            // moves a piece
            board.get(destY).set(destX, this);
            board.get(posY).set(posX, null);
            setPos(destY, destX);
            
            if((color == 1 && isCheck(board, whiteKingY, whiteKingX)) || (color == 0 && isCheck(board, blackKingY,
                                                                                                blackKingX)))
            {
                prevY = savePrevY;
                prevX = savePrevX;
                board.get(savePosY).set(savePosX, board.get(destY).get(destX));
                board.get(destY).set(destX, saveBeatenCell);
                setPos(savePosY, savePosX);
                System.out.println("You can't put your king under attack");
                return false;
            }
            if(((color == 1 && posY == 0) || (color == 0 && posY == 7)) && this.getClass() == Pawn.class)
            {
                System.out.println(
                        "You can transform your pawn into different piece \nWrite N, B, R, Q to replace pawn by Knight, Bishop, Rook and Queen respectfully\nYou can write P if you cool with pawn");
                Scanner newLetterInput = new Scanner(System.in);
                boolean isChosen = false;
                while(!isChosen)
                {
                    String newLetter = newLetterInput.nextLine();
                    switch(newLetter)
                    {
                        case ("P"):
                            board.get(destY).set(destX, new Pawn(destY, destX));
                            isChosen = true;
                            break;
                        case ("R"):
                            board.get(destY).set(destX, new Rook(destY, destX));
                            isChosen = true;
                            break;
                        case ("N"):
                            board.get(destY).set(destX, new Knight(destY, destX));
                            isChosen = true;
                            break;
                        case ("B"):
                            board.get(destY).set(destX, new Bishop(destY, destX));
                            isChosen = true;
                            break;
                        case ("Q"):
                            board.get(destY).set(destX, new Queen(destY, destX));
                            isChosen = true;
                            break;
                        
                        default:
                            System.out.println("Choose one from: P, R, N, B, Q");
                    }
                }
                board.get(destY).get(destX).color = this.color;
                board.get(destY).get(destX).prevY = this.prevY;
                board.get(destY).get(destX).prevX = this.prevX;
                
            }
            
            return true;
        }
        if(res.equals("castling"))
        {
            for(int i = posX; ; i += (destX - posX) / 2)
            {
                if(isCheck(board, posY, i))
                {
                    System.out.println("Casting fields are under attack");
                    return false;
                }
                if(i == destX)
                {
                    break;
                }
            }
            // moving rook to castling position
            if(destX - posX < 0)
            {
                // moving rook to the position
                board.get(posY).get(0).setPos(posY, destX + 1);
                board.get(posY).set(destX + 1, board.get(posY).get(0));
                board.get(posY).set(0, null);
                
            }
            else if(destX - posX > 0)
            {
                board.get(posY).get(7).setPos(posY, destX - 1);
                board.get(posY).set(destX - 1, board.get(posY).get(7));
                board.get(posY).set(7, null);
            }
            // moving king to castling position
            setPos(destY, destX);
            board.get(destY).set(destX, this);
            System.out.println(destX - posX);
            
            return true;
            
        }
        if(res.equals("en passant"))
        {
            int savePrevY = prevY;
            int savePrevX = prevX;
            int savePosY = posY;
            int savePosX = posX;
            Piece saveBeatenCell = board.get(posY).get(destX);
            
            prevY = posY;
            prevX = posX;
            
            // removes beaten pawn
            board.get(posY).set(destX, null);
            setPos(destY, destX);
            // moves a piece
            board.get(destY).set(destX, board.get(posY).get(posX));
            
            if((color == 1 && isCheck(board, whiteKingY, whiteKingX)) || (color == 0 && isCheck(board, blackKingY,
                                                                                                blackKingX)))
            {
                prevY = savePrevY;
                prevX = savePrevX;
                board.get(savePosY).set(savePosX, board.get(destY).get(destX));
                board.get(destY).set(destX, null);
                board.get(savePosY).set(destX, saveBeatenCell);
                setPos(savePosY, savePosX);
                System.out.println("You can't put your king under attack");
                return false;
            }
            
            return true;
        }
        System.out.println(res);
        return false;
    }
    
    private boolean isCheck(ArrayList<ArrayList<Piece>> board, int destY, int destX)
    {
        boolean pass = false;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                Piece cur = board.get(i).get(j);
                if(cur != null && cur.color != board.get(posY).get(posX).color && cur.moveStrategy.move(board, cur,
                                                                                                        destY,
                                                                                                        destX) == null)
                {
                    pass = true;
                }
            }
        }
        return pass;
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

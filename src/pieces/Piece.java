package pieces;

import piecestrategies.MoveStrategy;

import java.util.ArrayList;
import java.util.Scanner;

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
            // moves a piece
            board.get(y).set(x, board.get(posY).get(posX));
            
            setPos(y, x);
            return true;
        }
        if(res.equals("en passant"))
        {
            // moves a piece
            board.get(y).set(x, board.get(posY).get(posX));
            // removes beaten pawn
            board.get(posY).set(x, null);
            
            setPos(y, x);
            return true;
        }
        if(res.equals("morph"))
        {
            System.out.println("Your pawn can transform into different piece\n Write the letter of wanted piece:\n");
            Scanner newLetterInput = new Scanner(System.in);
            boolean isChosen = false;
            while(!isChosen)
            {
                String newLetter = newLetterInput.nextLine();
                switch(newLetter)
                {
                    case ("P"):
                        board.get(y).set(x, new Pawn(y, x));
                        setColor(board.get(posY).get(posX).color);
                        isChosen = true;
                        break;
                    case ("R"):
                        board.get(y).set(x, new Pawn(y, x));
                        setColor(board.get(posY).get(posX).color);
                        isChosen = true;
                        break;
                    case ("N"):
                        board.get(y).set(x, new Pawn(y, x));
                        setColor(board.get(posY).get(posX).color);
                        isChosen = true;
                        break;
                    case ("B"):
                        board.get(y).set(x, new Pawn(y, x));
                        setColor(board.get(posY).get(posX).color);
                        isChosen = true;
                        break;
                    case ("Q"):
                        board.get(y).set(x, new Pawn(y, x));
                        setColor(board.get(posY).get(posX).color);
                        isChosen = true;
                        break;
                    
                    default:
                        System.out.println("Choose one from: P, R, N, B, Q");
                }
            }
            
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

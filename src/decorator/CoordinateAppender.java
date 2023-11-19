package decorator;

import java.io.PrintStream;

/*
 * Decorator: wraps chess board with appropriate coordinates*/
public class CoordinateAppender implements CoordinateDecorator
{
    private static CoordinateAppender coordinateAppender;
    private final PrintStream out;
    
    
    private CoordinateAppender(PrintStream out)
    {
        this.out = out;
    }
    
    /*
     * Singleton: uses singleton pattern to ensure that only 1 instance of decorator exists*/
    public static CoordinateAppender getInstance()
    {
        if(coordinateAppender == null)
        {
            synchronized(CoordinateAppender.class)
            {
                if(coordinateAppender == null)
                {
                    coordinateAppender = new CoordinateAppender(System.out);
                }
            }
        }
        return coordinateAppender;
    }
    
    /*
     * Wrapper*/
    @Override
    public String decorateChessboard(String text)
    {
        StringBuilder chessboard = new StringBuilder();
        chessboard.append("    A B C D E F G H     \n");
        chessboard.append("                        \n");
        String[] lines = text.split("\n");
        for(int i = 0; i < lines.length; i++)
        {
            chessboard.append((8 - i) + "   ");
            chessboard.append(lines[i]);
            chessboard.append("   " + (8 - i) + "\n");
        }
        chessboard.append("                        \n");
        chessboard.append("    A B C D E F G H     \n");
        
        return chessboard.toString();
    }
}

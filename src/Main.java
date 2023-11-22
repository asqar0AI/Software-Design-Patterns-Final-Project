import decorator.DisplayMessage;
import decorator.RoundBorders;

import java.util.Scanner;

public class Main
{
    
    public static DisplayMessage game;
    
    /**
     * Used design patterns:
     * Decorator => to decorating output messages and chess board +
     * Factory => to creating chess figures +
     * Strategy => to defining moves for each figure type +-
     * Observer => to notify players about wrong moves and match result +
     * Adapter => to convert 'a1' like coordinates into numbers (1, 1)
     * Singleton => to make sure there is only 1 enter to the game +
     * to create single decorator +
     * to create single observer
     */
    public static void main(String[] args)
    {
        final String startScreen = """
                                   Do you want to start a new game?
                                   		     
                                          (Y)es         (N)o
                                   """;
        
        // Filling with white background
        System.out.println("\033[48;5;231m" + "\u001B[30m");
        
        
        final Scanner input = new Scanner(System.in);
		
		/*
		 Decorator: Singleton: getting instance of the game window decorator
		 
		 */
        ChessGame chessGame = null;
        game = RoundBorders.getInstance();
        game.display(startScreen);
        String response = input.nextLine().toLowerCase();
        if(response.equals("y"))
        {
            game.display("""
                         Game Starts!
                         
                         Write coordinates separated
                         Example: b1 a3""");
            chessGame = ChessGame.getInstance();
        }
        else if(response.equals("n"))
        {
            game.display("Bye!");
        }
        else
        {
            game.display("Invalid choice. Exiting the game.");
        }
    }
    
}

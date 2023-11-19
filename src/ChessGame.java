import adapter.CoordinateConverter;
import decorator.CoordinateAppender;
import factory.PieceFactory;
import observer.Player;
import pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame
{
    private static ArrayList<ArrayList<Piece>> board;
    private static ChessGame theGame;
    private final PieceFactory pieceFactory = new PieceFactory();
    private final String classic =
            "RNBKQBNR" + "PPPPPPPP" + "        " + "        " + "        " + "        " + "pppppppp" + "rnbkqbnr";
    private final ArrayList<Player> players;
    private final CoordinateAppender indexer;
    private String message;
    
    /*
     * Constructor of the game
     * */
    private ChessGame()
    {
        indexer = CoordinateAppender.getInstance();
        board = new ArrayList<>();
        players = new ArrayList<>(2);
        registerPlayer("Red");
        registerPlayer("Blue");
        
        for(int i = 0; i < 8; i++)
        {
            board.add(new ArrayList<>());
            for(int j = 0; j < 8; j++)
            {
                board.get(i).add(null);
            }
        }
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                String current = String.valueOf(classic.charAt(i * 8 + j));
                board.get(i).set(j, pieceFactory.createPiece(current,
                                                             Character.isLowerCase(current.charAt(0)) ? "white" :
                                                             "black", i, j));
            }
        }
        
        /*Launching the chess game*/
        Game();
    }
    
    
    /*
     * Singleton: retrieves single instance of the game*/
    public static ChessGame getInstance()
    {
        if(theGame == null)
        {
            synchronized(ChessGame.class)
            {
                if(theGame == null)
                {
                    theGame = new ChessGame();
                }
            }
        }
        return theGame;
    }
    
    /*
     * Main game process*/
    private void Game()
    {
        Scanner moveInput = new Scanner(System.in);
        for(int i = 0; ; i++)
        {
            /*
             * Decorator: decorating the chess board*/
            Main.game.display(indexer.decorateChessboard(this.toString()));
            
            /*
             * Observer: notifying about players' turn*/
            notifyTurn(i);
            String departure, destination;
            
            /*
             * Works until it gets valid input coordinate*/
            while(true)
            {
                departure = moveInput.next();
                destination = moveInput.next();
                
                if(!isChessCoordinate(departure, destination))
                {
                    System.out.println("Incompatible format of coordinates");
                    continue;
                }
                
                
                /*
                 * Adapter: using adapter to convert familiar to human coordinates into matrix coordinates so program could understand it*/
                CoordinateConverter.setDeparture(departure);
                CoordinateConverter.setDestination(destination);
                int depY, depX, destY, destX;
                // departure point
                depY = CoordinateConverter.getDepY();
                depX = CoordinateConverter.getDepX();
                // destination point
                destY = CoordinateConverter.getDestY();
                destX = CoordinateConverter.getDestX();
                
                // checks if departure point is chess piece
                if(!isPiece(depY, depX))
                {
                    System.out.println("You must choose existing piece");
                    continue;
                }
                // checks if player moving its own pieces
                if(!isCorrectColor(depY, depX, i))
                {
                    System.out.println("You can move only your pieces");
                    continue;
                }
                // checks if move executed successfully
                if(board.get(CoordinateConverter.getDepY()).get(CoordinateConverter.getDepX())
                        .move(board, CoordinateConverter.getDestY(), CoordinateConverter.getDestX()))
                {
                    break;
                }
            }
        }
    }
    
    // method to check if input coordinates are appropriate
    private boolean isChessCoordinate(String departure, String destination)
    {
        boolean is2 = departure.length() == 2 && destination.length() == 2;
        if(!is2)
        {
            return false;
        }
        departure = departure.toUpperCase();
        destination = destination.toUpperCase();
        char l1 = departure.charAt(0), l2 = destination.charAt(0), i1 = destination.charAt(1), i2 =
                destination.charAt(1);
        boolean isAppropriate =
                Character.isLetter(l1) && Character.isDigit(i1) && Character.isLetter(l2) && Character.isDigit(i2);
        isAppropriate =
                isAppropriate && (l1 >= 'A' && l1 <= 'H') && (l2 >= 'A' && l2 <= 'H') && (i1 >= '1' && i1 <= '8') && (i2 >= '1' && i2 <= '8');
        return is2 && isAppropriate;
    }
    
    // checks if there is a piece in given coordinate
    private boolean isPiece(int depY, int depX)
    {
        return board.get(depY).get(depX) != null;
    }
    
    // checks if color of piece is appropriate to player's color
    private boolean isCorrectColor(int depY, int depX, int moveCount)
    {
        return board.get(depY).get(depX).color == (moveCount + 1) % 2;
    }
    
    /*
     * Observer: notifies players about the turn*/
    public void notifyTurn(int x)
    {
        message = "Turn of " + (players.get((x + 1) % 2).name) + ":\n";
        notifyPlayers();
    }
    
    public void notifyPlayers()
    {
        for(Player player : players)
        {
            player.update(message);
        }
    }
    // adds player to the game
    
    public void registerPlayer(String player)
    {
        players.add(new Player(player));
    }
    
    
    /*
     * Converting chess board into user-friendly text*/
    @Override
    public String toString()
    {
        
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < board.size(); i++)
        {
            for(int f = 0; f < board.get(i).size(); f++)
            {
                if((i + f) % 2 == 0)
                {
                    temp.append("\033[48;5;231m");
                }
                else
                {
                    temp.append("\033[48;5;255m");
                }
                if(board.get(i).get(f) == null)
                {
                    temp.append(" ");
                }
                else
                {
                    temp.append(board.get(i).get(f));
                }
                temp.append(" ").append("\033[48;5;231m");
            }
            temp.append("\n");
        }
        return temp.toString();
    }
}

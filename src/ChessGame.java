import adapter.CoordinateConverter;
import factory.PieceFactory;
import figures.Piece;
import observer.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame {
	private static ChessGame theGame;
	private final PieceFactory pieceFactory = new PieceFactory();
	private final ArrayList<ArrayList<Piece>> board;
	private final String classic = "RNBKQBNR" +
	                               "PPPPPPPP" +
	                               "        " +
	                               "        " +
	                               "        " +
	                               "        " +
	                               "pppppppp" +
	                               "rnbkqbnr";
	private final ArrayList<Player> players;
	private String message;
	
	
	private ChessGame() {
		board = new ArrayList<>();
		players = new ArrayList<>(2);
		registerPlayer("Blue");
		registerPlayer("Red");
		for (int i = 0; i < 8; i++) {
			board.add(new ArrayList<>());
			for (int j = 0; j < 8; j++) {
				board.get(i).add(null);
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				String current = String.valueOf(classic.charAt(i * 8 + j));
				board.get(i).set(j, pieceFactory.createPiece(current,
				                                             Character.isLowerCase(current.charAt(0)) ? "white"
				                                                                                      : "black",
				                                             i, j));
			}
		}
		
		Game();
	}
	
	public static ChessGame getInstance() {
		if (theGame == null) {
			synchronized (ChessGame.class) {
				if (theGame == null) {
					theGame = new ChessGame();
				}
			}
		}
		return theGame;
	}
	
	private void Game() {
		Scanner moveInput = new Scanner(System.in);
		for (int i = 0; ; i++) {
			Main.game.display(this.toString());
			notifyTurn(i);
			String departure = moveInput.next(), destination = moveInput.next();
			moveInput.nextLine();
			while (!Character.isLetter(departure.charAt(0)) || !Character.isDigit(departure.charAt(1)) ||
			       !Character.isLetter(destination.charAt(0)) || !Character.isDigit(destination.charAt(1))) {
				departure = moveInput.next();
				destination = moveInput.next();
				CoordinateConverter.setDeparture(departure);
				CoordinateConverter.setDestination(destination);
			}
		}
	}
	
	public void notifyTurn(int x) {
		message = "Turn of " + (players.get((x % 2)).name) + ":\n";
		notifyPlayers();
	}
	
	public void registerPlayer(String player) {
		players.add(new Player(player));
	}
	
	public void notifyPlayers() {
		for (Player player : players) {
			player.update(message);
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < board.size(); i++) {
			for (int f = 0; f < board.get(i).size(); f++) {
				if ((i + f) % 2 == 0) {
					temp.append("\033[48;5;231m");
				} else {
					temp.append("\033[48;5;255m");
				}
				if (board.get(i).get(f) == null) {
					temp.append(" ");
				} else {
					temp.append(board.get(i).get(f));
				}
				temp.append(" ").append("\033[48;5;231m");
			}
			temp.append("\n");
		}
		return temp.toString();
	}
}

import factory.PieceFactory;
import figures.Piece;

import java.util.ArrayList;

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
	
	
	private ChessGame() {
		board = new ArrayList<>();
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
		
		
		for (int i = 0; i < 2; i++) {
			Main.game.display(this.toString());
			System.out.println(((i % 2) + 1 == 1 ? "Blue" : "Red") + ", your turn:\n");
		}
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

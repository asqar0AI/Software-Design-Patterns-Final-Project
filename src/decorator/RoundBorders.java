package decorator;

import java.io.PrintStream;

public class RoundBorders implements DisplayMessage {
	private static RoundBorders roundBorders;
	private final PrintStream out;
	
	private RoundBorders(PrintStream out) {
		this.out = out;
	}
	
	public static RoundBorders getInstance() {
		if (roundBorders == null) {
			synchronized (RoundBorders.class) {
				if (roundBorders == null) {
					roundBorders = new RoundBorders(System.out);
				}
			}
		}
		return roundBorders;
	}
	
	@Override
	public void display(String text) {
		StringBuilder scroll = new StringBuilder();
		int max_width = 0;
		String[] lines = text.split("\n");
		for (String line : lines) {
			max_width = Math.max(max_width, (line.length() - countOccurrences(line)));
		}
		scroll.append("╭──").append("─".repeat(max_width)).append("──╮\n");
		scroll.append("│  ").append(" ".repeat(max_width)).append("  │\n");
		for (String line : lines) {
			scroll.append("│  ").append(line).append(" ".repeat(max_width - (line.length() - countOccurrences(line)))).append("  │\n");
		}
		scroll.append("│  ").append(" ".repeat(max_width)).append("  │\n");
		scroll.append("╰──").append("─".repeat(max_width)).append("──╯\n");
		out.println(scroll);
	}
	
	private int countOccurrences(String input) {
		String cyan = "\u001B[36m", red = "\u001B[31m", reset = "\u001B[0m", black = "\u001B[30m";
		String blackTile = "\033[48;5;255m", whiteTile = "\033[48;5;231m";
		int count = 0;
		int index = input.indexOf(cyan);
		
		while (index != -1) {
			count += cyan.length();
			index = input.indexOf(cyan, index + 1);
		}
		index = input.indexOf(red);
		
		while (index != -1) {
			count += red.length();
			index = input.indexOf(red, index + 1);
		}
		index = input.indexOf(reset);
		
		while (index != -1) {
			count += reset.length();
			index = input.indexOf(reset, index + 1);
		}
		index = input.indexOf(black);
		
		while (index != -1) {
			count += black.length();
			index = input.indexOf(black, index + 1);
		}
		index = input.indexOf(blackTile);
		
		while (index != -1) {
			count += blackTile.length();
			index = input.indexOf(blackTile, index + 1);
		}
		index = input.indexOf(whiteTile);
		
		while (index != -1) {
			count += whiteTile.length();
			index = input.indexOf(whiteTile, index + 1);
		}
		
		
		return count;
	}
	
}

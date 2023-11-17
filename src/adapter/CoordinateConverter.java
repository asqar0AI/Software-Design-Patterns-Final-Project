package adapter;

public class CoordinateConverter implements CoordinateInterface {
	private static String departure, destination;
	
	private static int depY, depX, destY, destX;
	
	public static void setDeparture(String departure) {
		CoordinateConverter.departure = departure.toUpperCase();
		convertDep();
	}
	
	public static void setDestination(String destination) {
		CoordinateConverter.destination = destination.toUpperCase();
		convertDest();
	}
	
	private static void convertDep() {
		depY = 8 - (Integer.parseInt(String.valueOf(departure.charAt(1))));
		depX = departure.charAt(0) - 65;
	}
	
	private static void convertDest() {
		destY = 8 - (Integer.parseInt(String.valueOf(destination.charAt(1))));
		destX = destination.charAt(0) - 65;
	}
	
	public static int getDepY() {
		return depY;
	}
	
	public static int getDepX() {
		return depX;
	}
	
	public static int getDestY() {
		return destY;
	}
	
	public static int getDestX() {
		return destX;
	}
}

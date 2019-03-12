package gone;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

// BoardConfiguration represents the configuration of pebble on the board
public class BoardConfiguration {

	// The BoardConfiguration hides boardMap, a Map of tuples of coordinates to their PebbleColor,
	// whiteCoordinates, a set of tuples of coordinates corresponding to white pebble locations,
	// and boardModifiedOnLastUpdate, a boolean representing if there are more turns to be taken.
	private Map<Coordinate, PebbleColor> boardMap;
	private Set<Coordinate> whiteCoordinates;
	private boolean boardModifiedOnLastUpdate;

	// Input: A Map of tuple of coordinates to their PebbleColor values possibleBoardMap
	// Output: A BoardConfiguration object that is a valid board
	// Result: If possibleBoardMap is not a valid board, an error will occur
	public BoardConfiguration(Map<Coordinate, PebbleColor> possibleBoardMap) {
		Objects.requireNonNull(possibleBoardMap, "boardMap cannot be null");
		// If isValidBoardConfiguration(possibleBoardMap) Then
		if(isValidBoardConfiguration(possibleBoardMap)) {

			// boardMap <- possibleBoardMap
			this.boardMap = possibleBoardMap;
			// whiteCoordinates <- whitePebbleCoordinates()
			this.whiteCoordinates = whitePebbleCoordinates();
			// boardModifiedOnLastUpdate <- true
			this.boardModifiedOnLastUpdate = true;
		}
		// Otherwise
		else {
			// Error, invalid input
			throw new IllegalArgumentException("Illegal coordinate has negative values in board configuration");
		}
	}

	// Input: A Map of tuple of coordinates to PebbleColor values possibleBoardMap
	// Output: true if the possibleBoardMap is valid, and false otherwise
	private static boolean isValidBoardConfiguration(Map<Coordinate, PebbleColor> boardMap) {

		// validCoordinates <- true
		//boolean validCoordinates = true;
		return boardMap.keySet().stream().anyMatch((c) -> (!(c.getX() < 0 || c.getY() < 0)));
//		findInvalidCoordinates:
//		//Foreach coordinate tuple in the set of keys in the possibleBoardMap Do
//		for(Coordinate coordinate : boardMap.keySet()) {
//			// If either value in the tuple is negative Then
//			if(coordinate.getX() < 0 || coordinate.getY() < 0) {
//				// validCoordinates <- false
//				validCoordinates = false;
//
//				// Break out of the Foreach loop
//				break findInvalidCoordinates;
//			}
//		}
//		// Return validCoordinates
		//return validCoordinates;
	}

	public static class BoardConfigurationTestHook{

		public boolean isValidBoardConfiguration(Map<Coordinate, PebbleColor> boardMap){
			return BoardConfiguration.isValidBoardConfiguration(boardMap);
		}

		public Set<Coordinate> whitePebbleCoordinates(Map<Coordinate, PebbleColor> boardMap){
			BoardConfiguration boardConfiguration = new BoardConfiguration(boardMap);
			return boardConfiguration.whitePebbleCoordinates();
		}
	}

	// Input: None
	// Output: A Boolean value that is true if a black pebble was replaced and false otherwise
	// Result: This BoardConfiguration object will have an updated boardMap after the replacement rules have been applied once
	boolean replacePebblesOnce() {
		// boardModifiedOnLastUpdate <- false
		boardModifiedOnLastUpdate = false;

		// Foreach coordinate tuple in whiteCoordinates Do
		for(Coordinate coordinate : whiteCoordinates) {
			// updateNeighbors(coordinate)
			this.updateNeighbors(coordinate);
			// Remove coordinate from boardMap
			boardMap.remove(coordinate);
		}

		// whiteCoordinates <- whitePebbleCoordinates()
		whiteCoordinates = whitePebbleCoordinates();
		// Return boardModifiedOnLastUpdate
		return boardModifiedOnLastUpdate;
	}

	// Input: None
	// Output: A Set of all tuples of coordinates mapped to white pebble color in this boardMap
	private Set<Coordinate> whitePebbleCoordinates(){

		/* Foreach coordinate tuple in the set of keys in this boardMap Do
		   If boardMap value at coordinate tuple is white then
		   add coordinate to whiteCoordinates */
		Set<Coordinate> whiteCoordinates = boardMap.keySet().stream()
				.filter((c) -> boardMap.get(c) == PebbleColor.WHITE)
				.collect(Collectors.toSet());

		return whiteCoordinates;
	}

	// Input: None
	// Output: A boolean value that is true when there is a black pebble remaining on the board and false otherwise
	boolean blackRemains() {
		// blackPebbleFound <- false;
		// Foreach coordinate tuple in the set of keys in this boardMap Do
		// If boardMap value at coordinate is black then
		// blackPebbleFound <- true;
		// break from the Foreach loop
		// Return blackPebbleFound;
		return boardMap.keySet().stream().anyMatch((c) -> boardMap.get(c) == PebbleColor.BLACK);
	}

	// Input: None
	// Output: true if there is at least one black pebble on the board that hasn’t been visited and false otherwise
	boolean hasUnvisitedBlackCoordinates(){
		// return boardModifiedOnLastUpdate AND whiteCoordinates size is greater than 0
		return boardModifiedOnLastUpdate;
	}

	// Input: coordinate tuple
	// Result: Flips the color of all black neighbors of the input coordinate
	private void updateNeighbors(Coordinate coordinate){
		// Foreach neighbor tuple of coordinate’s neighbors Do
		// If neighbor is a key in this boardmap AND the neighbor’s color is black Then
		// set boardmap at key neighbor to white
		// boardModifiedOnLastUpdate <- true

		long num =
				coordinate.getAdjacentCoordinates().stream()
						.filter(neighbor -> boardMap.keySet().contains(neighbor) && (boardMap.get(neighbor) == PebbleColor.BLACK))
						.peek(neighbor -> boardMap.put(neighbor, PebbleColor.WHITE))
						.peek(neighbor -> boardModifiedOnLastUpdate = true) //or remove this and uncomment line 131
						.count();

		//boardModifiedOnLastUpdate = num > 0 || boardModifiedOnLastUpdate;
	}
}

package gone.src.gone;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// BoardConfiguration represents the configuration of pebble on the board
public class BoardConfiguration {

	// The BoardConfiguration hides boardMap, a Map of tuples of coordinates to their PebbleColor,
	// whiteCoordinates, a set of tuples of coordinates corresponding to white pebble locations,
	// and shouldContinue, a boolean representing if there are more turns to be taken.
	private Map<Coordinate, PebbleColor> boardMap;
	private Set<Coordinate> whiteCoordinates;
	private boolean shouldContinue;

	// Input: A Map of tuple of coordinates to their PebbleColor values possibleBoardMap
	// Output: A BoardConfiguration object that is a valid board
	// Result: If possibleBoardMap is not a valid board, an error will occur
	public BoardConfiguration(Map<Coordinate, PebbleColor> possibleBoardMap) {

		// If isValidBoardConfiguration(possibleBoardMap) Then
		if(isValidBoardConfiguration(possibleBoardMap)) {

			// boardMap ← possibleBoardMap
			this.boardMap = possibleBoardMap;
			// whiteCoordinates ← whitePebbleCoordinates()
			this.whiteCoordinates = whitePebbleCoordinates();
			// shouldContinue ← true
			this.shouldContinue = true;
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

		// validCoordinates ← true
		boolean validCoordinates = true;

		findInvalidCoordinates:
		//Foreach coordinate tuple in the set of keys in the possibleBoardMap Do
		for(Coordinate coordinate : boardMap.keySet()) {
			// If either value in the tuple is negative Then
			if(coordinate.getX() < 0 || coordinate.getY() < 0) {
				// validCoordinates ← false
				validCoordinates = false;

				// Break out of the Foreach loop
				break findInvalidCoordinates;
			}
		}
		// Return validCoordinates
		return validCoordinates;
	}

	// Input: None
	// Output: A Boolean value that is true if a black pebble was replaced and false otherwise
	// Result: This BoardConfiguration object will have an updated boardMap after the replacement rules have been applied once
	boolean replacePebblesOnce() {
		// shouldContinue ← false
		shouldContinue = false;

		// Foreach coordinate tuple in whiteCoordinates Do
		for(Coordinate coordinate : whiteCoordinates) {
			// updateNeighbors(coordinate)
			this.updateNeighbors(coordinate);
			// Remove coordinate from boardMap
			boardMap.remove(coordinate);
		}

		// whiteCoordinates ← whitePebbleCoordinates()
		whiteCoordinates = whitePebbleCoordinates();
		// Return shouldContinue
		return shouldContinue;
	}

	// Input: None
	// Output: A Set of all tuples of coordinates mapped to white pebble color in this boardMap
	private Set<Coordinate> whitePebbleCoordinates(){
		// Set whiteCoordinates ← empty
		Set<Coordinate> whiteCoordinates = new HashSet<>();
		// Foreach coordinate tuple in the set of keys in this boardMap Do
		for(Coordinate coordinate : boardMap.keySet()) {
			// If boardMap value at coordinate tuple is white Then
			if(boardMap.get(coordinate) == PebbleColor.WHITE){
				// Add coordinate to whiteCoordinates
				whiteCoordinates.add(coordinate);
			}
		}
		// Return whiteCoordinates
		return whiteCoordinates;
	}

	// Input: None
	// Output: A boolean value that is true when there is a black pebble remaining on the board and false otherwise
	boolean blackRemains() {
		// blackPebbleFound ← false
		boolean blackPebbleFound = false;

		findBlackPebble:
		// Foreach coordinate tuple in the set of keys in this boardMap Do
		for (Coordinate key : boardMap.keySet()) {
			// If boardMap value at coordinate is black Then
			if(boardMap.get(key) == PebbleColor.BLACK) {
				// blackPebbleFound ← true
				blackPebbleFound = true;
				// Break from the Foreach loop
				break findBlackPebble;
			}
		}
		// Return blackPebbleFound
		return blackPebbleFound;
	}

	// Input: None
	// Output: true if there is at least one black pebble on the board that hasn’t been visited and false otherwise
	boolean hasUnvisitedBlackCoordinates(){
		// return shouldContinue AND whiteCoordinates size is greater than 0
		return shouldContinue && whiteCoordinates.size() > 0;
	}

	// Input: coordinate coordinate tuple
	// Result: Flips the color of all black neighbors of the input coordinate
	private void updateNeighbors(Coordinate coordinate){
		// Foreach neighbor tuple of coordinate’s neighbors Do
		for(Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
			// If neighbor is a key in this boardmap AND the neighbor’s color is black Then
			if(boardMap.keySet().contains(neighbor) && (boardMap.get(neighbor) == PebbleColor.BLACK)){
				// set boardmap at key neighbor to white
				boardMap.put(neighbor, PebbleColor.WHITE);
				// shouldContinue ← true
				shouldContinue = true;
			}
		}
	}

}

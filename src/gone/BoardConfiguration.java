package gone.src.gone;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoardConfiguration {

	private Map<Coordinate, PebbleColor> boardMap;
	private Set<Coordinate> whiteCoordinates;

	public BoardConfiguration(Map<Coordinate, PebbleColor> boardMap) {
		if(isValidBoardConfiguration(boardMap)) {
			this.boardMap = boardMap;
			this.whiteCoordinates = whitePebbleCoordinates(boardMap);
		}
		else {
			throw new IllegalArgumentException("Illegal coordinate in board configuration");
		}
	}
	
	private static boolean isValidBoardConfiguration(Map<Coordinate, PebbleColor> boardMap) {
		boolean validCoordinates = true;
		findInvalidCoordinates:
		for(Coordinate coordinate : boardMap.keySet()) {
			if(coordinate.getX() < 0 || coordinate.getY() < 0) {
				validCoordinates = false;
				break findInvalidCoordinates;
			}
		}
		return validCoordinates;
	}
	
	void applyReplacementRulesOnce() {
		boolean replacementNeeded = false;
		for(Coordinate coordinate : whiteCoordinates) {
			for(Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
				for(Coordinate key : boardMap.keySet()) {
					if(pebbleAtCoordinateIsBlack(neighbor, key)) {
						boardMap.put(key, PebbleColor.WHITE);
						if(!replacementNeeded){
							replacementNeeded = this.hasBlackNeighbors(key);
						}
					}
				}
			}
			boardMap.remove(coordinate);
		}
		whiteCoordinates = whitePebbleCoordinates(boardMap);
	}

	private boolean hasBlackNeighbors(Coordinate coord){
		boolean blackNeighborExists = false;

		findBlackNeighbor:
		for(Coordinate neighbor : coord.getAdjacentCoordinates()){
			for(Coordinate key : boardMap.keySet()){
				if(Coordinate.equals(neighbor, key)){
					if(boardMap.get(key) == PebbleColor.BLACK){
						blackNeighborExists = true;
						break findBlackNeighbor;
					}
				}
			}
		}
		return blackNeighborExists;
	}

	boolean hasMorePebblesToReplace() {
		boolean replacementNeeded = false;
		findBlackCoordinate:
		for (Coordinate coordinate : whiteCoordinates) {
			for (Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
				for (Coordinate key : boardMap.keySet()) {
					if(pebbleAtCoordinateIsBlack(neighbor, key)) {
						replacementNeeded = true;
						break findBlackCoordinate;
					}
				}
			}
		}
		return replacementNeeded;
	}

	private boolean pebbleAtCoordinateIsBlack(Coordinate neighbor, Coordinate key) {
		return Coordinate.equals(neighbor, key) && (boardMap.get(key) == PebbleColor.BLACK);
	}

	private static Set<Coordinate> whitePebbleCoordinates(Map<Coordinate, PebbleColor> boardMap){
		Set<Coordinate> whiteCoordinates = new HashSet<>();
		for(Coordinate coordinate : boardMap.keySet()) {
			if(boardMap.get(coordinate) == PebbleColor.WHITE){
				whiteCoordinates.add(coordinate);
			}
		}
		return whiteCoordinates;
	}
	
	boolean blackRemains() {
		boolean blackPebbleFound = false;
		findBlackPebble:
		for (Coordinate key : boardMap.keySet()) {
			if(boardMap.get(key) == PebbleColor.BLACK) {
				blackPebbleFound = true;
				break findBlackPebble;
			}
		}
		return blackPebbleFound;
	}

}

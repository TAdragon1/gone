package gone;

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
	
	public void applyReplacementRulesOnce() {
		for(Coordinate coordinate : whiteCoordinates) {
			for(Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
				for(Coordinate key : boardMap.keySet()) {
					if(pebbleAtCoordinateIsBlack(neighbor, key)) {
						boardMap.put(key, PebbleColor.WHITE);
					}
				}
			}
			boardMap.remove(coordinate);
		}
		whiteCoordinates = whitePebbleCoordinates(boardMap);
	}
	
	public boolean hasMorePebblesToReplace() {
		boolean replacementNeeded = false;
		for (Coordinate coordinate : whiteCoordinates) {
			for (Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
				for (Coordinate key : boardMap.keySet()) {
					if(pebbleAtCoordinateIsBlack(neighbor, key)) {
						replacementNeeded = true;
					}
				}
			}
		}
		return replacementNeeded;
	}
	
	private static Set<Coordinate> whitePebbleCoordinates(Map<Coordinate, PebbleColor> boardMap){
		Set<Coordinate> whiteCoordinates = new HashSet<>();
		for(Coordinate coordinate : boardMap.keySet()) {
			if(boardMap.get(coordinate).equals(PebbleColor.WHITE)){
				whiteCoordinates.add(coordinate);
			}
		}
		return whiteCoordinates;
	}
	
	private boolean pebbleAtCoordinateIsBlack(Coordinate neighbor, Coordinate key) {
		return (Coordinate.equals(neighbor, key) && boardMap.get(key).equals(PebbleColor.BLACK));
	}
	
	public boolean blackRemains() {
		boolean blackPebbleFound = false;
		findBlackPebble:
		for (Coordinate key : boardMap.keySet()) {
			if(boardMap.get(key).equals(PebbleColor.BLACK)) {
				blackPebbleFound = true;
				break findBlackPebble;
			}
		}
		return blackPebbleFound;
	}
}

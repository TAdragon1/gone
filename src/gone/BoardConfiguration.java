package gone.src.gone;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoardConfiguration {

	private Map<Coordinate, PebbleColor> boardMap;
	private Set<Coordinate> whiteCoordinates;
	private boolean shouldContinue;

	public BoardConfiguration(Map<Coordinate, PebbleColor> boardMap) {
		if(isValidBoardConfiguration(boardMap)) {
			this.boardMap = boardMap;
			this.whiteCoordinates = whitePebbleCoordinates();
			this.shouldContinue = true;
		}
		else {
			throw new IllegalArgumentException("Illegal coordinate has negative values in board configuration");
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

	boolean hasUnvisitedBlackCoordinates(){
		return whiteCoordinates.size() > 0 && shouldContinue;
	}

	boolean replacePebblesOnce() {
		shouldContinue = false;

		for(Coordinate coordinate : whiteCoordinates) {
			this.updateNeighbors(coordinate);
			boardMap.remove(coordinate);
		}

		whiteCoordinates = whitePebbleCoordinates();
		return shouldContinue;
	}

	private void updateNeighbors(Coordinate coordinate){
		for(Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
			if(boardMap.keySet().contains(neighbor) && (boardMap.get(neighbor) == PebbleColor.BLACK)){
				boardMap.put(neighbor, PebbleColor.WHITE);
				shouldContinue = true;
			}
		}
	}

	private Set<Coordinate> whitePebbleCoordinates(){
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

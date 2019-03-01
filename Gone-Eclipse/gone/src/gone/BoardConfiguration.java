package gone;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BoardConfiguration {
	
	private Map<Coordinate, PebbleColor> boardMap;

	public BoardConfiguration(Map<Coordinate, PebbleColor> boardMap) {
		if(isValidBoardConfiguration(boardMap)) {
			this.boardMap = boardMap;
		}
		else {
			//throw invalid argument error
		}
	}
	
	private static boolean isValidBoardConfiguration(Map<Coordinate, PebbleColor> boardMap) {
		boolean noInvalidCoordinates = true;
		for(Coordinate coordinate : boardMap.keySet()) {
			if(coordinate.getX() < 0 || coordinate.getY() < 0) {
				noInvalidCoordinates = false;
				break;
			}
		}
		return noInvalidCoordinates;
	}
	
	public void applyReplacementRulesOnce() {
		List<Coordinate> whiteCoordinates = whitePebbleCoordinates(this.boardMap);
		for(Coordinate coordinate : whiteCoordinates) {
			for(Coordinate neighbor : coordinate.adjacentCoordinates()) {
				if(boardMap.get(neighbor).equals(PebbleColor.BLACK)) {
					boardMap.put(neighbor, PebbleColor.WHITE);
				}
			}
		}
		// Remove whiteCoordinates from the boardMap
	}
	
	private static List<Coordinate> whitePebbleCoordinates(Map<Coordinate, PebbleColor> boardMap){
		List<Coordinate> whiteCoordinates = new LinkedList<>();
		for(Coordinate coordinate : boardMap.keySet()) {
			if(boardMap.get(coordinate).equals(PebbleColor.WHITE)){
				whiteCoordinates.add(coordinate);
			}
		}
		return whiteCoordinates;
	}
	
	public boolean hasMorePebblesToReplace() {
		boolean replacementNeeded = false;
		findOppositeColoredNeighbors:
		for (Coordinate coordinate : boardMap.keySet()) {
			if(boardMap.get(coordinate).equals(PebbleColor.WHITE)) {
				for(Coordinate neighbor : coordinate.adjacentCoordinates()) {
					if(boardMap.get(neighbor).equals(PebbleColor.BLACK)) {
						replacementNeeded = true;
						break findOppositeColoredNeighbors;
					}
				}
			}
			else {
				for(Coordinate neighbor : coordinate.adjacentCoordinates()) {
					if(boardMap.get(neighbor).equals(PebbleColor.WHITE)) {
						replacementNeeded = true;
						break findOppositeColoredNeighbors;
					}
				}
			}
		}
		return replacementNeeded;
	}
	
	public boolean blackRemains() {
		boolean blackPebbleFound = false;
		for (Coordinate key : boardMap.keySet()) {
			if(boardMap.get(key).equals(PebbleColor.BLACK)) {
				blackPebbleFound = true;
				break;
			}
		}
		return blackPebbleFound;
	}
}

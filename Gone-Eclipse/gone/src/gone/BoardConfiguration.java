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
		for(Coordinate coordinate : whiteCoordinates) {
			for(Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
				for(Coordinate key : boardMap.keySet()) {
					if(key.equals(neighbor) && boardMap.get(key).equals(PebbleColor.BLACK)) {
						boardMap.put(key, PebbleColor.WHITE);
					}
				}
			}
			boardMap.remove(coordinate);
		}
		whiteCoordinates = whitePebbleCoordinates(boardMap);
	}
	
	private static Set<Coordinate> whitePebbleCoordinates(Map<Coordinate, PebbleColor> boardMap){
		Set<Coordinate> whiteCoordinates = new HashSet<>();
		for(Coordinate coordinate : boardMap.keySet()) {
			if(boardMap.get(coordinate).equals(PebbleColor.WHITE)){
				whiteCoordinates.add(coordinate);
			}
		}
		System.out.println(whiteCoordinates);
		return whiteCoordinates;
	}
	
	public boolean hasMorePebblesToReplace() {
		boolean replacementNeeded = false;
		for (Coordinate coordinate : whiteCoordinates) {
			for (Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
				for (Coordinate key : boardMap.keySet()) {
					if(neighbor.equals(key)) {
						if(boardMap.get(key).equals(PebbleColor.BLACK)) {
							replacementNeeded = true;
						}
					}
				}
			}
		}
		return replacementNeeded;
	}
	
//	public boolean hasMorePebblesToReplace() {
//		boolean replacementNeeded = false;
//		findOppositeColoredNeighbors:
//		for (Coordinate coordinate : boardMap.keySet()) {
//			if(boardMap.get(coordinate).equals(PebbleColor.WHITE)) {
//				for(Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
//					if(boardMap.get(neighbor) != null && boardMap.get(neighbor).equals(PebbleColor.BLACK)) {
//						replacementNeeded = true;
//						break findOppositeColoredNeighbors;
//					}
//				}
//			}
//			else {
//				for(Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
//					if(boardMap.get(neighbor) != null && boardMap.get(neighbor).equals(PebbleColor.WHITE)) {
//						replacementNeeded = true;
//						break findOppositeColoredNeighbors;
//					}
//				}
//			}
//		}
//		return replacementNeeded;
//	}
	
//	public boolean hasMorePebblesToReplace() {
//		boolean replacementNeeded = false;
//		outerLoop:
//		for (Coordinate coordinate : boardMap.keySet()) {
//			for (Coordinate neighbor : coordinate.getAdjacentCoordinates()) {
//				// If the two adjacent colors are not the same
//				if(!boardMap.get(coordinate).equals(boardMap.get(neighbor))) {
//					replacementNeeded = true;
//					break outerLoop;
//				}
//			}
//		}
//		return replacementNeeded;
//	}
	
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

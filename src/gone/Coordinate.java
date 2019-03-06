package gone.src.gone;

import java.util.HashSet;
import java.util.Set;

public class Coordinate {

	private int x;
	private int y;
	private Set<Coordinate> adjacentCoordinates = new HashSet<>();
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	int getX() {
		return x;
	}
	
	int getY() {
		return y;
	}
	
	Set<Coordinate> getAdjacentCoordinates(){
		if(adjacentCoordinates.isEmpty()) {
			adjacentCoordinates = new HashSet<>();
			
			int[] mods = {-1, 1};
			for (int mod : mods) {
				adjacentCoordinates.add(new Coordinate(x + mod, y));
				adjacentCoordinates.add(new Coordinate(x, y + mod));
			}
			
		}
		return adjacentCoordinates;
	}

	@Override
	public boolean equals(Object o){
		boolean equalCoordinates = false;

		if(o instanceof Coordinate){
			Coordinate c = (Coordinate) o;
			if (this.getX() == c.getX() && this.getY() == c.getY()) {
				equalCoordinates = true;
			}
		}

		return equalCoordinates;
	}
}

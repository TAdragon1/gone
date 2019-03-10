package gone;

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
		adjacentCoordinates = new HashSet<>();

		int[][] mods = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for (int[] mod : mods) {
			adjacentCoordinates.add(new Coordinate(x + mod[0], y + mod[1]));
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

	@Override
	public int hashCode() {
		return (((x + y) * (x + y + 1) / 2) + y);	//Cantor pairing function
	}

}

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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Set<Coordinate> getAdjacentCoordinates(){
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
	
	public static boolean equals(Coordinate c1, Coordinate c2) {
		boolean equalCoordinates = false;
		if(c1.getX() == c2.getX() && c1.getY() == c2.getY()) {
			equalCoordinates = true;
		}
		return equalCoordinates;
	}
}

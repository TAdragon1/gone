package gone;

import java.util.ArrayList;
import java.util.List;

public class Coordinate {

	private int x;
	private int y;
	private List<Coordinate> adjacentCoordinates = null;
	
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
	
	public List<Coordinate> adjacentCoordinates(){
		if(adjacentCoordinates == null) {
			adjacentCoordinates = new ArrayList<>();
			
			int[] mods = {-1, 1};
			for (int mod : mods) {
				adjacentCoordinates.add(new Coordinate(x + mod, y));
				adjacentCoordinates.add(new Coordinate(x, y + mod));
			}
			
		}
		return adjacentCoordinates;
	}
}

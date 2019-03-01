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
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public List<Coordinate> adjacentCoordinates(){
		if(adjacentCoordinates == null) {
			adjacentCoordinates = new ArrayList<>();
			adjacentCoordinates.add(new Coordinate(this.x - 1, this.y));
			adjacentCoordinates.add(new Coordinate(this.x + 1, this.y));
			adjacentCoordinates.add(new Coordinate(this.x, this.y - 1));
			adjacentCoordinates.add(new Coordinate(this.x, this.y + 1));
		}
		return adjacentCoordinates;
	}
}

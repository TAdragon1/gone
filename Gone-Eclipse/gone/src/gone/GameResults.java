package gone;

public class GameResults {
	
	private boolean blackPebbleRemaining;
	private int rounds;
	
	public GameResults(boolean blackPebbleRemaining, int rounds) {
		this.blackPebbleRemaining = blackPebbleRemaining;
		this.rounds = rounds;
	}

	public int getRounds() {
		return rounds;
	}

	public boolean doesBlackPebbleRemain() {
		return blackPebbleRemaining;
	}
}

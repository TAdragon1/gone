package gone.src.gone;

public class GameResults {
	
	private boolean blackPebbleRemaining;
	private int rounds;
	
	public GameResults(boolean blackPebbleRemaining, int rounds) {
		this.blackPebbleRemaining = blackPebbleRemaining;
		this.rounds = rounds;
	}

	int getRounds() {
		return rounds;
	}

	boolean isBlackPebbleRemaining() {
		return blackPebbleRemaining;
	}
}

package gone.src.gone;

public class GameResults {
	
	private boolean blackPebbleRemaining;
	private int rounds;
	
	public GameResults(int rounds, boolean blackPebbleRemaining) {
		this.rounds = rounds;
		this.blackPebbleRemaining = blackPebbleRemaining;
	}

	int getRounds() {
		return rounds;
	}

	boolean isBlackPebbleRemaining() {
		return blackPebbleRemaining;
	}
}

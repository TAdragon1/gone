package gone;

public class Gone {

	private BoardConfiguration gameBoard;
	
	public Gone(BoardConfiguration gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public GameResults roundsAndBlackPebbleRemains() {
		int rounds = 0;
		while(gameBoard.hasMorePebblesToReplace()) {
			gameBoard.applyReplacementRulesOnce();
			rounds++;
			System.out.println(rounds);
		}
		return new GameResults(gameBoard.blackRemains(), rounds);
	}
}

package gone;

// Gone represents the game of Gone.
public class Gone {

	// Gone has two hidden fields called gameBoard and rounds.
	// gameBoard holds information on current BoardConfiguration state.
	private BoardConfiguration gameBoard;
	// rounds is the number of rounds it takes to play Gone on the board configuration
	private int rounds = 0;

	// Input: A BoardConfiguration boardConfiguration
	// Output: A Gone object with a valid gameBoard
	public Gone(BoardConfiguration gameBoard) {
		// gameBoard <- boardConfiguration
		this.gameBoard = gameBoard;
	}

	// Input: None
	// Output: A tuple of the number of iterations the game runs and whether or not a black pebble remains
	public GameResults finalGameResults() {
		// While gameBoard.hasUnvisitedBlackCoordinates() Do
		while(gameBoard.hasUnvisitedBlackCoordinates()){
			// If gameBoard.replacePebblesOnce() Then
			if(gameBoard.replacePebblesOnce()) {
				// rounds <- rounds + 1
				rounds++;
			}
		}
		// Return rounds, gameBoard.blackRemains()
		return new GameResults(rounds, gameBoard.blackRemains());
	}
}
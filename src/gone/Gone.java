package gone.src.gone;

public class Gone {

	private BoardConfiguration gameBoard;
	
	public Gone(BoardConfiguration gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public GameResults finalGameResults() {
		int rounds = 0;

		while(gameBoard.hasUnvisitedBlackCoordinates()){
			if(gameBoard.replacePebblesOnce()) {
				rounds++;
			}
		}

		return new GameResults(gameBoard.blackRemains(), rounds);
	}
}

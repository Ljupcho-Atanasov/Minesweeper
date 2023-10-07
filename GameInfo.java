
public class GameInfo {
	

	private int gameCount;
	private int wins;
	private int looses;
	private String gameState; // "Player wins" ,  "Not Finished" , "Player looses"
	
	public GameInfo() {
		
		gameCount = 0;
		wins = 0;
		looses = 0;
		gameState = "Not Finished";
	}
	
	
	public void gameOver() {
		
		if (gameState.equals("Not Finished")) {
			System.out.println("GameInfo.gameOver() shouldn't be called!");
		}
		
		if (gameState.equals("Player wins")) {
			++gameCount;
			++wins;
		}
		
		if (gameState.equals("Player looses")) {
			++gameCount;
			++looses;
		}
		gameState = "Not Finished";
	}


	public int getPlayerWins() {
		return wins;
	}

	public int getPlayerLooses() {
		return looses;
	}

	public int getGameCount() {
		return gameCount;
	}


	public String getGameState() {
		return gameState;
	}
	
	public void setGameState(String state) {
		this.gameState = state;
	}

}


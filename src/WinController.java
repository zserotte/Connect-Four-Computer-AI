
/*
 * WinController.java
 * Author: Zachary Serotte
 * This object's job is to decide
 * if there is a winning configuration 
 * (four in a row) on the board.
 */
public class WinController {
	GameBoard myGameBoard;
	
	public WinController(GameBoard game) {
		myGameBoard = game;
	}

	public boolean hasWinner() {
		boolean win = false;
		int[][] board = myGameBoard.getBoard();
				for (int i = 0; i < board.length - 3; i++) { // check vertical
					for (int j = 0; j < board[0].length; j++) {
						if (board[i][j] == GameBoard.RED
								&& board[i + 1][j] == GameBoard.RED
								&& board[i + 2][j] == GameBoard.RED
								&& board[i + 3][j] == GameBoard.RED) {
							win = true;	
						}
						else if (board[i][j] == GameBoard.BLACK
								&& board[i + 1][j] == GameBoard.BLACK
								&& board[i + 2][j] == GameBoard.BLACK
								&& board[i + 3][j] == GameBoard.BLACK) {
							win = true;	
						}
					}
				}
				for (int i = 0; i < board.length; i++) { // check horizontal
					for (int j = 0; j < board[0].length - 3; j++) {
						if (board[i][j] == GameBoard.RED
								&& board[i][j + 1] == GameBoard.RED
								&& board[i][j + 2] == GameBoard.RED
								&& board[i][j + 3] == GameBoard.RED) {
							win = true;
						}
						else if (board[i][j] == GameBoard.BLACK
								&& board[i][j + 1] == GameBoard.BLACK
								&& board[i][j + 2] == GameBoard.BLACK
								&& board[i][j + 3] == GameBoard.BLACK) {
							win = true;
						}
					}
				}
				// Diagonal check down right
				for (int i = 0; i < board.length - 3; i++) {
					for (int j = 0; j < board[0].length - 3; j++) {
						if (board[i][j] == GameBoard.RED
								&& board[i + 1][j + 1] == GameBoard.RED
								&& board[i + 2][j + 2] == GameBoard.RED
								&& board[i + 3][j + 3] == GameBoard.RED) {
							win = true;
						}
						else if (board[i][j] == GameBoard.BLACK
								&& board[i + 1][j + 1] == GameBoard.BLACK
								&& board[i + 2][j + 2] == GameBoard.BLACK
								&& board[i + 3][j + 3] == GameBoard.BLACK) {
							win = true;
						}
					}
				}
				// Diagonal check up right
				for (int i = 3; i < board.length; i++) {
					for (int j = 0; j < board[0].length - 3; j++) {
						if (board[i][j] == GameBoard.RED
								&& board[i - 1][j + 1] == GameBoard.RED
								&& board[i - 2][j + 2] == GameBoard.RED
								&& board[i - 3][j + 3] == GameBoard.RED) {
							win = true;
						}
						else if (board[i][j] == GameBoard.BLACK
								&& board[i - 1][j + 1] == GameBoard.BLACK
								&& board[i - 2][j + 2] == GameBoard.BLACK
								&& board[i - 3][j + 3] == GameBoard.BLACK) {
							win = true;
						}
					}
				}
				// Diagonal check down left
				for (int i = 0; i < board.length - 3; i++) {
					for (int j = board[0].length - 1; j > 2; j--) {
						if (board[i][j] == GameBoard.RED
								&& board[i + 1][j - 1] == GameBoard.RED
								&& board[i + 2][j - 2] == GameBoard.RED
								&& board[i + 3][j - 3] == GameBoard.RED) {
							win = true;
						}
						else if (board[i][j] == GameBoard.BLACK
								&& board[i + 1][j - 1] == GameBoard.BLACK
								&& board[i + 2][j - 2] == GameBoard.BLACK
								&& board[i + 3][j - 3] == GameBoard.BLACK) {
							win = true;
						}
					}
				}
				// Diagonal check up left
				for (int i = board.length - 1; i > 2; i--) {
					for (int j = board[0].length - 1; j > 2; j--) {
						if (board[i][j] == GameBoard.RED
								&& board[i - 1][j - 1] == GameBoard.RED
								&& board[i - 2][j - 2] == GameBoard.RED
								&& board[i - 3][j - 3] == GameBoard.RED) {
							win = true;
						}
						else if (board[i][j] == GameBoard.BLACK
								&& board[i - 1][j - 1] == GameBoard.BLACK
								&& board[i - 2][j - 2] == GameBoard.BLACK
								&& board[i - 3][j - 3] == GameBoard.BLACK) {
							win = true;
							}
						}
					}
			return win;
		}	
	}


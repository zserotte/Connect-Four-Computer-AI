
/*
* @author: Zachary Serotte
* @version: 12/06/2016
*/

public class SerottePlayer extends Player{
	public SerottePlayer() {
		myName = "Serotte";
	}
	
	public int ai(GameBoard gb) {
		int[][] board = gb.getBoard();
		int challenger = 1;
		int move = (int)(Math.random()*board[0].length);
		
		if (myColor == GameBoard.BLACK) {
			challenger = GameBoard.RED;
		}
		else {
			challenger = GameBoard.BLACK;
		}
		
		while(!gb.isLegalMove(move)){
			move = (int)(Math.random()*board[0].length);
		}
		
		/*
		 * All code below is strategy outside of blocking and winning
		 */
		
		for (int i = board.length - 2; i < board.length; i++) { // checks for 1 space 1
			for (int j = 0; j < board[0].length; j++) { // for bottom row
				if (board[i][j] == challenger
						&& board[i - 1][j] == challenger
						&& board[i - 2][j] == GameBoard.EMPTY) {
						move = j;
				}
			}
		}
		
		for (int i = board.length - 1; i < board.length; i++) { // checks for 1 checker, than a space
			for (int j = 1; j < board[0].length - 3; j++) { // then another checker for the bottom row
				if (board[i][j] == challenger
						&& board[i][j + 2] == challenger
						&& board[i][j + 1] == GameBoard.EMPTY) {
						move = j + 1;
				}
			}
		}
		
		for (int i = 0; i < board.length - 1; i++) { // checks for 1 space 1
			for (int j = 1; j < board[0].length - 3; j++) { // for non bottom row
				if (board[i][j] == challenger
						&& board[i][j + 2] == challenger
						&& board[i][j + 1] == GameBoard.EMPTY
						&& board[i + 1][j + 1] != GameBoard.BLACK) {
						move = j + 1;
				}
			}
		}
		
		for (int i = 0; i < board.length - 1; i++) { // checks for 2 in a row horizontally left to right 
			for (int j = 0; j < board[0].length - 3; j++) { // (except for bottom row) then drops to the right of the 2
				if (board[i][j] == challenger
						&& board[i][j + 1] == challenger
						&& board[i + 1][j + 2] != GameBoard.EMPTY
						&& board[i][j + 2] == GameBoard.EMPTY) {
						move = j + 2;
				}
			}
		}
		
		for (int i = 0; i < board.length - 1; i++) { // checks for 2 in a row horizontally right to left 
			for (int j = board[0].length - 1; j > 2; j--) { // (except for bottom row) then drops to the left of the 2
				if (board[i][j] == challenger
						&& board[i][j - 1] == challenger
						&& board[i + 1][j - 2] != GameBoard.EMPTY
						&& board[i][j - 2] == GameBoard.EMPTY) {
						move = j - 2;
				}
			}
		}
		
		for (int i = board.length - 1; i < board.length; i++) { // checks for 2 in a row horizontally on bottom 
			for (int j = 0; j < board[0].length - 3; j++) { // row left to right then drops to the right of the 2
				if (board[i][j] == challenger
						&& board[i][j + 1] == challenger
						&& board[i][j + 2] == GameBoard.EMPTY) {
						move = j + 2;
				}
			}
		}
		
		for (int i = board.length - 1; i < board.length; i++) { // checks for 2 in a row horizontally right to 
			for (int j = board[0].length - 1; j > 2; j--) { // left on bottom row then drops to the left of the 2
				if (board[i][j] == challenger
						&& board[i][j - 1] == challenger
						&& board[i][j - 2] == GameBoard.EMPTY) {
						move = j - 2;
				}
			}
		}
		
		/*
		 * All code below is for blocking a win, up until otherwise notified
		 */
		
		for (int i = 3; i < board.length; i++) { // checks for 3 in a row vertical
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == challenger
						&& board[i - 1][j] == challenger
						&& board[i - 2][j] == challenger
						&& board[i - 3][j] == GameBoard.EMPTY) {
						move = j;
				}
			}
		}

		for (int i = 0; i < board.length - 1; i++) { 
					for (int j = 0; j < board[0].length - 3; j++) {
						if (board[i][j] == challenger	// checks for 3 in a row horiz right, except for bottom row
								&& board[i][j + 1] == challenger
								&& board[i][j + 2] == challenger
								&& board[i + 1][j + 3] != GameBoard.EMPTY
								&& board[i][j + 3] == GameBoard.EMPTY) {
								move = j + 3;
						}
						else if (board[i][j] == challenger // checks for 1 space 2 horiz right, except for bottom row
								&& board[i][j + 2] == challenger
								&& board[i][j + 3] == challenger
								&& board[i][j+1] == GameBoard.EMPTY
								&& board[i + 1][j + 1] != GameBoard.EMPTY) {
								move = j + 1;
						}
						else if (board[i][j] == challenger // checks for 2 space 1 horiz right, except for bottom row
								&& board[i][j + 1] == challenger
								&& board[i][j + 3] == challenger
								&& board[i][j + 2] == GameBoard.EMPTY
								&& board[i + 1][j + 2] != GameBoard.EMPTY) {
							move = j + 2;
						}
					}
				}
		
		for (int i = board.length - 1; i < board.length; i++) { 
			for (int j = 0; j < board[0].length - 3; j++) {
				if (board[i][j] == challenger	// checks for 3 in a row horiz right for bottom row
						&& board[i][j + 1] == challenger
						&& board[i][j + 2] == challenger
						&& board[i][j + 3] == GameBoard.EMPTY) {
						move = j + 3;
				}
				else if (board[i][j] == challenger // checks for 1 space 2 horiz right for bottom row
						&& board[i][j + 2] == challenger
						&& board[i][j + 3] == challenger
						&& board[i][j+1] == GameBoard.EMPTY) {
						move = j + 1;
				}
				else if (board[i][j] == challenger // checks for 2 space 1 horiz right for bottom row
						&& board[i][j + 1] == challenger
						&& board[i][j + 3] == challenger
						&& board[i][j + 2] == GameBoard.EMPTY) {
					move = j + 2;
				}
			}
		}
		
		for (int i = 0; i < board.length - 1; i++) { 
			for (int j = 3; j < board[0].length; j++) { 
				if (board[i][j] == challenger // checks for 3 in a row horiz left, except for bottom row
						&& board[i][j - 1] == challenger
						&& board[i][j - 2] == challenger
						&& board[i][j - 3] == GameBoard.EMPTY
						&& board[i + 1][j - 3] != GameBoard.EMPTY) {
						move = j - 3;
				}
				else if (board[i][j] == challenger // checks for 1 space 2 horiz left, except for bottom row
						&& board[i][j - 2] == challenger
						&& board[i][j - 3] == challenger
						&& board[i][j - 1] == GameBoard.EMPTY		
						&& board[i + 1][j - 1] != GameBoard.EMPTY) {
						move = j - 1;
				}
				else if (board[i][j] == challenger // checks for 2 space 1 horiz left, except for bottom row
						&& board[i][j - 1] == challenger
						&& board[i][j - 3] == challenger
						&& board[i][j - 2] == GameBoard.EMPTY
						&& board[i + 1][j - 2] != GameBoard.EMPTY) {
						move = j - 2;
				}
			}
		}
		
		for (int i = board.length - 1; i < board.length; i++) { 
			for (int j = 3; j < board[0].length; j++) { 
				if (board[i][j] == challenger // checks for 3 in a row horiz left for bottom row
						&& board[i][j - 1] == challenger
						&& board[i][j - 2] == challenger
						&& board[i][j - 3] == GameBoard.EMPTY) {
						move = j - 3;
				}
				else if (board[i][j] == challenger // checks for 1 space 2 horiz left for bottom row
						&& board[i][j - 2] == challenger
						&& board[i][j - 3] == challenger
						&& board[i][j - 1] == GameBoard.EMPTY		) {
						move = j - 1;
				}
				else if (board[i][j] == challenger // checks for 2 space 1 horiz left for bottom row
						&& board[i][j - 1] == challenger
						&& board[i][j - 3] == challenger
						&& board[i][j - 2] == GameBoard.EMPTY) {
						move = j - 2;
				}
			}
		}
		
		for (int i = 0; i < board.length - 4; i++) { 
			for (int j = 0; j < board[0].length - 3; j++) {
				if (board[i][j] == challenger // down right 3 in a row for top row
						&& board[i + 1][j + 1] == challenger
						&& board[i + 2][j + 2] == challenger
						&& board[i + 4][j + 3] != GameBoard.EMPTY
						&& board[i + 3][j + 3] == GameBoard.EMPTY) {
						move = j + 3;
				}
				else if (board[i][j] == challenger // down right 2 space 1 for top row
						&& board[i + 1][j + 1] == challenger
						&& board[i + 3][j + 3] == challenger
						&& board[i + 3][j + 2] != GameBoard.EMPTY
						&& board[i + 2][j + 2] == GameBoard.EMPTY) {
						move = j + 2;
				}
				else if (board[i][j] == challenger // down right 1 space 2 for top row
						&& board[i + 2][j + 2] == challenger
						&& board[i + 3][j + 3] == challenger
						&& board[i + 2][j + 1] != GameBoard.EMPTY
						&& board[i + 1][j + 1] == GameBoard.EMPTY) {
						move = j + 1;
				}
			}
		}
		
		for (int i = 1; i < board.length - 3; i++) { 
			for (int j = 0; j < board[0].length - 3; j++) {
				if (board[i][j] == challenger // down right 3 in a row for second row
						&& board[i + 1][j + 1] == challenger
						&& board[i + 2][j + 2] == challenger
						&& board[i + 3][j + 3] == GameBoard.EMPTY) {
						move = j + 3;
				}
				else if (board[i][j] == challenger // down right 2 space 1 for second row
						&& board[i + 1][j + 1] == challenger
						&& board[i + 3][j + 3] == challenger
						&& board[i + 2][j + 2] == GameBoard.EMPTY) {
						move = j + 2;
				}
				else if (board[i][j] == challenger // down right 1 space 2 for second row
						&& board[i + 2][j + 2] == challenger
						&& board[i + 3][j + 3] == challenger
						&& board[i + 1][j + 1] == GameBoard.EMPTY) {
						move = j + 1;
				}
			}
		}
		
		for (int i = 0; i < board.length - 4; i++) { 
			for (int j = 3; j < board[0].length; j++) {
				if (board[i][j] == challenger // down left 3 in a row for top row
						&& board[i + 1][j - 1] == challenger
						&& board[i + 2][j - 2] == challenger
						&& board[i + 4][j - 3] != GameBoard.EMPTY
						&& board[i + 3][j - 3] == GameBoard.EMPTY) {
						move = j - 3;
				}
				else if (board[i][j] == challenger // down left 2 space 1 for top row
						&& board[i + 1][j - 1] == challenger
						&& board[i + 3][j - 3] == challenger
						&& board[i + 3][j - 2] != GameBoard.EMPTY
						&& board[i + 2][j - 2] == GameBoard.EMPTY) {
						move = j - 2;
				}
				else if (board[i][j] == challenger // down left 1 space 2 for top row
						&& board[i + 2][j - 2] == challenger
						&& board[i + 3][j - 3] == challenger
						&& board[i + 2][j - 1] != GameBoard.EMPTY
						&& board[i + 1][j - 1] == GameBoard.EMPTY) {
						move = j - 1;
				}
			}
		}
		
		for (int i = 1; i < board.length - 3; i++) { 
			for (int j = 3; j < board[0].length; j++) {
				if (board[i][j] == challenger // down left 3 in a row for second row
						&& board[i + 1][j - 1] == challenger
						&& board[i + 2][j - 2] == challenger
						&& board[i + 3][j - 3] == GameBoard.EMPTY) {
						move = j - 3;
				}
				else if (board[i][j] == challenger // down left 2 space 1 for second row
						&& board[i + 1][j - 1] == challenger
						&& board[i + 3][j - 3] == challenger
						&& board[i + 2][j - 2] == GameBoard.EMPTY) {
						move = j - 2;
				}
				else if (board[i][j] == challenger // down left 1 space 2 for second row
						&& board[i + 2][j - 2] == challenger
						&& board[i + 3][j - 3] == challenger
						&& board[i + 1][j - 1] == GameBoard.EMPTY) {
						move = j - 1;
				}
			}
		}
		
		for (int i = 3; i < board.length; i++) { 
			for (int j = 0; j < board[0].length - 3; j++) {
				if (board[i][j] == challenger // up right 3 in a row
						&& board[i - 1][j + 1] == challenger
						&& board[i - 2][j + 2] == challenger
						&& board[i - 2][j + 3] != GameBoard.EMPTY
						&& board[i - 3][j + 3] == GameBoard.EMPTY) {
						move = j + 3;
				}
				else if (board[i][j] == challenger // up right 2 space 1
						&& board[i - 1][j + 1] == challenger
						&& board[i - 3][j + 3] == challenger
						&& board[i - 1][j + 2] != GameBoard.EMPTY
						&& board[i - 2][j + 2] == GameBoard.EMPTY) {
						move = j + 2;
				}
				else if (board[i][j] == challenger // up right 1 space 2
						&& board[i - 2][j + 2] == challenger
						&& board[i - 3][j + 3] == challenger
						&& board[i][j + 1] != GameBoard.EMPTY
						&& board[i - 1][j + 1] == GameBoard.EMPTY) {
						move = j + 1;
				}
			}
		}
		
		for (int i = 3; i < board.length; i++) { 
			for (int j = 3; j < board[0].length; j++) {
				if (board[i][j] == challenger // up left 3 in a row
						&& board[i - 1][j - 1] == challenger
						&& board[i - 2][j - 2] == challenger
						&& board[i - 2][j - 3] != GameBoard.EMPTY
						&& board[i - 3][j - 3] == GameBoard.EMPTY) {
						move = j - 3;
				}
				else if (board[i][j] == challenger // up left 1 space 2
						&& board[i - 2][j - 2] == challenger
						&& board[i - 3][j - 3] == challenger
						&& board[i][j - 1] != GameBoard.EMPTY
						&& board[i- 1][j- 1]   == GameBoard.EMPTY) {
						move = j - 1;
				}
				else if (board[i][j] == challenger // up left 2 space 1
						&& board[i - 1][j - 1] == challenger
						&& board[i - 3][j - 3] == challenger
						&& board[i - 1][j - 2] != GameBoard.EMPTY
						&& board[i - 2][j - 2] == GameBoard.EMPTY) {
						move = j - 2;
				}
			}
		}
		
		/*
		 * All code below is for game winning moves
		 */
		
		for (int i = 3; i < board.length; i++) { // checks for 3 in a row vertical
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == myColor
						&& board[i - 1][j] == myColor
						&& board[i - 2][j] == myColor
						&& board[i - 3][j] == GameBoard.EMPTY) {
						move = j;
				}
			}
		}

		for (int i = 0; i < board.length - 1; i++) { 
					for (int j = 0; j < board[0].length - 3; j++) {
						if (board[i][j] == myColor	// checks for 3 in a row horiz right, except for bottom row
								&& board[i][j + 1] == myColor
								&& board[i][j + 2] == myColor
								&& board[i + 1][j + 3] != GameBoard.EMPTY
								&& board[i][j + 3] == GameBoard.EMPTY) {
								move = j + 3;
						}
						else if (board[i][j] == myColor // checks for 1 space 2 horiz right, except for bottom row
								&& board[i][j + 2] == myColor
								&& board[i][j + 3] == myColor
								&& board[i][j+1] == GameBoard.EMPTY
								&& board[i + 1][j + 1] != GameBoard.EMPTY) {
								move = j + 1;
						}
						else if (board[i][j] == myColor // checks for 2 space 1 horiz right, except for bottom row
								&& board[i][j + 1] == myColor
								&& board[i][j + 3] == myColor
								&& board[i][j + 2] == GameBoard.EMPTY
								&& board[i + 1][j + 2] != GameBoard.EMPTY) {
							move = j + 2;
						}
					}
				}
		
		for (int i = board.length - 1; i < board.length; i++) { 
			for (int j = 0; j < board[0].length - 3; j++) {
				if (board[i][j] == myColor	// checks for 3 in a row horiz right for bottom row
						&& board[i][j + 1] == myColor
						&& board[i][j + 2] == myColor
						&& board[i][j + 3] == GameBoard.EMPTY) {
						move = j + 3;
				}
				else if (board[i][j] == myColor // checks for 1 space 2 horiz right for bottom row
						&& board[i][j + 2] == myColor
						&& board[i][j + 3] == myColor
						&& board[i][j+1] == GameBoard.EMPTY) {
						move = j + 1;
				}
				else if (board[i][j] == myColor // checks for 2 space 1 horiz right for bottom row
						&& board[i][j + 1] == myColor
						&& board[i][j + 3] == myColor
						&& board[i][j + 2] == GameBoard.EMPTY) {
					move = j + 2;
				}
			}
		}
		
		for (int i = 0; i < board.length - 1; i++) { 
			for (int j = 3; j < board[0].length; j++) { 
				if (board[i][j] == myColor // checks for 3 in a row horiz left, except for bottom row
						&& board[i][j - 1] == myColor
						&& board[i][j - 2] == myColor
						&& board[i][j - 3] == GameBoard.EMPTY
						&& board[i + 1][j - 3] != GameBoard.EMPTY) {
						move = j - 3;
				}
				else if (board[i][j] == myColor // checks for 1 space 2 horiz left, except for bottom row
						&& board[i][j - 2] == myColor
						&& board[i][j - 3] == myColor
						&& board[i][j - 1] == GameBoard.EMPTY		
						&& board[i + 1][j - 1] != GameBoard.EMPTY) {
						move = j - 1;
				}
				else if (board[i][j] == myColor // checks for 2 space 1 horiz left, except for bottom row
						&& board[i][j - 1] == myColor
						&& board[i][j - 3] == myColor
						&& board[i][j - 2] == GameBoard.EMPTY
						&& board[i + 1][j - 2] != GameBoard.EMPTY) {
						move = j - 2;
				}
			}
		}
		
		for (int i = board.length - 1; i < board.length; i++) { 
			for (int j = 3; j < board[0].length; j++) { 
				if (board[i][j] == myColor // checks for 3 in a row horiz left for bottom row
						&& board[i][j - 1] == myColor
						&& board[i][j - 2] == myColor
						&& board[i][j - 3] == GameBoard.EMPTY) {
						move = j - 3;
				}
				else if (board[i][j] == myColor // checks for 1 space 2 horiz left for bottom row
						&& board[i][j - 2] == myColor
						&& board[i][j - 3] == myColor
						&& board[i][j - 1] == GameBoard.EMPTY		) {
						move = j - 1;
				}
				else if (board[i][j] == myColor // checks for 2 space 1 horiz left for bottom row
						&& board[i][j - 1] == myColor
						&& board[i][j - 3] == myColor
						&& board[i][j - 2] == GameBoard.EMPTY) {
						move = j - 2;
				}
			}
		}
		
		for (int i = 0; i < board.length - 4; i++) { 
			for (int j = 0; j < board[0].length - 3; j++) {
				if (board[i][j] == myColor // down right 3 in a row for top row
						&& board[i + 1][j + 1] == myColor
						&& board[i + 2][j + 2] == myColor
						&& board[i + 4][j + 3] != GameBoard.EMPTY
						&& board[i + 3][j + 3] == GameBoard.EMPTY) {
						move = j + 3;
				}
				else if (board[i][j] == myColor // down right 2 space 1 for top row
						&& board[i + 1][j + 1] == myColor
						&& board[i + 3][j + 3] == myColor
						&& board[i + 3][j + 2] != GameBoard.EMPTY
						&& board[i + 2][j + 2] == GameBoard.EMPTY) {
						move = j + 2;
				}
				else if (board[i][j] == myColor // down right 1 space 2 for top row
						&& board[i + 2][j + 2] == myColor
						&& board[i + 3][j + 3] == myColor
						&& board[i + 2][j + 1] != GameBoard.EMPTY
						&& board[i + 1][j + 1] == GameBoard.EMPTY) {
						move = j + 1;
				}
			}
		}
		
		for (int i = 1; i < board.length - 3; i++) { 
			for (int j = 0; j < board[0].length - 3; j++) {
				if (board[i][j] == myColor // down right 3 in a row for second row
						&& board[i + 1][j + 1] == myColor
						&& board[i + 2][j + 2] == myColor
						&& board[i + 3][j + 3] == GameBoard.EMPTY) {
						move = j + 3;
				}
				else if (board[i][j] == myColor // down right 2 space 1 for second row
						&& board[i + 1][j + 1] == myColor
						&& board[i + 3][j + 3] == myColor
						&& board[i + 2][j + 2] == GameBoard.EMPTY) {
						move = j + 2;
				}
				else if (board[i][j] == myColor // down right 1 space 2 for second row
						&& board[i + 2][j + 2] == myColor
						&& board[i + 3][j + 3] == myColor
						&& board[i + 1][j + 1] == GameBoard.EMPTY) {
						move = j + 1;
				}
			}
		}
		
		for (int i = 0; i < board.length - 4; i++) { 
			for (int j = 3; j < board[0].length; j++) {
				if (board[i][j] == myColor // down left 3 in a row for top row
						&& board[i + 1][j - 1] == myColor
						&& board[i + 2][j - 2] == myColor
						&& board[i + 4][j - 3] != GameBoard.EMPTY
						&& board[i + 3][j - 3] == GameBoard.EMPTY) {
						move = j - 3;
				}
				else if (board[i][j] == myColor // down left 2 space 1 for top row
						&& board[i + 1][j - 1] == myColor
						&& board[i + 3][j - 3] == myColor
						&& board[i + 3][j - 2] != GameBoard.EMPTY
						&& board[i + 2][j - 2] == GameBoard.EMPTY) {
						move = j - 2;
				}
				else if (board[i][j] == myColor // down left 1 space 2 for top row
						&& board[i + 2][j - 2] == myColor
						&& board[i + 3][j - 3] == myColor
						&& board[i + 2][j - 1] != GameBoard.EMPTY
						&& board[i + 1][j - 1] == GameBoard.EMPTY) {
						move = j - 1;
				}
			}
		}
		
		for (int i = 1; i < board.length - 3; i++) { 
			for (int j = 3; j < board[0].length; j++) {
				if (board[i][j] == myColor // down left 3 in a row for second row
						&& board[i + 1][j - 1] == myColor
						&& board[i + 2][j - 2] == myColor
						&& board[i + 3][j - 3] == GameBoard.EMPTY) {
						move = j - 3;
				}
				else if (board[i][j] == myColor // down left 2 space 1 for second row
						&& board[i + 1][j - 1] == myColor
						&& board[i + 3][j - 3] == myColor
						&& board[i + 2][j - 2] == GameBoard.EMPTY) {
						move = j - 2;
				}
				else if (board[i][j] == myColor // down left 1 space 2 for second row
						&& board[i + 2][j - 2] == myColor
						&& board[i + 3][j - 3] == myColor
						&& board[i + 1][j - 1] == GameBoard.EMPTY) {
						move = j - 1;
				}
			}
		}
		
		for (int i = 3; i < board.length; i++) { 
			for (int j = 0; j < board[0].length - 3; j++) {
				if (board[i][j] == myColor // up right 3 in a row
						&& board[i - 1][j + 1] == myColor
						&& board[i - 2][j + 2] == myColor
						&& board[i - 2][j + 3] != GameBoard.EMPTY
						&& board[i - 3][j + 3] == GameBoard.EMPTY) {
						move = j + 3;
				}
				else if (board[i][j] == myColor // up right 2 space 1
						&& board[i - 1][j + 1] == myColor
						&& board[i - 3][j + 3] == myColor
						&& board[i - 1][j + 2] != GameBoard.EMPTY
						&& board[i - 2][j + 2] == GameBoard.EMPTY) {
						move = j + 2;
				}
				else if (board[i][j] == myColor // up right 1 space 2
						&& board[i - 2][j + 2] == myColor
						&& board[i - 3][j + 3] == myColor
						&& board[i][j + 1] != GameBoard.EMPTY
						&& board[i - 1][j + 1] == GameBoard.EMPTY) {
						move = j + 1;
				}
			}
		}
		
		for (int i = 3; i < board.length; i++) { 
			for (int j = 3; j < board[0].length; j++) {
				if (board[i][j] == myColor // up left 3 in a row
						&& board[i - 1][j - 1] == myColor
						&& board[i - 2][j - 2] == myColor
						&& board[i - 2][j - 3] != GameBoard.EMPTY
						&& board[i - 3][j - 3] == GameBoard.EMPTY) {
						move = j - 3;
				}
				else if (board[i][j] == myColor // up left 1 space 2
						&& board[i - 2][j - 2] == myColor
						&& board[i - 3][j - 3] == myColor
						&& board[i][j - 1] != GameBoard.EMPTY
						&& board[i- 1][j- 1]   == GameBoard.EMPTY) {
						move = j - 1;
				}
				else if (board[i][j] == myColor // up left 2 space 1
						&& board[i - 1][j - 1] == myColor
						&& board[i - 3][j - 3] == myColor
						&& board[i - 1][j - 2] != GameBoard.EMPTY
						&& board[i - 2][j - 2] == GameBoard.EMPTY) {
						move = j - 2;
				}
			}
		}
		return move;
	}
}
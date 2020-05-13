
/*
 * Author: Zachary Serotte
 */
	
	public class SequentialPlayer extends Player {
		public SequentialPlayer() {
			myName = "Sequential";
		}
		
		public int ai(GameBoard gb) {
			int[][] board = gb.getBoard();
			int goodMove = 0;
			
			for (int i = 0; i < board.length; i++) {
				if (gb.isLegalMove(i) == true) {
					goodMove = i;
					break;
				}
			}
			return goodMove;
		}
	}


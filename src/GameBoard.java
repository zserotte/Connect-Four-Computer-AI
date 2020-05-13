
/*
 * GameBoard.java
 *
 * @author  J. Hollingsworth
 * revised by Shannon Duvall
 *
 * A two-dimensional gameboard for Connect-Four.
 */
import java.awt.*;

public class GameBoard
{
   /*
    * Define possible values for each cell in the board.
    * These constants are declared static so that they 
    * can be accessed anywhere in the game with 
    * GameBoard.EMPTY, etc.
    */
   public static final int EMPTY = 0;
   public static final int RED = 1;
   public static final int BLACK = 2;

   /*
    * Define the canvas size and spacing inside a cell.
    */
   private final int CANVASX = 400;
   private final int CANVASY = 400;
   private final int OFFSET = 6;

   /*
    * The game board.
    */
   private int [][] board;
   /*
    * The last spot clicked on the applet
    */
   private Point lastClicked;
   
   /* Setters and getters
    */
   public Point getLastClicked(){
   	return lastClicked;
   }
   
   public void setLastClicked(Point p){
   	lastClicked = p;
   }
   
   public int[][] getBoard(){
   	return board;
   }

   public GameBoard clone(){
	   GameBoard clone = new GameBoard(board.length, board[0].length);
	   for(int i=0; i<board.length; i++){
		   for(int j=0; j<board[0].length; j++){
			   clone.board[i][j] = this.board[i][j];
		   }
	   }
	   clone.setLastClicked(this.getLastClicked());
	   return clone;
   }
   
   /*
    * Constructor:
    * Creates a width x height game board initialized to EMPTY.
    */
   GameBoard(int width, int height){
      board = new int[width][height];
    
      for (int row=0; row<width; row++)
         for (int col=0; col<height; col++)
            board[row][col] = EMPTY;
   }

	/*
	 * Used to reset the board to empty
	 */
	public void reset(){
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board[0].length; col++){
				board[row][col]= EMPTY;
			}
		}
	}

   /*
    * Displays a graphical version of the current game board.
    */
   public void draw(Graphics page){
      // width and height of each cell
      int cellx = CANVASX/board[0].length;
      int celly = CANVASY/board.length;
      int radius;

      // compute radius based on smallest value
      if (cellx > celly)
         radius = celly - OFFSET;
      else 
         radius = cellx - OFFSET;

      // amount to move over in the cell
      int shiftx = (cellx - radius)/2;
      int shifty = (celly - radius)/2;

      // loop through all cells in the board
      for (int row=0; row<board.length; row++)
         for (int col=0; col<board[0].length; col++)
         {
            // draw the background board
            page.setColor(Color.yellow);
            page.fillRect(col*cellx, row*celly, cellx, celly);
            page.setColor(Color.black);
            page.drawRect(col*cellx, row*celly, cellx, celly);
            page.drawLine(col*cellx+(cellx/2), row*celly+(celly/2), col*cellx, row*celly);
            page.drawLine(col*cellx+(cellx/2), row*celly+(celly/2), col*cellx, row*celly+celly);
            page.drawLine(col*cellx+(cellx/2), row*celly+(celly/2), col*cellx+cellx, row*celly);
            page.drawLine(col*cellx+(cellx/2), row*celly+(celly/2), col*cellx+cellx, row*celly+celly);

            if (board[row][col] == RED)        // place a red checker row,col cell
            {
               page.setColor(Color.red);
               page.fillOval(col*cellx+shiftx, row*celly+shifty, radius, radius);
            }
            else if (board[row][col] == BLACK) // place a black checker in row,col cell
            {
               page.setColor(Color.black);
               page.fillOval(col*cellx+shiftx, row*celly+shifty, radius, radius);
            } 
            else                               // no checker, draw the background hole
            {
               page.setColor(Color.gray);
               page.fillOval(col*cellx+shiftx, row*celly+shifty, radius, radius);
            }
         }
   }

   /*
    * Accepts a column and marking color. Makes the mark
    * in the column based on the highest numbered row -
    * due to gravity. Returns true is the mark was made,
    * otherwise returns false (column full, or invalid column).
    */
   public boolean drop(int col, int mark)
   {
   	  if(!isLegalMove(col)) 
   	  	return false;	
      for (int row=board.length-1; row>=0; row--)
         if (board[row][col] == EMPTY)
         {
            board[row][col] = mark;
            return true;
         }

      return false;
   }
   
   /*
    * Accepts a column number.  Returns true if that move 
    * is valid.  That is, the column is within the array 
    * bounds and there's an empty spot in that column.
    * Otherwise, it returns false.
    */
   public boolean isLegalMove(int col){
   		if ((col<0)||(col>=board[0].length)){
   			return false;
   		}
		for (int row=board.length-1; row>=0; row--){
			 if (board[row][col] == EMPTY){
				return true;
			 }
		}
		return false;
   }

   /*
	* Returns a column based on the given point.
	*/
   public int getColumn(Point pt)
   {
	  return (pt.x/(CANVASY/board[0].length));
   }
   
   /* 
    * Print board to console, for debugging only
    */
   public void printBoard(){
	   for(int i=0;i<board.length;i++){
		   for(int j=0; j<board[i].length; j++){
			   System.out.print(board[i][j]);
		   }
		   System.out.println();
	   }
   }
}

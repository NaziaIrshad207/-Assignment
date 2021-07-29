import java.util.Scanner;
public class ca
{

	private void test(int nit)
	{
		
		int a,b,i,j;
		//Enter the cell constraints
		Scanner sc = new Scanner(System.in);
			System.out.println("Enter the rows");
			a=sc.nextInt();
			
			if (a>=100)	
			 	{
				System.out.println("Max Limit is 100");
			 	}
			
			System.out.println("Enter the columns");			
			b=sc.nextInt();
			
			if(b>=100)
			 	{
				 System.out.println("Max Limit is 100");
			 	}
			int board [][] = new int[a][b];
					  
			 System.out.println("Rows= "+a);
			 System.out.println("Columns= "+b); 
			 System.out.println("Attention!!!!!!!!!,"
			 		+ "Welcome,Please enter the values in either 0 or in 1,since 0 represents DEAD state and 1 represents LIVE state!!!!!!");
			 //Enter cell values
			 System.out.println("Enter the elements for the cells: ");
			 
			 //Display cell values		 	 
			 for (i = 0; i < a; i++)	 
				 for (j = 0; j < b; j++)   	 
					 board[i][j] = sc.nextInt();
			 
			 System.out.println("Elements of the cells are: ");   	 
			 for (i = 0; i < a; i++)  	 
			 	{   
				 
				 for (j = 0; j < b; j++)  
					 System.out.print(board[i][j] + " ");
				 System.out.println();  
				 
			 	}
			 for (int t = 0 ; t < nit ; t++) {
		            System.out.println();
		            board = getNextBoard(board);
		            printBoard(board);
		        }
	}
	//Method to print board to display cell values
	private void printBoard(int[][] board) {

        for (int i = 0, e = board.length ; i < e ; i++) {

            for (int j = 0, f = board[i].length ; j < f ; j++) {
                System.out.print(Integer.toString(board[i][j]) + ",");
            } 
            System.out.println();
        }
    }
	
	private int getLiveNeighbours(final int cellRow, final int cellCol, final int[][] board) {

        int liveNeighbours = 0;
        int rowEnd = Math.min(board.length , cellRow + 2);
        int colEnd = Math.min(board[0].length, cellCol + 2);

        for (int row = Math.max(0, cellRow - 1) ; row < rowEnd ; row++) {
            
            for (int col = Math.max(0, cellCol - 1) ; col < colEnd ; col++) {
                
                //exclude the cell itself from calculation
                if ((row != cellRow || col != cellCol) && board[row][col] == 1) {
                    liveNeighbours++;
                }
            }
        }
        return liveNeighbours;
    }
	
	
	private int getNewCellState(int curState, int liveNeighbours) {

        int newState = curState;

        switch (curState) {
        case 1:

            // Any live cell with fewer than two live neighbours dies as if by loneliness
            
            if (liveNeighbours < 2) {
                newState = 0;
            }

            // Any live cell with two or three live neighbours lives,unchanged, to the next generation.  
            if (liveNeighbours == 2 || liveNeighbours == 3) {
                newState = 1;
            }

            // Any live cell with more than three live neighbours dies, as if by overcrowding.           
            if (liveNeighbours > 3) {
                newState = 0;
            }
            break;

        case 0:
            // Any dead cell with exactly three live neighbours comes to life.
            if (liveNeighbours == 3) {
                newState = 1;
            }
            break;

        default:
            throw new IllegalArgumentException("State of cell must be either LIVE or DEAD ie. it should be either 0 or 1");
        }			
        return newState;
    }
	
	public int[][] getNextBoard(int[][] board) {

        // The board does not have any values so return the newly created playing field.
     
        if (board.length == 0 || board[0].length == 0) {
            throw new IllegalArgumentException("Board must have a positive amount of rows and/or columns");
        }

        int nrRows = board.length;
        int nrCols = board[0].length;

        // Temporary board to store new values
        int[][] buf = new int[nrRows][nrCols];

        for (int row = 0 ; row < nrRows ; row++) {
	
            for (int col = 0 ; col < nrCols ; col++) {
                buf[row][col] = getNewCellState(board[row][col], getLiveNeighbours(row, col, board));
            }
        }   
        return buf;
    }
	//Main function
	public static void main(String[] args)
	{
		int e;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);	
		ca d = new ca();
		d.test(5);
		System.out.println("Press 0 to exit: ");
		e=sc.nextInt();
		if (e==0)
		{
			System.out.println("The Program Is Terminated"); 
			System.exit(e);
		
		}
				
	}
	
	
}

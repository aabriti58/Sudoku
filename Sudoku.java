import java.util.*; 

public class Sudoku { 

	static int[][] board = new int[9][9]; 

	static int zeroes = 0; //to count zeroes

	static int row = 0, col = 0; 

	static Scanner input = new Scanner(System.in); 

	public static void main(String[] args) 

	{ 

		//do-while loop will help to read the input as sequential boards until we have 9*9 of zeroes 

		do  

		{ 

			readBoard(); 

			if(zeroes <= 3) 

			{ 

				solveBoard(); 

			} 

		}while(zeroes < 81); //code will display "END" if 81 zeroes are found

		System.out.println("END"); 

	} 

	//to read 9*9 boards 

	public static void readBoard() 

	{ 

		zeroes = 0; // to count number of zeroes in board 

		for(row=0; row<9; row++) //for each row 

		{ 

			for(col=0; col<9; col++) //for each column  

			{ 

				board[row][col] = input.nextInt(); 

				if(board[row][col] == 0) 

				{ 

					zeroes++; //will increment the values of zero when value zero is found in the board 

				} 

			} 

		} 

	} 


	//using solveBoard() method to solve the board by finding the missing rows and columns
	//and using findMissing() to find the missing values
	public static void solveBoard() 

	{ 

		while(zeroes > 0) 

		{ 

			//for Type-1 when we have only one zero in the board 

			if(zeroes == 1) 

			{ 

				for(row=0; row<9; row++) 

				{ 

					for(col=0; col<9; col++) 

					{ 

						if(board[row][col] == 0) 

						{ 

							board[row][col] = findMissing(board[row]); //findMissing() method will be called to help find out row, column of missing value and missing value ifself 

							//System.out.print("\n"); 

							System.out.print("(" + row + "," + col + "," + board[row][col] + ")"); 

							System.out.print("\n"); 

							zeroes --; 

						} 

					} 

				} 

			} 

			//for Type-2 when we have two zeroes in the board 

			else if(zeroes == 2) 

			{ 

				for(int row =0; row<9; row++) 

				{ 

					for(int col=0; col<9; col++) 

					{ 

						if(board[row][col] == 0) //first zero 

						{ 

							if(col != 8) //if it is not the last column, code will be proceed 

							{ 

								if(board[row][col+1] == 0) //for two missing values in same row 

								{ 

									int missingNum = 0; 

									int[] column = new int[] {board[0][col], board[1][col], 

											board[2][col], board[3][col], board[4][col], board[5][col], 

											board[6][col], board[7][col], board[8][col]}; 

									missingNum = findMissing(column); 

									board[row][col] = missingNum; 

									System.out.print("(" + row + "," + col + "," + missingNum + ") "); 

									col++; // incrementing column to find the second missing value next to it 

									missingNum = findMissing(new int[]{board[0][col], board[1][col], 

											board[2][col], board[3][col], board[4][col], board[5][col], 

											board[6][col], board[7][col], board[8][col]}); 

									board[row][col] = missingNum;  

									System.out.print("(" + row + "," + col + "," + missingNum + ")\n"); 

									zeroes -=2; 

								} 

								else if(board[row+1][col] == 0) //for two missing value in same column 

								{ 

									int missingNum = 0; 

									int[] rows = new int[] {board[row][0], board[row][1], board[row][2], 

											board[row][3],board[row][4], board[row][5],  

											board[row][6], board[row][7], board[row][8]}; 

									missingNum = findMissing(rows); 

									board[row][col] = missingNum; 

									System.out.print("(" + row + "," + col + "," + missingNum + ") "); 

									row++; 

									missingNum = findMissing(new int[]{board[row][0], board[row][1], board[row][2], 

											board[row][3],board[row][4], board[row][5],  

											board[row][6], board[row][7], board[row][8]}); 

									board[row][col] = missingNum;  

									System.out.print("(" + row + "," + col + "," + missingNum + ")\n"); 

									zeroes -= 2; 

								} 

							} 

						} 

					} 

				} 

			} 

			else if(zeroes == 3) { 

				int[][] threeByCounts = new int[9][3]; //putting 3*3 box into 9*3 box 

				int whichbox = 0; 

				for(int r=0; r<9; r++) 

				{ 

					for(int c=0; c<9; c++) 

					{ 

						if(board[r][c] == 0 ) 

						{ 

							whichbox = (r/3)*3 + (c/3); 

							threeByCounts[whichbox][0]++; 

							threeByCounts[whichbox][1] = r; 

							threeByCounts[whichbox][2] = c; 

						} 

					} 

				} 

				for(int i=0; i<9; i++) 

				{ 

					if(threeByCounts[i][0] == 1) 

					{ 

						row = threeByCounts[i][1]; 

						col = threeByCounts[i][2]; 

						int startrow = (row/3) * 3; 

						int startcol = (col/3) * 3; 

						int[] val = new int[] {board[startrow+0][startcol],board[startrow+0][startcol+1],board[startrow+0][startcol+2], 

								board[startrow+1][startcol],board[startrow+1][startcol+1],board[startrow+1][startcol+2], 

								board[startrow+2][startcol],board[startrow+2][startcol+1],board[startrow+2][startcol+2]}; 

						int missing = findMissing(val); 

						board[row][col] = missing; 

						System.out.print("(" + row + "," + col + "," + board[row][col] + ") "); 

						zeroes--; 

					} 

				} 

			} 

		} 

	} 

	public static int findMissing(int[] A) 

	{ 

		//A is the array of int that will return int value between 1-8 

		boolean[] found = new boolean[10]; 

		for(int i=0; i<9; i++) 

		{ 

			found[A[i]] = true; 

		} 

		for(int i=1; i<10; i++) 

		{ 

			if(!found[i]) 

			{ 

				return i; 

			} 

		} 

		return -1; 

	} 

} 





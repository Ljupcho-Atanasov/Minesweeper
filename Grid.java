import java.util.Arrays;
import java.util.Random;

public class Grid {
	
	private boolean [][] bombGrid; 
	private  int [][] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;
//-------------------HELPER METHODS-------------------------------------------------------------------	
	private void addOneBomb (boolean [][] grid) {  	
		Random rand = new Random ();
		int rIndex;
		int cIndex;
		while (true) {
			 rIndex = rand.nextInt(grid.length);
			 cIndex = rand.nextInt(grid[0].length);
		
			while (grid[rIndex][cIndex]) {
				rIndex = rand.nextInt(grid.length);
				cIndex = rand.nextInt(grid[0].length);
			}
				grid[rIndex][cIndex] = true;
				break;
			}
		}
	
	private void createBombGrid() {					
	for (int i = 0; i < this.bombGrid.length; ++i) {
			for (int j = 0; j < this.bombGrid[i].length; ++j) {
				this.bombGrid[i][j] = false;
			}
		}
		for (int i = 0; i < this.numBombs; ++i) {
			this.addOneBomb(this.bombGrid);
		}
	}
	
	public int countOneCell (boolean[][] bombGrid, int r, int c) {
		int count = 0;
		
		for (int i = r-1; i <= r+1; i++) {
			for (int j = c-1; j <= c+1; j++) {
				try {
					if (bombGrid[i][j]){
						++count;
					}
				}	 catch (Exception e) {}
			}
		}
		return count;
	}
	
	public  void createCountGrid() { 
		for (int i = 0; i < this.countGrid.length; i++) {
			for (int j = 0; j < this.countGrid[i].length; j++) {
				this.countGrid[i][j] = this.countOneCell(bombGrid, i, j);
				
			}
		}
	}
//-------------------CONSTRUCTORS-----------------------------------------------------------------------
	public Grid() {							
	this.numRows = 10;
	this.numColumns = 10;
	this.numBombs = 25;
	this.bombGrid = new boolean[10][10];
	this.countGrid = new int [10][10];
	this.createBombGrid();
	this.createCountGrid();
	}
	
	public Grid (int rows, int columns) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = 25;
		this.bombGrid = new boolean[this.numRows][this.numColumns];
		this.countGrid = new int [this.numRows][this.numColumns];
		this.createBombGrid();
		this.createCountGrid();
	}
	
	public Grid (int rows, int columns, int numBombs) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = numBombs;
		this.bombGrid = new boolean[this.numRows][this.numColumns];
		this.countGrid = new int [this.numRows][this.numColumns];
		this.createBombGrid();
		this.createCountGrid();
	}
//-----------------------GETTERS AND SETTERS-----------------------------------------------------------
	public int getNumRows() {
		return this.numRows;
	}
	
	public int getNumColumns() {
		return this.numColumns;
	}
	
	public int getNumBombs() {
		return this.numBombs;
	}
	
	public boolean[][] getBombGrid() {
		boolean[][] temp = new boolean [bombGrid.length][bombGrid[0].length];
		for (int i = 0; i < bombGrid.length; ++i) {
			for (int j = 0; j < bombGrid[i].length; ++j) {
				temp [i][j] = this.bombGrid [i][j];
			}
		}
		return temp;
	}
	
	public int [][] getCountGrid() {
		int [][] temp = new int [countGrid.length][countGrid[0].length];
		for (int i = 0; i < countGrid.length; ++i) {
			for (int j = 0; j < countGrid[i].length; ++j) {
				temp [i][j] = this.countGrid[i][j];
			}
		}
		return temp;
	}
//----------------------------------local methods------------------------------------------------------
	public boolean isBombAtLocation(int row, int column) {
			return this.bombGrid[row][column];
	}
	
	public int getCountAtLocation(int row, int column) {
			return this.countGrid[row][column];
	}
	public void printBombGrid(boolean [][] grid) {
		for (int i = 0; i < grid.length; ++i) {
			System.out.println(Arrays.toString(grid[i]));
		}
	}
	public void printCountGrid(int [][] grid) {
		for (int i = 0; i < grid.length; ++i) {
				System.out.println(Arrays.toString(grid[i]));
		}
	}
/*	public static void main (String [] args) {
		Grid g = new Grid ();
		g.printBombGrid(g.bombGrid);
		g.printCountGrid(g.countGrid);
	//	System.out.println(countOneCell (g.bombGrid, 0, 0));
	//	System.out.println(countOneCell (g.bombGrid, 1, 1));
	//	System.out.println(countOneCell (g.bombGrid, 2, 2));
	//	System.out.println(countOneCell (g.bombGrid, 3, 3));
		
	}				*/

}

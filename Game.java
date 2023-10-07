import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

public class Game extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton[][] gameBoard;
	private GameInfo gameInfo;
	private Grid grid;

	public Game(int rows, int columns, int numBombs) {
		gameInfo = new GameInfo();
		grid = new Grid(rows, columns, numBombs);

		// Iterate through gameBoard, create each button
		gameBoard = new JButton[rows][columns];

		// Use GridLayout(rows, columns)
		this.setLayout(new GridLayout(rows, columns));

		for (int r = 0; r < gameBoard.length; r++) {
			for (int c = 0; c < gameBoard[r].length; c++) {
				gameBoard[r][c] = new JButton(" ");
				gameBoard[r][c].setFont(new Font("mono spaced", Font.BOLD, 15));
				this.add(gameBoard[r][c]);
				gameBoard[r][c].addActionListener(this);
			}
		}
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int r;
		int c;
		int numB;

		System.out.println("Enter the difficulty speciffications: ");
		System.out.println("***************************************************************************************");
		System.out.println("Enter the number of rows: ");
		r = scnr.nextInt();
		System.out.println("Enter the number of columns: ");
		c = scnr.nextInt();
		System.out.println("Enter the number of bombs: ");
		numB = scnr.nextInt();

		Game window = new Game(r, c, numB);

//		window.pack();
		window.setSize(950, 950);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		scnr.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{

		// Find out the row and column index of the button being clicked.
		// e.getSource() -> the object that triggers this event
		int rowClicked = -1;
		int colClicked = -1;
		int num;
		int clickCount = 0;
		String message = "";
//		++clickCount;

		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				if (gameBoard[row][col].equals(e.getSource())) {
					rowClicked = row;
					colClicked = col;
				}
			}
		}

		if (grid.isBombAtLocation(rowClicked, colClicked)) 
		{
			gameBoard[rowClicked][colClicked].setText("X");
			gameInfo.setGameState("Player looses");
			gameInfo.gameOver(); // NEED DIALOG BOX
			message = "Player lost!";
			int response = JOptionPane.showConfirmDialog(this, message + "\n New game?", "Game Over",
					JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.NO_OPTION) {
				System.exit(0);

			} 
			else 
			{
				gameInfo.gameOver();
				updateView();
			}
		} 
		else 
		{
			while (!grid.isBombAtLocation(rowClicked, colClicked) 
			&& ((clickCount + grid.getNumBombs()) <= (grid.getNumRows() * grid.getNumColumns()))) 
			{
				
// ENTER REVEAL METHOD :				
				
				num = grid.countOneCell(grid.getBombGrid(), rowClicked, colClicked);
				gameBoard[rowClicked][colClicked].setText("" + num);
				clickCount += revealZeros(rowClicked, colClicked);
				
				++clickCount;
				System.out.println(clickCount);
				
				
				if (clickCount + grid.getNumBombs() == grid.getNumRows() * grid.getNumColumns()) 
				{
					gameBoard[rowClicked][colClicked].setText("" + num);
					gameInfo.setGameState("Player wins");
					gameInfo.gameOver();					//
					message = "Player wins the game!";
  			    if (!gameInfo.getGameState().equals("Not Finished")) 	
				    {

						if (gameInfo.getGameState().equals("Player wins")) 
						{
							message = "Player wins the game!";
							gameInfo.gameOver();
						}
//						if (clickCount + grid.getNumBombs() == grid.getNumRows() * grid.getNumColumns()) {

						int response = JOptionPane.showConfirmDialog(this, message + "\n New game?", "Game Over",
								JOptionPane.YES_NO_OPTION);
						if (response == JOptionPane.NO_OPTION) 
						{
							System.exit(0);
//							break;
						} 
						else 
						{
							gameInfo.gameOver();

							updateView();
						}
					}
				}
		}

 /*                    if (!gameInfo.getGameState().equals("Not Finished")) {

						if (gameInfo.getGameState().equals("Player wins")) {
							message = "Player wins the game!";
						}

						int response = JOptionPane.showConfirmDialog(this, message + "\n New game?", "Game Over",
								JOptionPane.YES_NO_OPTION);
						if (response == JOptionPane.NO_OPTION) {
							System.exit(0);
						} else {
							gameInfo.gameOver();

							updateView();
						}
					}		*/
				}
	}
	

	private void updateView() {
		grid = new Grid(gameBoard.length, gameBoard[0].length, grid.getNumBombs());
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {

				gameBoard[row][col].setText("");
			}
		}
	}
	
	private int revealZeros(int r, int c) /////////////////////////////////////////////////
	{
		int count = 0;
		if (grid.countOneCell(grid.getBombGrid(), r, c) == 0) 
		{
		for (int i = r - grid.getNumRows(); i <= r + grid.getNumRows(); i++) 
		{
			for (int j = c - grid.getNumColumns(); j <= c + grid.getNumColumns(); j++) 
			{
				try 
				{
					if (!grid.isBombAtLocation(i, j) && ((grid.countOneCell(grid.getBombGrid(), i, j) == 0) ||
							                             (grid.countOneCell(grid.getBombGrid(), i, j) == 1) ||
							                             (grid.countOneCell(grid.getBombGrid(), i, j) == 2)))
					{            
						gameBoard[i][j].setText("" + grid.countOneCell(grid.getBombGrid(), i, j));
						
						++count;
					}
					
				} catch (Exception e) {	}
//				if (grid.countOneCell(grid.getBombGrid(), i, j) == 0) revealZeros(i, j);
			}
		}
		}
		return count;
	}
	
}
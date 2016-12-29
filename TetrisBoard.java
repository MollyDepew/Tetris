/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * A Pane in which tetris squares can be displayed.
 * 
 * @author pipWolfe
 */
public class TetrisBoard extends Pane {
	private int count;
	private int row;
	private int score = 0;
	private int col;
	private int completeRow;

	// The size of the side of a tetris square
	public static final int SQUARE_SIZE = 20;
	// The number of squares that fit on the screen in the x and y dimensions
	public static final int X_DIM_SQUARES = 20;
	public static final int Y_DIM_SQUARES = 30;

	/**
	 * Sizes the board to hold the specified number of squares in the x and y
	 * dimensions.
	 */
	public TetrisBoard() {
		this.setPrefHeight(Y_DIM_SQUARES * SQUARE_SIZE);
		this.setPrefWidth(X_DIM_SQUARES * SQUARE_SIZE);
		BackgroundFill myBF = new BackgroundFill(Color.WHITE, new CornerRadii(1), new Insets(0.0, 0.0, 0.0, 0.0));// or
																													// null
																													// for
																													// the
																													// padding
		setBackground(new Background(myBF));
	}
	

	/**
	 * checking one row at a time this method looks to see if the location (row,
	 * col) is null or there is a tetrisSquare in that space. if it is not null
	 * then the count increases by 1 starting at zero for every different row so
	 * when the count = the X dimension of our board we know that line is full
	 * that is when we can remove it
	 * 
	 * 
	 * @param boardSquares
	 * @return
	 */
	public int getCompleteRow(TetrisSquare[][] boardSquares) {
		for (row = 0; row < Y_DIM_SQUARES; row++) {
			count = 0;
			for (int col = 0; col < X_DIM_SQUARES; col++) {
				if (boardSquares[row][col] != null) {
					count = count + 1;
					
				}
			}
			if (count == X_DIM_SQUARES) {
					System.out.println(count);
					completeRow = row;
					//System.out.print(completeRow);
					this.removeRow(completeRow, boardSquares);
					this.refill(row,boardSquares);
					this.getScore(count);
				}
		}
			
		return count;
	}
	
	

	/**
	 * returns the score increases by one every time there is a full line
	 * detected the count = 20 is how we check that
	 * 
	 * @param count
	 * @return
	 */
	public int getScore(int count) {
		if (count == 20) {
			score = score + 1;
		}
		return score;
	}

	/**
	 * taking in the 2D array and the complete row we found this method takes
	 * the row out of the board visually and takes it out of the array by
	 * setting it to null
	 * 
	 * @param row
	 * @param boardSquares
	 */
	public void removeRow(int completeRow, TetrisSquare[][] boardSquares) {
		for (col = 0; col < X_DIM_SQUARES; col++) {
			boardSquares[completeRow][col].removeFromDrawing();
			boardSquares[completeRow][col]=null;
			
			}
	}

	public void refill(int completeRow, TetrisSquare[][] boardSquares) {
		for (row = completeRow-1 ; row > 0; row--) {
			for (col = 0; col < X_DIM_SQUARES; col++) {
				if (boardSquares[row][col] != null) {
					boardSquares[row][col].moveToTetrisLocation(col,row+1);
					}
				boardSquares[row+1][col] = boardSquares[row][col];
				boardSquares[row][col] = null;
				
			}
		}
	}

	/**
	 * this boolean method return true or false by checking the next location of
	 * the square that is put in. if it exceeds the board boundaries or the 2D
	 * array is not null meaning a square is already occupying that space. the
	 * method will return false meaning that next move can't be performed I
	 * created one for X and one for Y so that when I check my rotate I can do
	 * both
	 * 
	 * @param nextLocX
	 * @param nextLocY
	 * @param boardSquares
	 * @return
	 */
	public boolean checkMovesX(int nextLocX, int nextLocY, TetrisSquare[][] boardSquares) {
		if (nextLocX < 0 || nextLocX >= X_DIM_SQUARES || boardSquares[nextLocY][nextLocX] != null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkMovesY(int nextLocX, int nextLocY, TetrisSquare[][] boardSquares) {
		if (nextLocY < 0 || nextLocY > Y_DIM_SQUARES - 1 || boardSquares[nextLocY][nextLocX] != null) {
			return false;

		} else {
			return true;
		}
	}
	public int getYDIM(){
		return Y_DIM_SQUARES;
		
	}
}	
	

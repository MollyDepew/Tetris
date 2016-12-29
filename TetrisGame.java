/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * This should be implemented to include your game control.
 * @author pipWolfe
 */
public class TetrisGame {
    private final Tetris tetrisApp;
    private Shape squares;
	private int i;
	protected double x;
	protected int a;
	protected int b;
	protected int c;
	protected int d;
	protected int e;
	protected int f;
	private int count;
	private TetrisBoard board;

   
    /**
     * Initialize the game. Remove the example code and replace with code
     * that creates a random piece.
     * @param tetrisApp A reference to the application (use to set messages).
     * @param board A reference to the board on which Squares are drawn
     */
    public TetrisGame(Tetris tetrisApp, TetrisBoard board) {
    	squares = new Shape(board);
    	squares.makeShapes();
    	this.tetrisApp = tetrisApp;
    	
    	
    	
    }

    /**
     * Animate the game, by moving the current tetris piece down.
     */
    void update() {
    	squares.move();
    	count = squares.getCompleteRow(squares.getBoardSquares());
	
/**
 * using checkMovesY method checks to see if the move below the start position is false
 * if it is that mean the game is over
 * otherwise prints the score
 */
   	if(squares.checkMovesY(10,2,squares.getBoardSquares())== false){
        		 tetrisApp.setMessage("Game Over!");
        	 }
        	 else{
            tetrisApp.setMessage("Score " + squares.getScore(count));
        	 }
    		
    	
    }
    		
     
   
     
 

    
    /**
     * Move the current tetris piece left.
     */
    void left() {
    	squares.moveLeft();
        System.out.println("left key was pressed!");
        
    }

    /**
     * Move the current tetris piece right.
     */
    void right() {
    	squares.moveRight();
        System.out.println("right key was pressed!");
    }

    /**
     * Drop the current tetris piece.
     * sees how many times it needs to update to get to the bottom
     * and calls it that many times speeding up the falling process
     * update has the method move in it so it will automatically stop
     * when it hits a boundary or another piece
     */
    void drop() {
    	for(i=0; i< 30-squares.getY(); i++){
    		this.update();
    		
    	
    }
        System.out.println("drop key was pressed!");
    }

    /**
     * Rotate the current piece counter-clockwise.
     */
    void rotateLeft() {
    	squares.rotateLeft();
        System.out.println("rotate left key was pressed!");
    }
    
    /**
     * Rotate the current piece clockwise.
     */
    void rotateRight() {
    	squares.rotateRight();
        System.out.println("rotate right key was pressed!");
    }
    
}



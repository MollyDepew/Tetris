import java.util.Arrays;

import java.util.Random;

import javafx.scene.paint.Color;

/** This class creates the different shapes used in Tetris
 
 * @author depew
 *
 */
public class Shape extends TetrisBoard{
	protected TetrisSquare[] squares = new TetrisSquare[4];
	protected TetrisBoard board;
	protected TetrisSquare[][] boardSquares = new TetrisSquare[Y_DIM_SQUARES][X_DIM_SQUARES];
	private int i;
	protected double x;
	private int newY;
	private int a;
	private int b;
	private int c;
	private int d;
	private int e;
	private int f;
	private int currX;
	private int currY;
	private int newX;
	private int x0;
	private int y0;
	private int[] Yval = new int[4]; 
	private int[] Xval = new int[4];
	private int startLocY = 1;
	private int startLocX= 10;
	private int y;
	
	
	public Shape(TetrisBoard board){
		this.board= board;
		for (int i = 0; i < squares.length; i++) {
			squares[i] = new TetrisSquare(board);
			}
		}
	/**
	 *  by using tetrisSquares in groups of 4 in an array
	 * they're x and y coordinates are set relative to squares[0]
	 * to creates different shapes
	 */
	
	public void makeShapes(){
		for (int i = 0; i < squares.length; i++) {
			squares[i] = new TetrisSquare(board);
			squares[i].setStroke(Color.BLACK);
			}
			
		
				Random rn = new Random();
				int x = rn.nextInt(7);
				if(x==0){
					a = 0;
					b = -1;
					c = -1;
				 	d = -1;
					e = -1;
					f = 0;
					 for(i=0;i<4;i++){
						 squares[i].setColor(Color.PURPLE);
					 }
				}
				if(x==1){
					 a = 1;
					 b = -1;
					 c = 0;
					 d = -1;
					 e = 0;
					 f = 1;
					 for(i=0;i<4;i++){
					squares[i].setColor(Color.BLUE);
					 	} 
					 }
				if(x==2){
					 a = 1;
					 b = -1;
					 c = 0;
					 d = -1;
					 e = -1;
					 f = 0;
					 for(i=0;i<4;i++){
					squares[i].setColor(Color.YELLOW);
					 }
				}
				if(x==3){
					 a = 0;
					 b = -1;
					 c = -1;
					 d = -1;
					 e = 0;
					 f = 1;
					 for(i=0;i<4;i++){
					squares[i].setColor(Color.ORANGE);
					 	}
					 }
				if(x==4){
					 a = 1;
					 b = 0;
					 c = -1;
					 d = 0;
					 e = 0;
					 f = 1;
					 for(i=0;i<4;i++){
					 squares[i].setColor(Color.GREEN);
					 	}
					 }
				if(x==5){
					 a = 0;
					 b = 1;
					 c = 0;
					 d = -1;
					 e = 0;
					 f = 2;
					 for(i=0;i<4;i++){
					 squares[i].setColor(Color.RED);
					 	}
					 }
				if(x==6){
					 a = -1;
					 b = -1;
					 c = 0;
					 d = -1;
					 e = 1;
					 f =0;
					 for(i=0;i<4;i++){
					squares[i].setColor(Color.GREY);
					 }
				}
					 
				squares[0].moveToTetrisLocation(startLocX,startLocY);
				squares[1].moveToTetrisLocation(squares[0].getX()+ a, squares[0].getY()+ b);
				squares[2].moveToTetrisLocation(squares[0].getX()+ c, squares[0].getY()+ d);
				squares[3].moveToTetrisLocation(squares[0].getX()+ e, squares[0].getY()+f);
			} 


	public void move(){
				currX = squares[0].getX();
				currY = squares[0].getY();
				for(i =0; i<4; i++){
					Yval[i]  = squares[i].getY()+1;
					Xval[i] = squares[i].getX();
				}
				
					
				if(this.checkMovesY(Xval[0],Yval[0],boardSquares)== false
						|| this.checkMovesY(Xval[1],Yval[1],boardSquares)== false
						||this.checkMovesY(Xval[2],Yval[2],boardSquares)== false
						||this.checkMovesY(Xval[3],Yval[3],boardSquares)== false){
					squares[0].moveToTetrisLocation(currX, currY);
					squares[1].moveToTetrisLocation(squares[0].getX()+ a, squares[0].getY() + b);
					squares[2].moveToTetrisLocation(squares[0].getX() + c, squares[0].getY() + d);
					squares[3].moveToTetrisLocation(squares[0].getX() + e, squares[0].getY() +f);
					for(i=0;i<4;i++){
						boardSquares[squares[i].getY()][squares[i].getX()] = squares[i];
					}
					if(this.checkMovesY(startLocX, startLocY+1, boardSquares)== true){
					this.makeShapes();
					}
					}
				
				else{
				currX = squares[0].getX();
				newY = currY + 1;
				squares[0].moveToTetrisLocation(currX,newY);
				squares[1].moveToTetrisLocation(squares[0].getX()+a, squares[0].getY()+b);
				squares[2].moveToTetrisLocation(squares[0].getX()+c, squares[0].getY() + d);
				squares[3].moveToTetrisLocation(squares[0].getX()+e, squares[0].getY() +f);

				}
		}
				

		public TetrisSquare[][] getBoardSquares(){
			return boardSquares;
		}
		/**
		 * same idea at move(). if the space to the left is open for all the squares
		 * x coordinate -1 then square[0] will be move to it's current x coordinate -1
		 * and the others follow moving them relative to the new location
		 */
			
			

			public void moveLeft(){
				currX = squares[0].getX();
				currY = squares[0].getY();
				for(i =0; i<4; i++){
					Xval[i] = squares[i].getX()-1;
					Yval[i] = squares[i].getY();
					
				}
				
				if(this.checkMovesX(Xval[0],Yval[0],boardSquares)== false||
						this.checkMovesX(Xval[1],Yval[1],boardSquares)== false||
						this.checkMovesX(Xval[2],Yval[2],boardSquares)== false||
						this.checkMovesX(Xval[3],Yval[3],boardSquares)== false||
						this.checkMovesY(startLocX, startLocY+1,boardSquares)==false){
					currX = squares[0].getX();
					currY = squares[0].getY();
					squares[0].moveToTetrisLocation(currX,currY);
					squares[1].moveToTetrisLocation(squares[0].getX()+a, squares[0].getY()+b );
					squares[2].moveToTetrisLocation(squares[0].getX()+c , squares[0].getY()+d);
					squares[3].moveToTetrisLocation(squares[0].getX()+e, squares[0].getY()+f);
				}
					else{
					currX = squares[0].getX();
					currY = squares[0].getY();
					newX = currX -1;
					squares[0].moveToTetrisLocation(newX,currY);
					squares[1].moveToTetrisLocation(squares[0].getX()+a, squares[0].getY()+b );
					squares[2].moveToTetrisLocation(squares[0].getX()+c , squares[0].getY()+d);
					squares[3].moveToTetrisLocation(squares[0].getX()+e, squares[0].getY()+f);
				}

							
						}
			
			/**
			 * same moveLeft but in the other direction
			 * adding one to the x coordinates instead of subtracting 
			 */
			
			public void moveRight(){
				currX = squares[0].getX();
				currY = squares[0].getY();
			
				for(i =0; i<4; i++){
					Xval[i] = squares[i].getX()+1;
					Yval[i] = squares[i].getY();
					}
				Arrays.sort(Xval);
				Arrays.sort(Yval);
				if(this.checkMovesX(Xval[3],Yval[3],boardSquares)== false||
						this.checkMovesY(startLocX, startLocY+1,boardSquares)==false){
					currX = squares[0].getX();
					currY = squares[0].getY();
					squares[0].moveToTetrisLocation(currX,currY);
					squares[1].moveToTetrisLocation(squares[0].getX()+ a, squares[0].getY()+b);
					squares[2].moveToTetrisLocation(squares[0].getX() + c,squares[0].getY()+d);
					squares[3].moveToTetrisLocation(squares[0].getX() + e, squares[0].getY()+f);
				}
				else{
					
					currX = squares[0].getX();
					currY = squares[0].getY();
					newX = currX +1;
					squares[0].moveToTetrisLocation(newX,currY);
					squares[1].moveToTetrisLocation(squares[0].getX()+ a, squares[0].getY()+b);
					squares[2].moveToTetrisLocation(squares[0].getX() + c,squares[0].getY()+d);
					squares[3].moveToTetrisLocation(squares[0].getX() + e, squares[0].getY()+f);
					
				}
			
						}

			
				/**
				 * sets the other squares relative location to squares[0]
				 * 
				 * @param a
				 */
			public void setA(int a){
				this.a =a;
			}
			public void setB(int b){
				this.b =b;
			}
			public void setC(int c){
				this.c =c;
			}
			public void setD(int d){
				this.d =d;
				}
			public void setE(int e){
				this.e =e;
			}
			public void setF(int f){
				this.f =f;
			}
		
			/**
			 * when the rotateLeft() button is pressed the x values are changed to its y value
			 * and the y values are changed to the negative x values
			 * if these new locations are open setA, setB, setC... etc are used and we change the relative 
			 * location for these squares to square[0]
			 * if the space is not open we keep the same relative location
			 */
public void rotateLeft(){
				x0 = squares[0].getX();
				y0 = squares[0].getY();
				
				for(i =1; i<4; i++){
					Xval[i] = x0+squares[i].getY()-y0;
					Yval[i] = x0-(squares[i].getX()-x0);
				}
				Arrays.sort(Xval);
				Arrays.sort(Yval);
				
				if(this.checkMovesX(Xval[1],Yval[1],boardSquares)== false ||
						this.checkMovesX(Xval[2],Yval[2],boardSquares) == false||
						this.checkMovesX(Xval[3],Yval[3],boardSquares)== false ||
						this.checkMovesX(Xval[0],Yval[0],boardSquares)== false||
						this.checkMovesY(startLocX, startLocY+1,boardSquares)==false){
					x0 = squares[0].getX();
					y0 = squares[0].getY();
					squares[0].moveToTetrisLocation(x0,y0);
					squares[1].moveToTetrisLocation(x0+a,y0+b);
					squares[2].moveToTetrisLocation(x0+c,y0+d);
					squares[3].moveToTetrisLocation(x0+e,y0+f);
				}
				else{
					setA(squares[1].getY()-y0);
					setB(-(squares[1].getX()-x0));
					setC(squares[2].getY()-y0);
					setD(-(squares[2].getX()-x0));
					setE(squares[3].getY()-y0);
					setF(-(squares[3].getX()-x0));
					
					squares[0].moveToTetrisLocation(x0,y0);
					squares[1].moveToTetrisLocation(x0+a,y0+b);
					squares[2].moveToTetrisLocation(x0+c,y0+d);
					squares[3].moveToTetrisLocation(x0+e,y0+f);
				}
				
			
				}
			/**
			 * same logic as rotateLeft except the new x value is the negative y value
			 * and the new y value is the x value
			 */
				
				
		public void rotateRight(){
				x0 = squares[0].getX();
				y0 = squares[0].getY();
				for(i =1; i<4; i++){
					Xval[i] = x0-(squares[i].getY()-y0);
					Yval[i] = x0+squares[i].getX()-x0;
				}
			if(this.checkMovesX(Xval[1],Yval[1],boardSquares)== false ||
					this.checkMovesX(Xval[2],Yval[2],boardSquares) == false||
					this.checkMovesX(Xval[3],Yval[3],boardSquares)== false ||
					this.checkMovesX(Xval[0],Yval[0],boardSquares)== false||
					this.checkMovesY(startLocX, startLocY+1,boardSquares)==false){
				x0 = squares[0].getX();
				y0 = squares[0].getY();
				squares[0].moveToTetrisLocation(x0,y0);
				squares[1].moveToTetrisLocation(x0+a,y0+b);
				squares[2].moveToTetrisLocation(x0+c,y0+d);
				squares[3].moveToTetrisLocation(x0+e,y0+f);
			}
			else{
				setA(-(squares[1].getY()-y0));
				setB((squares[1].getX()-x0));
				setC(-(squares[2].getY()-y0));
				setD((squares[2].getX()-x0));
				setE(-(squares[3].getY()-y0));
				setF((squares[3].getX()-x0));
				
				squares[0].moveToTetrisLocation(x0,y0);
				squares[1].moveToTetrisLocation(x0+a,y0+b);
				squares[2].moveToTetrisLocation(x0+c,y0+d);
				squares[3].moveToTetrisLocation(x0+e,y0+f);
					}
		
			}
		public int getY(){
			for(i=0;i<4;i++){
			y = squares[i].getY();
			}
			return y;
			
		}
}



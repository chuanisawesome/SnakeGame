package snake;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

/******************************************************************************************
 * 
 *Name: 		Chuan-liChang
 *Course: 		CSC 143
 *Quarter: 		Fall 2018
 *Description: 
 *
 ******************************************************************************************/
public class Snake{

	// snakeBody array that tracks the snake's body segments
	private ArrayList<Point> snakeBody;
	private Point tail;

	private Direction direction;
	private Point     head;
	private boolean   elongate;
	public  boolean   gameOver;
	private boolean   isMoving          = false;

	// Colors
	private Color            color      = Color.WHITE;

	// Postioning
	public static final int  SQUARE     = 6;
	public static final int  START_SIZE = 20;
	public static final int  START_X    = GameWindow.WIDTH  / 2;
	public static final int  START_Y    = GameWindow.HEIGHT / 2;

	/*****************************************************************************************
	 * TODO: 
	 * 		[x] test snakeself collision game is over
	 * 		[x] test food collision
	 * 
	 *****************************************************************************************/


	/*****************************************************************************************
	 * 
	 * Constructor: Snake
	 * 				initializes data Fields
	 * 
	 * ***************************************************************************************/
	public Snake() {
		isMoving = false;
		elongate = false;
		snakeBody = make();
	}

	/*****************************************************************************************
	 * 
	 * method: checkFoodCollision
	 * 		   checks whether the snake has collided with food
	 * 
	 *****************************************************************************************/
	public void checkFoodCollision() {
		if(elongate) {
			grow();
		}
	}

	/*******************************************************************************************
	 * 
	 * method: checkSelfCollision
	 * 		   checks whether the snake has collided with itself
	 * 
	 *******************************************************************************************/
	public boolean checkSelfCollision() {
		// snake head location
		head = snakeBody.get(0);
		
		// checks to see if the snake head is the same position as the tailsegments
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeBody.get(i).getX() == head.getX() && snakeBody.get(i).getY() == head.getY()) {
				return true;
			}
		}
		return false;
	}

	/*******************************************************************************************
	 * 
	 * method: draw(Pane)
	 *         draws the current snakeBody
	 *         
	 * *****************************************************************************************/
	public void draw(Pane pane) {
		for (Point p : snakeBody) {
			Rectangle body = new Rectangle(p.getX(), p.getY(), SQUARE, SQUARE);
			body.setStroke(color);
			pane.getChildren().add(body);
		}
	}

	/*******************************************************************************************
	 * 
	 * method: elongate
	 * 			indicates whether the snake body is growing by one square.
	 * 
	 *******************************************************************************************/
	public boolean elongate() {
		return false;
	}

	/******************************************************************************************
	 * 
	 * accessor: getDirection
	 * 		      indicates the direction of the snake
	 * 
	 *******************************************************************************************/
	public Direction getDirection() {
		return direction;
	}

	/*******************************************************************************************
	 * 
	 * accessor: getX
	 * 		     reports the x position of the head of the snake
	 * 
	 *******************************************************************************************/
	public double getX() {
		return snakeBody.get(0).getX();
	}

	/*******************************************************************************************
	 * 
	 * accessor: getY
	 * 		     reports the y position of the head of the snake
	 * 
	 *******************************************************************************************/
	public double getY() {
		return snakeBody.get(0).getY();
	}

	/*******************************************************************************************
	 * 
	 * accessor: getSnakeBody
	 * 		     reports the body(tail segment) of snake
	 * 
	 *******************************************************************************************/
	public ArrayList<Point> getSnakeBody() {
		return snakeBody;
	}

	/*******************************************************************************************
	 * 
	 * method: grow
	 * 		   reports whether to lengthen the snake by one square
	 * 
	 *******************************************************************************************/
	public void grow() {
		snakeBody.add(tail);
		elongate = false;
	}
	
	/*******************************************************************************************
	 * 
	 * method: isGrowing(boolean b)
	 * 		   checks if snake is growing
	 * 
	 *******************************************************************************************/
	public void isGrowing(boolean b) {
		elongate = b;
	}

	/*******************************************************************************************
	 * 
	 * method: isMoving
	 * 		   reports whether the snake is moving,
	 * 		   to provide this information to outside classes
	 * 
	 *******************************************************************************************/
	public boolean isMoving() {
		return false;
	}
	
	/*******************************************************************************************
	 * 
	 * helper method: make
	 *                makes initial snake with an initial body length
	 *                
	 * *****************************************************************************************/
	private ArrayList<Point> make() {

		ArrayList<Point> newSnake = new ArrayList<Point>();
		newSnake.add(new Point(START_X, START_Y) );

		for (int i = 1; i < START_SIZE; i++) {
			newSnake.add(new Point(START_X - i * SQUARE , START_Y) );
		}
		return newSnake;
	}

	/********************************************************************************************
	 * 
	 * method: move()
	 * 		   moves snake and checks if snake has collided with the food as it is moving
	 * 
	 ********************************************************************************************/
	public void move() {

		if(isMoving) {	

			head = snakeBody.get(0);
			tail = snakeBody.get(snakeBody.size() -1);

			double newX = head.getX() + direction.getX() * SQUARE;
			double newY = head.getY() + direction.getY() * SQUARE;

			Point newHead = new Point(newX, newY);
			// Updates the snake's tail using the snakeBody ArrayList.
			for (int i = snakeBody.size() - 1; i > 0; i--) {
				// sets to index and point element
				snakeBody.set(i, snakeBody.get(i - 1));
			}
			snakeBody.set(0, newHead);
		}
		// check if snake collided with food
		checkFoodCollision();
	}

	/*******************************************************************************************
	 * 
	 * mutator: setDirection
	 * 		    modifies the direction of the snake
	 * 
	 *******************************************************************************************/
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/*******************************************************************************************
	 * 
	 * mutator: setMoving
	 * 		    modifies whether the snake is moving, or not
	 * 
	 *******************************************************************************************/
	public void setMoving(boolean moving) {
		isMoving = moving;
	}
}

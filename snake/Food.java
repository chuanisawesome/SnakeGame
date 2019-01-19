package snake;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/******************************************************************************************
 * 
 *Name: 		Chuan-liChang
 *Course: 		CSC 143
 *Quarter: 		Fall 2018
 *Description: 
 *
 ******************************************************************************************/

public class Food {

	private double   n;
	private double   x;
	private double   y;
	public  int		 score;

	private Color    foodColor;

	public static final int SIZE  = 8;


	/*******************************************************************************************
	 * TODO: 
	 * 		[x] create food boundries
	 * 		[x] check snake collision is true
	 * 		[x] add point when food is eaten
	 * 
	 *******************************************************************************************/

	/**************************************************************************************
	 * 
	 * Constructor: Food(Snake)
	 * 			    initializes data Fields
	 * 
	 * ************************************************************************************/
	public Food(Snake s) {
		foodColor = Color.LAWNGREEN;
		n         = 1.5;
		createFoodLocation();
	}

	/*************************************************************************************
	 * 
	 * method: checkSnakeCollision
	 * 		   checks to see if the snake has collided with the food
	 * 		   then Places the food at a random location on 
	 * 		   the grid without placing it directly on the snake
	 * 
	 *************************************************************************************/
	public boolean checkSnakeCollision(Snake snake) {
		
		//looks at the position of the snake's body
//		double snakeHeadX = snake.getX() + Snake.SQUARE / 2;
//		double snakeHeadY = snake.getY() + Snake.SQUARE / 2;
		
		if(((snake.getX() >= x) && (snake.getX() <= x + SIZE)) 
				&& ((snake.getY() >= y) && (snake.getY() <= y + SIZE))) {
				//calls createFoodLocation in random location 
			
				createFoodLocation();
				score++;
				snake.isGrowing(true);
				return true;
		}
		return false;
	}

	/*************************************************************************************
	 * 
	 * method: createFoodLocation
	 * 			determines food location on playing area with bounds of the gameBoard
	 * 
	 *************************************************************************************/
	public void createFoodLocation() {

		x     =  (Math.random() * ( Controller.BOARD_WIDTH  - n * SIZE ) );
		y     =  (Math.random() * ( Controller.BOARD_HEIGHT - n * SIZE ) );

		// create food within the bounding area
		if(x < SIZE) {
			x = -x;
		}
		
		if(x > Controller.BOARD_WIDTH - SIZE) {
			x = Controller.BOARD_WIDTH - 5 * SIZE;
		}

		if(y < n * SIZE) {
			y = -y;
		}

		if(y > Controller.BOARD_HEIGHT - SIZE) {
			y = Controller.BOARD_HEIGHT - 2 * SIZE;
		}
	}

	/***********************************************************************************
	 * 
	 * method: draw
	 * 		   draws the food onto the game screen
	 * 
	 ***********************************************************************************/
	public void draw(Pane pane) {
		Rectangle food = new Rectangle(x, y, SIZE, SIZE);
		food.setFill(foodColor);
		pane.getChildren().add(food);
	}

	/***********************************************************************************
	 * 
	 * accessor: getScore
	 * 		   	 reports the score of the player playing the game
	 * 
	 ***********************************************************************************/
	public int getScore() {
		return score;
	}

}

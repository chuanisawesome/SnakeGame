package snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javafx.util.Duration;

/******************************************************************************************
 * 
 *Name: 		Chuan-liChang
 *Course: 		CSC 143
 *Quarter: 		Fall 2018
 *Description: 
 *
 ******************************************************************************************/
public class Controller extends BorderPane {

	private Snake      snake;
	private Food       food;

	private Text       score;
	private Text       endGame;
	private Text       about;

	private Pane       gameBoard;
	private Pane	   scoreBoard;
	private Pane	   optionsBoard;

	private Stage      stage;
	private Timeline   animation;


	public static final int  WIDTH        = GameWindow.WIDTH ;
	public static final int  HEIGHT       = GameWindow.HEIGHT;
	public static final int  BOARD_WIDTH  = WIDTH - 10 - 25;
	public static final int  BOARD_HEIGHT = HEIGHT - 120 - 25;

	public static boolean gameOver        = false;
	public boolean paused                 = false;

	private static final String ABOUT     = "About Snake: \n" +
			"To start the game, use the up arrow key. \n" 
			+ "To control the snake use the arrow keys \n"
			+ "and eat the food";


	/*******************************************************************************************
	 * TODO: 
	 *  	[x] Find a way to create a random food location
	 *  	[x] Move snake
	 *  	[x] Start the animation of the snake to move
	 *  	[x] Add pause game
	 *  	[x] creating different board game for snake and buttons
	 *  	[x] add score when food is eaten
	 *  	[x] make sure the snake is clearing out it's tail
	 *  	[x] show the score on the game screen
	 *  	[x] game over on screen
	 *  	[x] creating a new game
	 * 
	 *******************************************************************************************/

	/*******************************************************************************************
	 * 
	 * constructor: draw(Pane)
	 *         draws the current snakeBody
	 *         
	 * *****************************************************************************************/
	public Controller(Stage primarystage) {
		stage 	  = primarystage;
		snake     = new Snake();			  
		food      = new Food(snake);
		gameBoard = new Pane();
		
		
		//about the game
		about = new Text (45,45, ABOUT);
		about.setFont(Font.font("Helvetiva", FontWeight.BOLD, FontPosture.REGULAR, 16));
		about.setFill(Color.BLACK);

		//displays score for player
		//Creating a Text object
		score = new Text(25, 25, "\n Score: " + food.score);
		score.setFill(Color.BLACK);
		score.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 20));

		/******************************************************************************************
		 * 
		 * layout: HBox
		 * 		   displays horizontally.
		 * 
		 * ****************************************************************************************/
		scoreBoard = new HBox(score);


		// button to create the new game
		Button newGame = new Button("New Game");
		newGame.setPrefSize(120, 100);
		// invoked whenever the button is fired (user clicking the button or by key press)
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				newGame(event);
			}
		});

		// button for the settings of the game
		Button settings = new Button("Settings");
		settings.setPrefSize(120, 100);
		// invoked whenever the button is fired (user clicking the button or by key press)
		settings.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Going to Settings");
			}
		});

		/******************************************************************************************
		 * 
		 * layout: VBox
		 * 		   displays buttons vertically.
		 * 
		 * ****************************************************************************************/
		optionsBoard = new VBox(newGame, settings);

		//sets background color
		gameBoard.setStyle("-fx-background-color: white;");
		gameBoard.setPrefSize(500, 450);

		optionsBoard.setStyle("-fx-background-color: white;");
		optionsBoard.setPrefSize(100, 100);

		scoreBoard.setStyle("-fx-background-color: white;");
		scoreBoard.setPrefSize(100, 50);

		setRight(optionsBoard);
		setLeft(scoreBoard);
		setTop(gameBoard);
		setCenter(about);

		//Sets the style of the game board, score board, and options board.
		//Set margin for top area.
		BorderPane.setMargin(gameBoard, new Insets(2, 5, 5, 3));
		//Set margin for left area.
		BorderPane.setMargin(scoreBoard, new Insets(10));
		//Set margin for right area.
		BorderPane.setMargin(optionsBoard, new Insets(10));	

		gameBoard.setOnKeyPressed(e -> control(e));
		KeyFrame frame = new KeyFrame(Duration.millis(100), e -> update());
		animation = new Timeline(frame);
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		gameBoard.requestFocus();
		gameBoard.setFocusTraversable(true);
		System.out.println("End of Controller");
	}

	/*******************************************************************************************
	 * 
	 * method: animate
	 *         this is where the animation of the snake happens
	 *         how the snake is moving on the screen
	 *         
	 * *****************************************************************************************/
	public void animate() {

		checkCollisions();

		if(!gameOver) {
			// moves the snake
			snake.move();
			// calls the checkSnakeCollision to see if the snake has collided with food
			food.checkSnakeCollision(snake);
		} else {
			//Creating a Text object for GameOver and the players score
			int playerScore = food.getScore();
			endGame = new Text(140, 200, "Game Over \n Score: " + playerScore);
			endGame.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
			endGame.setFill(Color.RED);
			getChildren().add(endGame);
		}
	}

	/*******************************************************************************************
	 * 
	 * method: checkCollisons
	 *         checks whether the snake ate the food or ate itself
	 *         
	 * *****************************************************************************************/
	public void checkCollisions() {

		// new gameboard bounds
		double newBoardX = 600;
		double newBoardY = 450;

		//checks to see if the snake is in bound of the gameBoard
		if (snake.getX() < 0 || snake.getX() >= newBoardX || snake.getY() < 0 || snake.getY() >= newBoardY) {
			gameOver = true;
		}

		//calls snake self collision method to see if snake has collided with self
		if(snake.checkSelfCollision()) {
			gameOver = true;
		}

		//if the game is over then stop
		if(gameOver) {
			animation.stop();
		}

	}

	/*******************************************************************************************
	 * 
	 * method: control
	 *         controls the snakes movement 
	 *         listening to the key press that the play press
	 *         
	 * *****************************************************************************************/
	public void control(KeyEvent event) {

		//if snake is moving if the keys are pressed
		if (!snake.isMoving()) {
			if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.DOWN) {
				snake.setMoving(true);
			}
		}

		//listening to keypress and calls enums direction to set the direction
		switch(event.getCode()){
		case DOWN:
			if (snake.getDirection() != Direction.UP) {
				snake.setDirection(Direction.DOWN);
				System.out.println("Snake is going down");
			}
			break;
		case UP:
			if (snake.getDirection() != Direction.DOWN) {
				snake.setDirection(Direction.UP);
				System.out.println("Snake is going up");
			}
			break;
		case RIGHT:
			if (snake.getDirection() != Direction.LEFT) {
				snake.setDirection(Direction.RIGHT);
				System.out.println("Snake is going right");
			}
			break;
		case LEFT:
			if (snake.getDirection() != Direction.RIGHT) {
				snake.setDirection(Direction.LEFT);
				System.out.println("Snake is going left");
			}
			break;
		case SPACE:
			paused = !paused;
			System.out.println("Game is paused");
			if (paused) {
				animation.pause();
			} else {
				animation.play();
			}

			break;
		default:
		}
	}

	/*******************************************************************************************
	 * 
	 * method: draw
	 * 		   draws snake and food on gameBoard Pane
	 *         
	 *******************************************************************************************/
	public void draw() {
		snake.draw(gameBoard);
		food.draw(gameBoard);
	}

	/********************************************************************************************
	 * 
	 * method: createNewGame(ActionEvent event)
	 *            draw all about, boundLine, score
	 *            creates the Stage again
	 *            
	 *******************************************************************************************/
	public void newGame(ActionEvent event) {
		System.out.println("Starting New Game");

		//close the stage and adds then gameBoard scene back
		stage.close();

		/***************************************************************************************
		 * 
		 * required: Scene
		 * 			 creates the scene for the game
		 * 
		 ***************************************************************************************/
		Scene scene = new Scene(new Controller(stage), GameWindow.WIDTH, GameWindow.HEIGHT, Color.BLACK);
		scene.setFill(Color.BLACK);	
		stage.setTitle("Snake Game");
		stage.setScene(scene);					
		stage.show();
	}

	/*******************************************************************************************
	 * 
	 * method: update
	 * 		   updates graphics and the score on screen
	 *         
	 *******************************************************************************************/
	public void update() {
		gameBoard.getChildren().clear();
		score.setText("Score: " + food.getScore());
		draw();
		animate();
	}
}

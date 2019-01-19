package snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/******************************************************************************************
 * 
 *Name: 		Chuan-liChang
 *Course: 		CSC 143
 *Quarter: 		Fall 2018
 *Description: 
 *
 ******************************************************************************************/

public class GameWindow extends Application {


	public static final int HEIGHT = 600;
	public static final int WIDTH  = 600;
	

	/*****************************************************************************************
	 * 
	 * override: start
	 *           overrides the start method in the Application class
	 *           
	 * ***************************************************************************************/
	@Override
	public void start(Stage primaryStage) {


		primaryStage.setTitle("Snake Game");
		/***************************************************************************************
		 * 
		 * required: control
		 * 			 creates the Pane to hold graphics.  
		 * 
		 ***************************************************************************************/
		 //create a Controller to hold graphics
        Controller control = new Controller(primaryStage);
		
		/***************************************************************************************
		 * 
		 * required: Scene
		 * 			 creates the scene
		 * 
		 ***************************************************************************************/
		Scene scene = new Scene(control, WIDTH, HEIGHT, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		System.out.println("Focus Rquested");
	}


	/*******************************************************************************************
	 * 
	 * main method: launch application
	 *              needed to launch JavaFX program when running in IDE
	 *              
	 * *****************************************************************************************/
	public static void main(String[] args) {
		Application.launch(args);
	}

}

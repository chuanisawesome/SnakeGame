package snake;

/******************************************************************************************
 * 
 *Name: 		Chuan-liChang
 *Course: 		CSC 143
 *Quarter: 		Fall 2018
 *Description: 
 *
 ******************************************************************************************/

public enum Direction {
	/***********************************************************************************
	 * 
	 * Enums: Direction
	 * 		  the direction of where the snake is going to move that is going to be
	 * 		  mapped with the keypress
	 * 
	 ***********************************************************************************/
	
	UP(0,-1), 
	DOWN(0,1), 
	LEFT(-1,0), 
	RIGHT(1,0);
	
	public final double x;
	public final double y;
	
	Direction(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}

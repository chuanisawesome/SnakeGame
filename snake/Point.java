package snake;


/******************************************************************************************
 * 
 *Name: 		Chuan-liChang
 *Course: 		CSC 143
 *Quarter: 		Fall 2018
 *Description: 
 *
 ******************************************************************************************/

public class Point {
	
	/*************************************************************************************
	 * 
	 * Data members of the point class.
	 * 
	 *************************************************************************************/
	public double x;
	public double y;
	
	/*************************************************************************************
	 * 
	 * constructor: Point
	 * 				Takes in two arguments and initializes x and y
	 * 
	 *************************************************************************************/
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/*************************************************************************************
	 * 
	 * constructor: Point
	 * 				Takes in two arguments and initializes x and y
	 * 
	 *************************************************************************************/
	public Point(Point point) {
		this.x = point.x;
		this.y = point.y;
	}
	
	

	/************************************************************************************
	 * 
	 * accessor: getX
	 * 			 provides access to instance var x
	 * 
	 ************************************************************************************/
	public double getX() {
		return x;
	}
	
	/************************************************************************************
	 * 
	 * accessor: getY
	 * 			 provides access to instance var y
	 * 
	 ************************************************************************************/
	public double getY() {
		return y;
	}
	
	/************************************************************************************
	 * 
	 * mutator: setX
	 * 			Builds the set point of x
	 * 
	 ************************************************************************************/
	public void setX(double x) {
		this.x = x;
	}
	
	/***********************************************************************************
	 * 
	 * mutator: setY
	 * 			Builds the set point of y
	 * 
	 ***********************************************************************************/
	public void setY(double y) {
		this.y = y;
	}
	
	/***********************************************************************************
	 * 
	 * method: toString
	 * 			prints out information of x and y
	 * 
	 ***********************************************************************************/
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}

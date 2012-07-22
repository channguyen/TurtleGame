import java.awt.Color;


public class MazeTurtle extends Turtle {
	private static final Color WALL_COLOR 		 = Color.black;
	private static final Color WENSLEYDALE_COLOR = Color.yellow;
	
	private static final int step = 10;
	private static final int DELAY_INTERVAL = 10;
	private Picture maze;
	
	public MazeTurtle(int x, int y, Picture picture) {
		super(x, y, picture);
		maze = new Picture("maze.png"); 
	}
	
	public MazeTurtle(int x, int y, ModelDisplay model) {
		super(x, y, model);
		maze = new Picture("maze.png"); 
	}
	
	public void delay() {
		try {
			Thread.sleep(DELAY_INTERVAL);
		}
		catch (InterruptedException ie) {
			System.out.println("Error in delay() " + ie.toString());
		}
	}
	
	public boolean onColor(Color c) {
		return false;
	}
	
	private boolean isWall(int x, int y) {
		Pixel p = maze.getPixel(x, y);
		if (p.getColor().equals(WALL_COLOR))
			return true;
		return false;
	}
	
	private boolean isTreasure(int x, int y) {
		Pixel p = maze.getPixel(x, y);
		if (p.getColor().equals(WENSLEYDALE_COLOR))
			return true;
		return false;
	}
	
	public boolean move() {
		int oldX = getXPos();
	    int oldY = getYPos();
	    
	    // change the current position
	    int newX = oldX + (int) (step * Math.sin(Math.toRadians(getHeading())));
	    int newY = oldY + (int) (step * -Math.cos(Math.toRadians(getHeading())));
	    
	    double rightHeading = (getHeading() + 90) % 360;
	    double leftHeading = (getHeading() - 90) % 360;
	    
	    if (isWall(newX, newY)) {
	    	turnRight();
	    	forward(step);	
	    } 
	    else {
	    	int leftX = oldX + (int) (10 * Math.sin(Math.toRadians(leftHeading)));
	    	int leftY = oldY + (int) (10 * -Math.cos(Math.toRadians(leftHeading)));
	    
	    	int rightX = oldX + (int) (10 * Math.sin(Math.toRadians(rightHeading)));
	    	int rightY = oldY + (int) (10 * -Math.cos(Math.toRadians(rightHeading)));
	    	
	    	if (!isWall(rightX, rightY) && !isWall(leftX, leftY))
	    		turnLeft();
	    	
	    	forward(step);	
	    }
	   
	    if (isTreasure(getXPos(), getYPos()))
	    	return true;
		return false;
	}
}

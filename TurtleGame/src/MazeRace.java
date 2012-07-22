import java.awt.Color; 
import java.util.Random; 

public class MazeRace {
	/* Turtle objects for game */
	private MazeTurtle chaser;
	private MazeTurtle evader; 
	
	/* Background of world with safe zones */
	private Picture maze; 
	
	/* Time delay between steps to see chasing */
	private int delayInterval = 50; 
	
	/* Random generator */
	private Random rand;
									
	/**
	 * Constructor
	 */
	public MazeRace() {
		World world = new World(); 
		maze = new Picture("maze.png"); 
		world.setPicture(maze);
		rand = new Random();
		
		setupEvader(world);
		setupChaser(world);
		world.repaint(); 
	}
	
	public void run() {
		sayGameStart();
		play(); 
		sayGameOver();	
	}
	
	private int getRandomHeading() {
		int i = rand.nextInt(4);
		switch (i) {
			case 0:
				return 0;
			case 1:
				return 90;
			case 2:
				return 180;
			case 3:
				return 270;
		}
		return -1;
	}
	
	private void sayGameStart() {
		System.out.println(chaser);
		System.out.println(evader);
		System.out.println("Play tag!");
	}
	
	private void sayGameOver() {
		System.out.println("Game over.");
		System.out.println(chaser);
		System.out.println(evader);
	}
	
	private void setupEvader(World w) {
		evader = new MazeTurtle(605, 35, w);
		evader.setHeading(getRandomHeading());
		evader.setName("Wallace");
	}
	
	private void setupChaser(World w) {
		chaser = new MazeTurtle(35, 35, w);
		chaser.setHeading(getRandomHeading());
		chaser.setName("Gromit");
	}

	private void play() {
		int moves = -1; 
		boolean done = false;
		while (!done) { 
			moves++;
			
			done = evader.move();
			evader.delay();
			
			if (done) {
				System.out.println("Evader wins in " + moves + " moves");
				break;
			}
			
			done = chaser.move(); 
			chaser.delay();
			
			if (done) {
				System.out.println("Chaser wins in " + moves + " moves");
				break;
			}
		} 
	}

	public static void main(String[] arg) {
		MazeRace race = new MazeRace();
		race.run();
		System.out.println("thats all folks...");
	}
} 
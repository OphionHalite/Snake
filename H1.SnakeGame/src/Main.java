import java.util.Scanner;

public class Main {
	private int rows;
	private int columns;
	private Spot field[][];
	private Snake snake;
	
	public Main(){
		rows = 10;
		columns = 10;
		field = new Spot[][]{{Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall},
					{Spot.Wall,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Wall},
					{Spot.Wall,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Wall},
					{Spot.Wall,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Wall},
					{Spot.Wall,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Wall},
					{Spot.Wall,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Wall},
					{Spot.Wall,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Wall},
					{Spot.Wall,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Wall},
					{Spot.Wall,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Empty,Spot.Wall},
					{Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall,Spot.Wall}};
					// Adjust matrix for different map lay-out
		snake = new Snake(new int[]{5,1});
	}
	
	private enum Spot {
		Wall, Apple, Empty;
	}
	
	private void deleteApple(){
		field[snake.getRow()][snake.getColumn()] = Spot.Empty;  //Delete previous apple
		//Use only if snakehead ate apple
	}
	
	private void newApple(){
		boolean placed = true;
		while(placed){
			int a = (int)(Math.random()*rows); 		//Pick a random row
			int b = (int)(Math.random()*columns); 	//Pick a random column
			if (field[a][b] == Spot.Empty){ 		//If random spot is empty
				field[a][b] = Spot.Apple; 			//Place new apple on chosen spot
				placed = false;
			}
		}
	}
	
	private void detectMovement(){
		if(field[snake.getRow()][snake.getColumn()]==Spot.Apple){	//Detects if snakehead ate apple
			deleteApple();											//Consumes the apple
			newApple();												//Spawn a new apple
		} else {
			snake.removeLast();	//If snake didn't consume apple, tail moves along
		}
	}
	
	public void executeCommand(String command){
		switch (command){
		/* For use if game gets automated in time
		case " ":
			snake.goFurther();
			detectMovement();
			break;
			*/
		case "q":
			snake.goLeft();
			detectMovement();
			break;
		case "d":
			snake.goRight();
			detectMovement();
			break;
		case "z":
			snake.goUp();
			detectMovement();
			break;
		case "s":
			snake.goDown();
			detectMovement();
			break;
		case "exit":
			System.out.println("Game Over");
			System.exit(0);
			break;
		case "help":
			System.out.println("Welcome to Snake");
			System.out.println("The goal in this game is to have your snake become as large as possible.");
			System.out.println("Your snake gets bigger by eating apples indicated by \"A\".");
			System.out.println("The following commands are available:");
			System.out.println("    d: moves the snake's head left");
			System.out.println("  	q: moves the snake's head right");
			System.out.println("   	z: moves the snake's head up");
			System.out.println("   	s: moves the snake's head down");
			System.out.println("	exit: forfeit the game");
			System.out.println("Good luck!");
			break;
		default:
			System.out.println("Unknown command, use help for a full list of all available commands.");
		}
	}
	
	public boolean gameOver(){
		boolean state = false;
		//Check if coordinates of head are at a Wall
		int y = snake.getRow();
		int x = snake.getColumn();
		if(field[y][x]==Spot.Wall) state = true;
		
		//Check if coordinates of head are at coordinates of tail
		Node<int[]> cursor = snake.head().next();
		for(int k=1; k<snake.size();k++){
			if(((cursor.getElement())[0] == y) && ((cursor.getElement())[1] == x)){
				state = true;
			}
			cursor = cursor.next();
		}
		return state;
	}
	
	public String toString(){
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				switch (field[i][j]) {
				case Wall:
					b.append("# ");
					break;
				case Apple:
					b.append("A ");
					break;
				case Empty:
					boolean placed = false;
					if(snake.getRow() == i && snake.getColumn() == j){
						b.append("O ");
						placed = true;
					}
					Node<int[]> cursor = snake.head().next();
					if(!placed){
						for(int k=1; k<snake.size();k++){
							if((cursor.getElement())[0] == i && (cursor.getElement())[1] == j){
								b.append("S ");
								placed = true;
							}
							cursor = cursor.next();
						}
					}
					if(!placed)
					b.append("  "); //Makes empy spot only if no snakepiece was placed
					break;
				}
			}
			b.append("\n");
		}
		return b.toString();
	}
	
	public static void main(String[] args) {
		Main game = new Main();
		Scanner scanner = new Scanner(System.in); 	//Initialize scanner
		game.newApple(); 							//Spawn apple
		System.out.print(game.toString()); 			//Show game grid
		while(true){
			String command = scanner.next(); 		//Get next command
			game.executeCommand(command);			//Execute the command
			if(game.gameOver()){					//return true if game over
				System.out.println("Game Over");
				System.out.println("Score: "+game.snake.size());
				scanner.close();					//Close the program
				System.exit(0);
			}
			System.out.print(game.toString());		//Print the board
		}
	}
}

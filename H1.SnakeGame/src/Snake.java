
public class Snake extends List<int[]>{
	
	private Direction dir;
	private enum Direction {
		Left, Right, Up, Down;
	}
	
	public Snake(int[] element) {
		super(element);
		dir = Direction.Right;
	}
	
	public int getRow(){
		return head.getElement()[0];
	}
	
	public int getColumn(){
		return head.getElement()[1];
	}
	
	public void goFurther(){
		switch(dir){
		case Left:
			goLeft();
		case Right:
			goRight();
		case Up:
			goUp();
		case Down:
			goDown();
		}
	}
	
	public void goLeft(){
		Node<int[]> newHead = new Node<int[]>(new int[]{getRow(),getColumn()-1});
		prepend(newHead);
		dir = Direction.Left;
	}
	
	public void goRight(){
		Node<int[]> newHead = new Node<int[]>(new int[]{getRow(),getColumn()+1});
		prepend(newHead);
		dir = Direction.Right;
	}
	
	public void goUp(){
		Node<int[]> newHead = new Node<int[]>(new int[]{getRow()-1,getColumn()});
		prepend(newHead);
		dir = Direction.Up;
	}
	
	public void goDown(){
		Node<int[]> newHead = new Node<int[]>(new int[]{getRow()+1,getColumn()});
		prepend(newHead);
		dir = Direction.Down;
	}

}

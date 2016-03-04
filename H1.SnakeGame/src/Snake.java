
public class Snake extends List<int[]>{
	
	public Snake(int[] element) {
		super(element);
	}
	
	public int getRow(){
		return head.getElement()[0];
	}
	
	public int getColumn(){
		return head.getElement()[1];
	}
	
	public void prependLeft(){
		Node<int[]> newHead = new Node<int[]>(new int[]{getRow(),getColumn()-1});
		prepend(newHead);
	}
	
	public void prependRight(){
		Node<int[]> newHead = new Node<int[]>(new int[]{getRow(),getColumn()+1});
		prepend(newHead);
	}
	
	public void prependUp(){
		Node<int[]> newHead = new Node<int[]>(new int[]{getRow()-1,getColumn()});
		prepend(newHead);
	}
	
	public void prependDown(){
		Node<int[]> newHead = new Node<int[]>(new int[]{getRow()+1,getColumn()});
		prepend(newHead);
	}

}

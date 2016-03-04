
public class List<E> {
	protected int size = 1;
	protected Node<E> head;
	
	public List(E element){
		head = new Node<E>(element);
	}
	
	
	public int size(){
		return size;
	}
	
	public Node<E> head(){
		return head;
	}
	
	public void prepend(Node<E> node){
		size++;
		node.setNext(head);
		head = node;
	}


	public void removeLast() {
		size--;
		Node<E> cursor = head;
		while(cursor.next().next() != null){
			cursor = cursor.next();
		};
		cursor.setNext(null);
	}
}



public class Node<E> {
	private E element;
	private Node<E> next;
	
	public Node(E element){
		this.element = element;
		next = null;
	}
	
	public E getElement(){
		return element;
	}
	
	public Node<E> next(){
		return next;
	}
	
	public void setNext(Node<E> next){
		this.next = next;
	}
	
	public void setElement(E element){
		this.element = element;
	}
}
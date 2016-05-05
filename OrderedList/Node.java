// Ordered List implementation
// Shawn Seymour
// CSci 4651

public class Node<T> {
	public T item;
	public Node<T> next;
	
	public Node(T item) {
		System.out.println("Creating node: " + item);
		this.item = item;
	}
}

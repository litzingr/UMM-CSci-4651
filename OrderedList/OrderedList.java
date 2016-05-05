// Ordered List implementation
// Shawn Seymour
// CSci 4651

import java.util.function.Function;
import java.util.function.Predicate;

public class OrderedList<T extends Comparable<T>> {
	
	private Node<T> first;
	private int size = 0;
	
	public OrderedList() {
		System.out.println("Constructing ordered list");
		
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public void add(T item) {
		Node<T> node = new Node<T>(item);
		if(first == null) {
			System.out.println("Adding " + item + " to the front");
			first = node;
		} else if(first.item.compareTo(item) > 0) {
			// we have something in first spot. lets compare. add to front here.
			node.next = first;
			first = node;
		} else {
			Node<T> before = first;
			Node<T> after = first.next;
			while(after != null) {
				if(after.item.compareTo(item) > 0) {
					break;
				}
				before = after;
				after = after.next;
			}
			// insert between!
			node.next = before.next;
			before.next = node;
		}
		size++;
	}
	
	public T get(int i) {
		if(i < 0 || i > size()-1) {
			return null;
		} else {
			return findNode(i, first);
		}
	}
	
	private T findNode(int i, Node<T> node) {
		if(i == 0) {
			return node.item;
		} else {
			return findNode(i-1, node.next);
		}
	}
	
	public String toString(){
		String statement = "The list is: ";
		Node<T> temp = first;
		
		while(temp != null) {
			if(temp.next == null) {
				statement += temp.item + ".";
			} else {
				statement += temp.item + ", ";
			}
			
			temp = temp.next;
		}
		
		return statement;
	}
	
	public OrderedList<T> map(Function<T,T> cond) {
		Node<T> temp = first;
		while(temp != null) {
			temp.item = cond.apply(temp.item);
			temp = temp.next;
		}
		return this;
	}
	
	public OrderedList<T> filter(Predicate<T> cond) {
		Node<T> temp = first;
		Node<T> previous = null;
		
		if(temp.next == null) {
			if(!cond.test(temp.item)) {
				System.out.println("Remove first item");
				first = null;
				size--;
			}
		} else {
			while(temp != null) {
				if(!cond.test(temp.item)) {
					System.out.println("Remove: " + temp.item);
					size--;
					// We want to remove temp here
					if(previous != null) {
						previous.next = temp.next;
						temp = previous;
					} else {
						first = temp.next;
						temp = first;
					}
				} else {
					previous = temp;
					temp = temp.next;
				}
			}
		}
		return this;
	}
	
	
}

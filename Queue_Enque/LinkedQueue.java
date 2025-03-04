package Queue_Enque;

import interfaces.Queue;
import list.DoublyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

	private DoublyLinkedList<E> ll;

	public static void main(String[] args) {
	}

	public LinkedQueue() {
		ll = new DoublyLinkedList<>();
	}

	@Override
	public int size() {
		return ll.size();
	}

	@Override
	public boolean isEmpty() {
		return ll.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		ll.addLast(e);
	}

	@Override
	public E first() {
		return ll.first();
	}

	@Override
	public E dequeue() {
		return ll.removeFirst();
	}

	public String toString() {
		return ll.toString();
	}
}

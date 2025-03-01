package list;

import interfaces.List;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private T element;  // The element stored in the node
        private Node<T> next;  // Reference to the next node in the list

        public Node(T e, Node<T> n) {
            //TODO...Done
            element = e;
            next = n;
        }

        //TODO...Done
        public T getData() {
            return element;
        }

        // Setter for the element
        public void setData(T e) {
            element = e;
        }

        // Getter for the next node
        public Node<T> getNext() {
            return next;
        }

        // Setter for the next node
        public void setNext(Node<T> n) {
            next = n;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        //TODO..Done
        if (i == 0) {
            return tail.getNext().getData();
        }
    
        Node<E> current = tail.getNext();
        for (int j = 0; j < i; j++) {
            current = current.getNext();
        }
        return current.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        //TODO...Done
        if (i == 0) { // Add at the beginning
            addFirst(e);
        } else if (i == size) { // Add at the end
            addLast(e);
        } else { // Add at an intermediate position
            Node<E> prev = tail.getNext(); // Start at the first node
            for (int j = 0; j < i - 1; j++) {
                prev = prev.getNext();
            }
    
            Node<E> newNode = new Node<>(e, prev.getNext());
            prev.setNext(newNode);
            size++;
        }
    }

    @Override
    public E remove(int i) {
        //TODO...DOne
        if (i == 0) { // Add at the beginning
            removeFirst();
        } else if (i == size) { // Add at the end
            removeLast();
        }

        Node<E> prev = tail.getNext(); // Start at the first node
        for (int j = 0; j < i - 1; j++) {
            prev = prev.getNext();
        }
        Node<E> removedElement = prev.getNext();
        prev.setNext(removedElement.getNext());

        if (removedElement == tail) { // If removing the last element, update tail
            tail = prev;
        }
        
        size--;
        return removedElement.getData();
    }

    public void rotate() {
        //TODO...DOne
        if (tail != null) { 
            tail = tail.getNext(); // Move tail to the next node
        }
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        private Node<E> current;  // Tracks current node

        @Override
        public boolean hasNext() {
            // TODO...Done
            return (current != tail.getNext());
        }

        @Override
        public E next() {
            // TODO..Done
            E data = current.getData(); // Get current element
            current = current.getNext(); // Move forward
            return data;
        }
        //TODO
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        //TODO..Done
        if (size == 0) {
            return null;  // List is empty, nothing to remove
        }
    
        Node<E> firstNode = tail.getNext();  // The first node is after the tail (circular structure)
        E removedElement = firstNode.getData();  // Get the element to return
    
        if (size == 1) {
            // If there's only one element in the list, set tail to null
            tail = null;
        } else {
            // Update tail's next to the second node (circular structure)
            tail.setNext(firstNode.getNext());
        }
    
        size--;  // Decrease the size of the list
        return removedElement;  // Return the removed element
    }

    @Override
    public E removeLast() {
        //TODO...Done
        if (size == 0) {
            return null;  // List is empty, nothing to remove
        }
    
        Node<E> lastNode = tail;  // The last node is the current tail
        E removedElement = lastNode.getData();  // Save the element to return
    
        if (size == 1) {
            // If there's only one element, set tail to null (empty list)
            tail = null;
        } else {
            // Traverse to the second-to-last node
            Node<E> current = tail;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
    
            // Now, current is the second-to-last node
            current.setNext(tail.getNext());  // Point second-to-last node to the first node
            tail = current;  // Set tail to the second-to-last node
        }
    
        size--;  // Decrease the size of the list
        return removedElement;  // Return the removed element
    }

    @Override
    public void addFirst(E e) {
        //TODO...Done
        if (tail == null){
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newNode = new Node<>(e, tail.getNext());
            tail.setNext(newNode); 
        }

        size++;
    }

    @Override
    public void addLast(E e) {
        //TODO...Done
        if (tail == null){
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newNode = new Node<>(e, tail.getNext());
            tail.setNext(newNode);
            tail = newNode; 
        }

        size++;
    }


    public String toString() {
        //TODO..Done
        if (tail == null) {
            return "[]"; // Empty list case
        }
    
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = tail.getNext(); // Start from head (first node)
        
        do {
            sb.append(current.getData()).append(", ");
            current = current.getNext();
        } while (current != tail.getNext()); // Stop when we reach the start again
    
        sb.setLength(sb.length() - 2); // Remove the last ", "
        sb.append("]");
        
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for(int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}

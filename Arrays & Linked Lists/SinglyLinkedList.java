package list;

import interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        // TODO...Done
        private E data;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            // TODO ..Done
            this.data = e;
            this.next = n;
        }

        // Accessor methods
        public E getData() {
            return data;
        }
    
        public Node<E> getNext() {
            return next;
        }

    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)

    /**
     * The last node of the list
     */
    private Node<E> tail = null;               // last node of the list (or null if empty)

    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() { }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        // TODO...Done
        if (position < 0 || position >= size) { 
            throw new IndexOutOfBoundsException("Invalid position");
        }

        Node<E> temp = head;
        for (int i = 0; i < position; i++){
            temp = temp.getNext();
        }
        
        return temp.getData();
        // return null;
    }

    @Override
    public void add(int position, E e) {
        // TODO...Done
        if (position < 0 || position >= size) { 
            throw new IndexOutOfBoundsException("Invalid position");
        }
        else if (position == 0) {
            addFirst(e);
        }
        else{
             Node<E> temp = head;
        
        for (int i = 0; i < position-1; i++){
            temp = temp.getNext();
        }
        Node<E> newNode = new Node<>(e,temp.getNext());
        temp.next = newNode;
        }
       
        size++;
    }


    @Override
    public void addFirst(E e) {
        // TODO..Done
        head = new Node<E>(e,head);
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO.done
        Node<E> newNode = new Node<>(e,null);
        if (head == null){
            head = newNode;
        }
        else{
            Node<E> temp = head;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.next = newNode;
            tail = temp.next;  
        }
        size++;
    }

    @Override
    public E remove(int position) {
        // TODO..done
        E removedData = null;

        if (position < 0 || position >= size) { 
            throw new IndexOutOfBoundsException("Invalid position");
        }
        else if (position == 0){
            removedData = removeFirst();
        }
        else{
            Node<E> temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.getNext();
            }
            removedData = temp.getNext().getData();
            
            temp.next = temp.getNext().getNext();
            
            if (temp.next == null) {
                tail = temp;
            }
        }
        size--;
        return removedData;
    }

    @Override
    public E removeFirst() {
        // TODO...Done
        if (head == null){
            return null;
        }

        E removedData = head.getData();

        if (head.getNext() == null) {
            head = null;
            tail = null;
        }
        else{
            head = head.next;
        }
        size--;
        return removedData;
    }

    @Override
    public E removeLast() {
        // TODO...Done
        E removedData = null;
        if (head == null){
            return null;
        }
        else if (head.getNext() == null) {
            removedData = head.getData();
            head = null;
            tail = null;
        }
        else{
            Node<E> temp = head;
            while (temp.getNext().getNext() != null){
                temp = temp.getNext();
            }
            removedData = temp.next.getData();
            temp.next = null;
            tail = temp;  
        }
        size--;

        return removedData;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node curr;

        // TODO..done
        SinglyLinkedListIterator() { 
            curr = head; 
        }

        @Override
        public boolean hasNext() {
            // TODO..Done
            return curr != null;
        }

        @Override
        public E next() {
            // TODO...Done
            E res = (E) curr.getData(); 
            curr = curr.getNext(); 
            return res;
        }
    }

    // Chat GPT
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");  // Start with an opening bracket
        
        Node<E> temp = head;
        while (temp != null) {
            sb.append(temp.getData());  // Append the data of the current node
            if (temp.getNext() != null) {
                sb.append(", ");  // Add a comma if it's not the last element
            }
            temp = temp.getNext();  // Move to the next node
        }
        
        sb.append("]");  // Close with a closing bracket
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        ll.removeLast();
        ll.removeFirst();
       
        ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);
    }
}

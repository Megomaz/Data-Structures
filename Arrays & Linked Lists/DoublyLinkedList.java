package list;

import interfaces.List;

import java.util.Iterator;
import java.util.LinkedList;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        //TODO
        private E data;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            //TODO
            this.data = e;
            this.prev = p;
            this.next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }    

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        //TODO...Done
        head = null;
        tail = null;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        //TODO..Done
        Node<E> newNode = new Node<>(e, pred, succ);
        pred.setNext(newNode); 
        succ.setPrev(newNode);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        //TODO...Done
        Node <E> temp = head;

        for (int position = 0; position < i; position++){
            temp = temp.next;
        }

        return temp.getData();
    }

    @Override
    public void add(int i, E e) {
        //TODO...Done
        if (i == 0) {
            // Insert at the beginning (head)
            head = new Node<>(e, null, head);
            if (head.getNext() != null) {
                head.getNext().setPrev(head);
            }
            if (size == 0) {
                tail = head;  // If list was empty, tail should also point to head
            }
        } else if (i == size) {
            // Insert at the end (tail)
            tail = new Node<>(e, tail, null);
            if (tail.getPrev() != null) {
                tail.getPrev().setNext(tail);
            }
        } else {
            // Insert in the middle
            Node<E> current = head;
            for (int k = 0; k < i; k++) {
                current = current.getNext();
            }
            Node<E> newNode = new Node<>(e, current.getPrev(), current);
            current.getPrev().setNext(newNode);
            current.setPrev(newNode);
        }
    
        size++;
    }

    @Override
    public E remove(int i) {
        //TODO...Done
        // Handle invalid index
        if (i < 0 || i >= size) {
            return null; // Index out of bounds
        }
        
        Node<E> removedNode;
        
        // If we're removing the first node (head)
        if (i == 0) {
            removedNode = head;
            head = head.getNext();
            if (head != null) {
                head.setPrev(null); // Set the prev pointer of the new head to null
            }
        }
        // If we're removing a node from the middle or the tail
        else {
            Node<E> temp = head;
            for (int k = 0; k < i; k++) {
                temp = temp.getNext(); // Traverse the list to the node at index i
            }
            
            removedNode = temp; // Save the node to be removed

            // Link the previous node to the next node
            if (temp.getPrev() != null) {
                temp.getPrev().setNext(temp.getNext());
            }
            
            if (temp.getNext() != null) {
                temp.getNext().setPrev(temp.getPrev());
            }
        }
        
        size--; // Decrease the size of the list

        return removedNode.getData(); // Return the data of the removed node

    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        private Node<E> current;

        public DoublyLinkedListIterator() {
            current = (Node<E>) head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E data = current.getData();  // Get data from the current node
            current = current.getNext(); // Move current to the next node
            return data;
        }
        // TODO..DOne
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        //TODO...Done
         // If the list is empty, there's nothing to remove
        if (head == null) {
            return null;
        }

        // If the node to remove is the head node
        if (n == head) {
            head = head.getNext(); // Move head to the next node
            if (head != null) {
                head.setPrev(null); // Set the previous pointer of the new head to null
            }
        } 
        // If the node to remove is the tail node
        else if (n == tail) {
            tail = tail.getPrev(); // Move tail to the previous node
            if (tail != null) {
                tail.setNext(null); // Set the next pointer of the new tail to null
            }
        } 
        // If the node is in the middle
        else {
            Node<E> prevNode = n.getPrev();
            Node<E> nextNode = n.getNext();

            prevNode.setNext(nextNode); // Link the previous node to the next node
            nextNode.setPrev(prevNode); // Link the next node to the previous node
        }

        size--; // Decrease the size of the list
        return n.getData(); // Return the data of the removed node

    }

    public E first() {
        //TODO...Done
        if (head == null) {
            return null; 
        }

        return head.getData();
    }

    public E last() {
        //TODO..DONE
        if (size == 0) {
            return null; 
        }
        return tail.getData();
    }

    @Override
    public E removeFirst() {
        //TODO..Done
        if (size == 0) {
            return null; 
        }
        E removedData = head.getData();

        if (size == 1){
            head = null;
            tail = null;
        }
        else{
            head = head.getNext();
            head.setPrev(null);
        }
        size--;

        return removedData;
    }

    @Override
    public E removeLast() {
        //TODO...Done
        if (size == 0) {
            return null; 
        }
        E removedData = tail.getData();

        if (size == 1){
            head = null;
            tail = null;
        }
        else{
            tail = tail.getPrev();
            tail.setNext(null);
        }
        size--;

        return removedData;
    }

    @Override
    public void addLast(E e) {
        //TODO..Done
        Node<E> newNode = new Node<>(e, tail, null);

        if (size == 0){
            head = newNode;
            tail = newNode;
        }

        tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    @Override
    public void addFirst(E e) {
        // TODO..Done
        Node<E> newNode = new Node<>(e, null, head);
        if (head != null) {
            head.setPrev(newNode);
        }
        head = newNode;
        if (size == 0) { 
            tail = head; 
        }
        size++;
    }

    public String toString() {
        // Check if the list is empty
        if (head == null) {
            return "[]";  // Return empty list representation if there's no data
        }

        StringBuilder sb = new StringBuilder();  // StringBuilder to efficiently build the string
        sb.append("[");  // Start the string with an opening bracket

        Node<E> current = head;  // Start from the head of the list
        while (current != null) {
            sb.append(current.getData());  // Append the current node's data to the string
            if (current.getNext() != null) {
                sb.append(", ");  // Add a comma and space if it's not the last element
            }
            current = current.getNext();  // Move to the next node
        }

        sb.append("]");  // End the string with a closing bracket
        return sb.toString();  // Return the final string representation

    }

    public void reverseInplace() {
        // TODO...Done
        Node<E> curr = head;
        Node<E> temp = null;

        while (curr != null){
            temp = curr.getNext();
            curr.setNext(curr.getPrev());
            curr.setPrev(temp);
            
            curr = curr.getPrev();
        }

        temp = head;
        head = tail;
        tail = temp;

        
    }

    public static void main(String [] args) {
        Integer [] arr = {1,2,3,4,5,6,7,8,9};
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
        for(Integer i : arr) dl.addLast(i);
        System.out.println("forward list: " + dl);
        dl.reverseInplace();
        System.out.println("reverse list: " + dl);
    }
}

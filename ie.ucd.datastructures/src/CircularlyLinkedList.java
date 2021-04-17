import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E>,Iterable<E> {
    //---------------- nested Node class ----------------
    /**
     * Singly linked node, which stores a reference to its element and
     * to the subsequent node in the list.
     */
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e) {
            element = e;
        }
        public Node(E e, Node<E> next) {
            element =e;
            this.next = next;
        }

        public E getElement() {
            return element;
        }
        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
        public void setElement(E e) {
            element = e;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the CircularlyLinkedList
    /** The designated cursor of the list */
    private Node<E> tail = null;                  // we store tail (but not head)

    /** Number of nodes in the list */
    private int size = 0;                         // number of nodes in the list

    /** Constructs an initially empty list. */
    public CircularlyLinkedList() { }             // constructs an initially empty list

    // access methods
    /**
     * Returns the number of elements in the linked list.
     * @return number of elements in the linked list
     */
    public int size() { return size; }

    /**
     * Tests whether the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() { return size == 0; }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        Node<E> curr = tail;
        int index = 0;
        while (index != i) {
            curr = curr.getNext();
            index++;
        }
        return curr.getElement();
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        Node<E> curr = tail;
        int index = 0;
        while (index != i) {
            curr = curr.getNext();
            index++;
        }
        E rep = curr.getElement();
        curr.setElement(e);
        return rep;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        Node<E> curr;
        curr = tail;
        int index = 0;
        while (index != i - 1) {
            curr = curr.getNext();
            index++;
        }
        Node<E> aft = curr.getNext();
        Node<E> newest = new Node<>(e);
        newest.setNext(aft);
        curr.setNext(newest);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        Node<E> curr;
        curr = tail;
        int index = 0;
        while (index != i - 1) {
            curr = curr.getNext();
            index++;
        }
        Node<E> del = curr.getNext();
        E old = del.getElement();
        Node<E> aft = del.getNext();
        curr.setNext(aft);
        return old;
    }

    /**
     * Returns (but does not remove) the first element of the list
     * @return element at the front of the list (or null if empty)
     */
    public E first() {             // returns (but does not remove) the first element
        if (isEmpty()) {return null;}
        Node<E> curr;
        curr = tail;
        return curr.getElement();
    }

    /**
     * Returns (but does not remove) the last element of the list
     * @return element at the back of the list (or null if empty)
     */
    public E last() {              // returns (but does not remove) the last element
        if (isEmpty()) {return null;}
        Node<E> curr;
        curr = tail;
        while (curr.getNext() != tail) {
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    // update methods
    /**
     * Rotate the first element to the back of the list.
     */
    public void rotate() {         // rotate the first element to the back of the list
        if(tail != null) {
            tail = tail.next;      // the old head becomes the new tail
        }
    }

    /**
     * Adds an element to the front of the list.
     * @param e  the new element to add
     */
    public void addFirst(E e) {                // adds element e to the front of the list
        Node<E> newNode = new Node<E>(e);
        if(tail == null) {
            tail = newNode;
            tail.setNext(tail);
        } else {
            Node<E> curr = tail;
            while (curr.getNext() != tail) {
                curr = curr.getNext();
            }
            newNode.setNext(tail);
            tail = newNode;
            curr.setNext(tail);
        }
        size++;
    }

    /**
     * Adds an element to the end of the list.
     * @param e  the new element to add
     */
    public void addLast(E e) {                 // adds element e to the end of the list
        Node<E> newest = new Node<>(e);
        if(tail == null) {
            tail = newest;
            tail.setNext(tail);
        } else {
            Node<E> curr = tail;
            while (curr.getNext() != tail) {
                curr = curr.getNext();
            }
            newest.setNext(tail);
            curr.setNext(newest);
        }
        size++;
    }

    /**
     * Removes and returns the first element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {// removes and returns the first element
        Node<E> curr = tail;
        Node<E> first = tail;
        E num = first.getElement();
        if (isEmpty()) {
            return null;
        }
        if (curr.getNext() == curr) {
            tail = null;
            return null;
        }

        while (curr.getNext() != tail) {
            curr = curr.getNext();
        }
        tail = first.getNext();
        curr.setNext(tail);

        return num;
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
       String result = null;
        if(isEmpty()) return "";

        result = tail.getElement() + ", ";
        Node<E> curr;
        curr = tail;
        Node<E> first;
        first = tail;
        while (curr.getNext() != first) {
            curr = curr.getNext();
            result += curr.getElement();
            if (curr.getNext() != first) {
                result += ", ";
            }
        }
        return result;
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> p;
        Node<E> curr;
        boolean repeat = false;
        public CircularlyLinkedListIterator() {
             p = (Node<E>) tail.getNext();
             curr = (Node<E>) tail;
        }
        @Override
        public boolean hasNext() {
            if (p == tail || (curr == tail && repeat)){
                return false;
            }
            repeat = true;
            return true;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.getNext();
            return res;
        }
    }

    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }


    public static void main(String [] args) {
        //ArrayList<String> all;
        //LinkedList<String> ll;
        CircularlyLinkedList<String> ll = new CircularlyLinkedList<>();

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (String s : alphabet) {
            //ll.addFirst(s);
            ll.addLast(s);
        }
        System.out.print(ll.toString());
        ll.rotate();
        System.out.printf("\n");
        System.out.print(ll.toString());

        //System.out.println(ll.first());
        //System.out.println(ll.last());
        //System.out.println(ll.removeFirst());

        //System.out.println(ll.toString());

       // ll.rotate();
        //ll.rotate();
//        System.out.println(ll.get(3));
//        System.out.println(ll.set(2,"X"));
        //ll.remove(2);

//        for (String s : ll) {
//            System.out.print(s + ", ");
//        }

    }
}

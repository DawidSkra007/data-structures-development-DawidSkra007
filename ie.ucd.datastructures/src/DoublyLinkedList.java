import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E>,Iterable<E> {

    //---------------- nested Node class ----------------
    /**
     * Node of a doubly linked list, which stores a reference to its
     * element and to both the previous and next node in the list.
     */
    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p,Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }
        public E getElement() {
            return element;
        }
        public Node<E>getPrev() {
            return prev;
        }
        public Node<E>getNext() {
            return next;
        }

        public void setElement(E element) {
            this.element = element;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

    } //----------- end of nested Node class -----------

    // instance variables of the DoublyLinkedList
    /** Sentinel node at the beginning of the list */
    private Node<E> header;                    // header sentinel

    /** Sentinel node at the end of the list */
    private Node<E> trailer;                   // trailer sentinel

    /** Number of elements in the list (not including sentinels) */
    private int size = 0;                      // number of elements in the list

    /** Constructs a new empty list. */
    public DoublyLinkedList() {
        header = new Node<>(null,null,null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    // public accessor methods
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
        Node<E> curr;
        curr = header.getNext();
        int index = 0;
        while (index != i) {
            curr = curr.getNext();
            index++;
        }
        return curr.getElement();
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        Node<E> curr;
        E rep;
        curr = header.getNext();
        int index = 0;
        while (index != i) {
            curr = curr.getNext();
            index++;
        }
        rep = curr.getElement();
        curr.setElement(e);
        return rep;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        Node<E> curr;
        curr = header.getNext();
        int index = 0;
        while (index != i - 1) {
            curr = curr.getNext();
            index++;
        }
        Node<E> aft = curr.getNext();
        Node<E> newest = new Node<>(e,curr,aft);
        curr.setNext(newest);
        aft.setPrev(newest);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        Node<E> curr;
        curr = header.getNext();
        int index = 0;
        while (index != i - 1) {
            curr = curr.getNext();
            index++;
        }
        Node<E> del = curr.getNext();
        E deli = del.getElement();
        Node<E> aft = del.getNext();
        curr.setNext(aft);
        aft.setPrev(curr);
        return deli;
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr;
        public DoublyLinkedListIterator() {
            curr = (Node<E>) header.getNext();
        }

        @Override
        public boolean hasNext() {
            return curr.getNext() != null;//only diff for DLL
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.getNext();
            return res;
        }
    }
    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    /**
     * Returns (but does not remove) the first element of the list.
     * @return element at the front of the list (or null if empty)
     */
    public E first() {
        Node<E> curr;
        curr = header;
        if (isEmpty()) return null;
        curr = curr.getNext();
        return curr.getElement();
    }

    /**
     * Returns (but does not remove) the last element of the list.
     * @return element at the end of the list (or null if empty)
     */
    public E last() {
        Node<E> curr;
        curr = trailer;
        if (isEmpty()) return null;
        curr = curr.getPrev();
        return curr.getElement();
    }

    // public update methods
    /**
     * Adds an element to the front of the list.
     * @param e   the new element to add
     */
    public void addFirst(E e) {
        addBetween(e,header,header.getNext());
    }

    /**
     * Adds an element to the end of the list.
     * @param e   the new element to add
     */
    public void addLast(E e) {
       addBetween(e,trailer.getPrev(),trailer);
    }

    /**
     * Removes and returns the first element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {
        if (isEmpty()) return null;
        return remove(header.getNext());
    }

    /**
     * Removes and returns the last element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeLast() {
        if (isEmpty()) return null;
        return remove(trailer.getPrev());
    }

    // private update methods
    /**
     * Adds an element to the linked list in between the given nodes.
     * The given predecessor and successor should be neighboring each
     * other prior to the call.
     *
     * @param predecessor   node just before the location where the new element is inserted
     * @param successor     node just after the location where the new element is inserted
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e,predecessor,successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    /**
     * Removes the given node from the list and returns its element.
     * @param node    the node to be removed (must not be a sentinel)
     */
    private E remove(Node<E> node) {
        Node<E> pre = node.getPrev();
        Node<E> ne = node.getNext();
        ne.setPrev(pre);
        pre.setNext(ne);
        size--;
        return node.getElement();
    }


    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
        String result = null;
        if(isEmpty()) return "";

        result = header.getNext().getElement() + ", ";
        Node<E> curr;
        curr = header.getNext();
        while (curr.getNext() != trailer) {
            curr = curr.getNext();
            result += curr.getElement();
            if (curr.getNext() != trailer) {
                result += ", ";
            }
        }
        return result;
    }

    public static void main(String [] args) {
        //ArrayList<String> all;
        //LinkedList<String> ll;
        DoublyLinkedList<String> ll = new DoublyLinkedList<String>();

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (String s : alphabet) {
            //ll.addFirst(s);
            ll.addLast(s);
        }
        System.out.println(ll.toString());
        //System.out.println(ll.get(1));
        //System.out.println(ll.set(0,"X"));
        // ll.add(2,"X");
        //System.out.println(ll.first());
        //System.out.println(ll.last());
        //System.out.println(ll.remove(3));
//        ll.addFirst("x");
//        ll.addLast("g");
//        System.out.println(ll.removeFirst());
//        System.out.println(ll.removeLast());

        for (String s : ll) {
            System.out.print(s + ", ");
        }
    }
} //----------- end of DoublyLinkedList class -----------

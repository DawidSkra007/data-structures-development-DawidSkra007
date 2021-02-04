import java.util.Iterator;


/**
 * A basic singly linked list implementation.
 */
public class SinglyLinkedList<E> implements Iterable<E>, List<E> {
    //---------------- nested Node class ----------------

    /**
     * Node of a singly linked list, which stores a reference to its
     * element and to the subsequent node in the list (or null if this
     * is the last node).
     */
    private static class Node<E> {

        public E element;
        public Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
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

    // instance variables of the SinglyLinkedList
    private Node<E> head = null; // head node of the list (or null if empty)

    private int size = 0; // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    // access methods

    /**
     * Returns the number of elements in the linked list.
     *
     * @return number of elements in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        Node<E> curr;
        curr = head;
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
        curr = head;
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
        curr = head;
        int index = 0;
        while (index != i - 1) {
            curr = curr.getNext();
            index++;
        }
        Node<E> aft = curr.getNext();
        Node<E> newest = new Node<>(e,aft);
        curr.setNext(newest);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {

        Node<E> curr;
        curr = head;
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
     *
     * @return element at the front of the list (or null if empty)
     */
    public E first() {
        Node<E> curr;
        curr = head;
        if (isEmpty()) return null;
        return curr.getElement();
    }

    /**
     * Returns the last node of the list
     *
     * @return last node of the list (or null if empty)
     */
    public Node<E> getLast() {
        Node<E> curr;
        curr = head;
        if (isEmpty()) return null;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        return curr;
    }

    /**
     * Returns (but does not remove) the last element of the list.
     *
     * @return element at the end of the list (or null if empty)
     */
    public E last() {
        Node<E> curr;
        curr = head;
        if (isEmpty()) return null;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        assert false;
        return curr.getElement();

    }

    // update methods

    /**
     * Adds an element to the front of the list.
     *
     * @param e the new element to add
     */
    public void addFirst(E e) {
        head = new Node<>(e,head);
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param e the new element to add
     */
    public void addLast(E e) {
        Node<E> newest = new Node<E>(e, null);
        Node<E> last = head;
        if (last == null) {
            head = newest;
        } else{
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newest);
        }
        size++;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {
        E first;
        if (head.getElement() == null) {
            return null;
        } else{
            first = head.getElement();
            head = head.getNext();
        }
        size--;
        return first;
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
        String result = null;
        if(isEmpty()) return "";

        result = head.getElement() + ", ";
        Node<E> curr;
        curr = head;
        while (curr.getNext() != null) {
            curr = curr.getNext();
            result += curr.getElement();
            if (curr.next != null) {
                result += ", ";
            }
        }
        return result;
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        private Node<E> curr;
        public SinglyLinkedListIterator() {
            curr = (Node<E>) head;
        }
        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.getNext();
            return res;
        }
    }


    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    public static void main(String[] args) {

        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<>();
        int[] a = {2,6,20,24};
        int[] b = {1,3,5,8,12,19,25};
        for (int x : a) {
            l1.addLast(x);
        }
        for (int y : b) {
            l2.addLast(y);
        }

        SinglyLinkedList<String> sll = new SinglyLinkedList<String>();

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (String s : alphabet) {
            //sll.addFirst(s);
            sll.addLast(s);
        }
       // System.out.println(l1.toString());
       // System.out.println(l2.toString());
//        for (String s : sll) {
//            System.out.print(s + ", ");
//        }
//        System.out.println(sll.last());
//        System.out.println(sll.get(0));
//        System.out.println(sll.get(1));
//        System.out.println(sll.get(3));
//        System.out.println(sll.set(3,"X"));
//        System.out.println(sll.set(6,"X"));
//        System.out.println(sll.remove(3));
//            sll.add(2,"X");
//             System.out.println("\n");
//       System.out.println(sll.toString());
//        System.out.println(sll.removeFirst());
//        System.out.println("\n");
        for (String s : sll) {
            System.out.print(s + ", ");
        }
    }
}


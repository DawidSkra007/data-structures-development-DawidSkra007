public class LinkedDeque<E> implements Deque<E> {
	Node<E> front;
	Node<E> rear;
	private int size = 0;

	public static class Node<E> {
		private Node<E> next;
		private Node<E> prev;
		private E element;

		public Node<E> getPrev() {
			return prev;
		}
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		public E getElement() {
			return element;
		}
		public void setElement(E e) {
			element = e;
		}
	}

				//Doubly Linked list
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return rear == null;
	}

	@Override
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return front.getElement();
	}

	@Override
	public E last() {
		if (isEmpty()) {
			return null;
		}
		return rear.getElement();
	}

	@Override
	public void addFirst(E e) {
		Node<E> newest = new Node<E>();
		newest.setElement(e);
		newest.setNext(front);
		if(front != null) {
			front.setPrev(newest);
		}
		if(front == null) {
			rear = newest;
		}
		front = newest;
		size++;
	}

	@Override
	public void addLast(E e) {
		Node<E> newest = new Node<E>();
		newest.setElement(e);
		newest.setPrev(rear);
		if(rear != null) {
			rear.setNext(newest);
		}
		if(rear == null) {
			front = newest;
		}
		rear = newest;
		size++;
	}

	@Override
	public E removeFirst() {
		if(rear == null){
			return null;
		}
		E t = front.getElement();
		Node<E> temp = rear.getPrev();
		if(temp != null) {
			temp.setNext(null);
		}
		if(temp == null) {
			front = null;
		}
		size--;
		rear = temp;
		return t;
	}

	@Override
	public E removeLast() {
		if(rear == null){
			return null;
		}
		Node<E> temp = rear.getPrev();
		if(temp != null) {
			temp.setNext(null);
		}
		if(temp == null) {
			front = null;
		}
		rear = temp;
		size--;
		return rear.getElement();
	}

}

public class LinkedQueue<E> implements Queue<E> {
	Node<E> front;
	Node<E> rear;
	private int size = 0;

	public static class Node<E> {
		E element;
		Node<E> next;

		public Node(E e) {
			element = e;
			next = null;
		}
		public E getElement() {
			return element;
		}
		public Node<E> getNext() { return next;}
	}

	public LinkedQueue() {
		rear = null;
		front = null;
	}
					//Singly Linked List
	public static void main(String[] args) {
		LinkedQueue<Integer> q = new LinkedQueue<>();
		q.enqueue(10);
		q.enqueue(20);
		System.out.println(q.toString());
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
	public void enqueue(E e) {
		Node<E> oldlast = rear;
		rear = new Node<>(e);
		rear.next = null;
		if (isEmpty()){
			front = rear;
		} else {
			oldlast.next = rear;
		}
		size++;
	}

	@Override
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return front.getElement();
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			return null;
		}
		Node<E> temp = front;
		front = front.getNext();
		if (front == null) {
			rear = null;
		}
		size--;
		return temp.getElement();
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "";
		}
		String result = "[" + front.getElement() + ", ";
		Node<E> curr = front;
		while (curr.getNext() != null) {
			curr = curr.getNext();
			result += curr.getElement();
			if (curr.getNext() != null) {
				result += ", ";
			} else {
				result += "]";
			}
		}
		return result;
	}
}

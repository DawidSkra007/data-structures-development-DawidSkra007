
public class LinkedStack<E> implements Stack<E>{
	private Node<E> top;
	private int size = 0;

	public static void main(String[] args) {
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		stack.push(10);
		stack.push(20);
		System.out.println(stack.size());

	}
	private static class Node<E> {
		public E element;
		public Node<E> next;

		public Node(E e) {
			element = e;
		}

		public E getElement() {
			return element;
		}
		public Node<E> getNext() {
			return next;
		}
	}

	public LinkedStack() {
		top = null;
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
	public void push(E e) {
		Node<E> temp = new Node<>(e);
		temp.next = top;
		top = temp;
		size++;
	}

	@Override
	public E top() {
		if (isEmpty()) {
			return null;
		} else {
			return top.element;
		}
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			return null;
		} else {
			E pop = top.getElement();
			top = top.getNext();
			size--;
			return pop;
		}
	}

	@Override
	public String toString() {
		if(isEmpty()) return "";
		String result = "[" + top.getElement()+", ";
		Node<E> curr = top;

		while (curr.getNext() != null) {
			curr = curr.getNext();
			result += curr.getElement();
			if (curr.next != null) {
				result += ", ";
			} else {
				result += "]";
			}
		}
		return result;
	}
}

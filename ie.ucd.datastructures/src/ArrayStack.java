import java.util.Objects;

public class ArrayStack<E> implements Stack<E> {
	public static final int CAPACITY = 1000;

	private E[] data;
	private int t = -1;

	public ArrayStack() {
		data = (E[]) new Object[CAPACITY];
	}

	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.pop();
		System.out.println(stack.top());
		stack.push(40);
		System.out.println(stack.size());
	}

	@Override
	public int size() {
		return t + 1;
	}

	@Override
	public boolean isEmpty() {
		return (t == -1);
	}

	@Override
	public void push(E e) {
		if (size() >= CAPACITY) {
			System.out.println("Array full.\n");
		} else {
			data[++t] = e;
		}
	}

	@Override
	public E top() {
		if (isEmpty()) {
			return null;
		} else {
			E top = data[t];
			return top;
		}
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			return null;
		} else {
			E pop = data[t];
			t--;
			return pop;
		}
	}

}

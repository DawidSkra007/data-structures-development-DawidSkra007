import java.util.Objects;

public class ArrayStack<E> implements Stack<E> {
	public static final int CAPACITY = 1000;//default capacity

	private E[] data;//holds elements
	private int t = -1;//empty array

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

	@Override//adds element e to the top of the stack
	public void push(E e) {
		if (size() >= CAPACITY) {
			System.out.println("Array full.\n");
		} else {
			data[++t] = e;//increment t before adding element
		}
	}

	@Override//returns the top element of stack without removing it
	public E top() {
		if (isEmpty()) {
			return null;
		} else {
			E top = data[t];
			return top;
		}
	}

	@Override//removes and returns the top element from the stack (null if stack is empty)
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

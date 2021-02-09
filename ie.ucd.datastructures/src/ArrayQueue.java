import java.util.Arrays;

public class ArrayQueue<E> implements Queue<E> {
	private int CAPACITY = 10;
	private int front;
	private int rear;
	private E data[];

	public static void main(String[] args) {
		ArrayQueue<Integer> q = new ArrayQueue<>();
		System.out.println(q.isEmpty());
		q.enqueue(10);
		q.enqueue(20);
		q.enqueue(30);
		//q.dequeue();
		System.out.println(q.toString());
		System.out.println(q.size());
	}
	public ArrayQueue() {
		front = -1;
		rear = -1;
		data = (E[]) new Object[CAPACITY];
	}

	@Override
	public int size() {
		int size = 1;//accounting for rear
		for (int i = front;i != rear; i = (i + 1) % CAPACITY) {
			size++;
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return front == -1;

	}

	@Override
	public void enqueue(E e) {
		if (size() >= CAPACITY) {
			System.out.println("Queue full");
		} else {
			if (front == -1) {
				front = 0;
			}
			rear = (rear + 1) % CAPACITY;
			data[rear] = e;
		}
	}

	@Override
	public E first() {
		if (front == -1) {
			return null;
		} else {
			return data[front];
		}
	}

	@Override
	public E dequeue() {
		E element;
		if (isEmpty()) {
			return null;
		} else {
			element = data[front];
			if (front == rear) {
				front = -1;
				rear = -1;
			} else {
				front = (front + 1) % CAPACITY;
			}
		}
		return element;
	}

	@Override
	public String toString() {
		String result = "";
		if (isEmpty()) {
			System.out.println("Queue empty");
		} else {
			result = "[" ;
			for (int i = front; i != rear; i = (i + 1) % CAPACITY) {
				result += data[i] + ", ";
			}
			result += data[rear] + "";
			result += "]";
 		}
		return result;
	}
}

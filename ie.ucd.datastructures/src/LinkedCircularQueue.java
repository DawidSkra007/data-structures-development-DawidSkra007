/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {
	CircularlyLinkedList<E> queue;

	public LinkedCircularQueue() {
		queue = new CircularlyLinkedList<>();
	}

	public static void main(String[] args) {
		LinkedCircularQueue<Integer> linkedCircularQueue = new LinkedCircularQueue<>();
		linkedCircularQueue.enqueue(1);
		linkedCircularQueue.enqueue(2);
		linkedCircularQueue.enqueue(3);
		linkedCircularQueue.enqueue(4);
		System.out.println(linkedCircularQueue);

		System.out.println("First: " + linkedCircularQueue.first());

		linkedCircularQueue.dequeue();
		System.out.println(linkedCircularQueue);
		linkedCircularQueue.dequeue();
		System.out.println(linkedCircularQueue);
		System.out.println("First: " + linkedCircularQueue.first());
		linkedCircularQueue.rotate();
		System.out.println("Rotated " + linkedCircularQueue);
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		queue.addLast(e);
	}

	@Override
	public E first() {
		return queue.get(0);
	}

	@Override
	public E dequeue() {
		return queue.removeFirst();
	}

	public void rotate(){
		queue.rotate();
	}

	public String toString(){
		return queue.toString();
	}

}

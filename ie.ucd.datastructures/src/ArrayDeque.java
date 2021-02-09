public class ArrayDeque<E> implements Deque<E> {
    private final int CAPACITY = 100;
    int front;
    int rear;
    int size;
    private E data[];

    public ArrayDeque(int size) {
        data = (E[]) new Object[CAPACITY];
        front = -1;
        rear = 0;
        this.size = size;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return ((front == 0 && rear == size - 1) || front == rear + 1);
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[front];
    }

    @Override
    public E last() {
        if (isEmpty()) {
            return null;
        }
        return data[rear];
    }

    @Override
    public void addFirst(E e) {
        if (isFull()) {//if its full
            System.out.println("Queue full\n");
        } else {
            if (front == -1) {
                front = 0;
                rear = 0;
            } else {
                front = front - 1;
            }
            data[front] = e;
        }
    }

    @Override
    public void addLast(E e) {
        if (isFull()) {
            System.out.println("Queue full");
        }
        if (front == -1) {
            front = 0;
            rear = 0;
        } else if (rear == size - 1) {
            rear = 0;//circular array
        } else {
            rear = rear + 1;
        }
        data[rear] = e;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            if (front == rear) {
                front = -1;
                rear = -1;
            } else if (front == size - 1) {
                    front = 0;
            } else {
                front = front + 1;
            }
        }
        return data[front];
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            if (front == rear) {
                front = -1;
                rear = -1;
            } else if (rear == 0) {
                rear = size - 1;
            } else {
                rear = rear - 1;
            }
        }
        return data[rear];
    }
}

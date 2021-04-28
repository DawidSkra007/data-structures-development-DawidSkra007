public class BoundedStack<E> implements Stack<E> {

    private E[] data;//using generic array
    private int t = -1;
    private int capacity;

    public BoundedStack(int capacity) {//user defined capacity
        this.capacity = capacity;
        data = (E[]) new Object[capacity];
    }

    public static void main(String[] args) {
        BoundedStack<Integer> stack = new BoundedStack<>(10);
        for (int i =0; i < 11;i++) {
            stack.push(i);
        }
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
        if (size() >= capacity) {
            throw new StackOverflowError("Array full");
        } else {
            data[++t] = e;//increments t before adding element
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

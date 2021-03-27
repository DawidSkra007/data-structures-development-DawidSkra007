/*
 */

import org.junit.rules.Stopwatch;

import java.util.*;
import java.util.PriorityQueue;


/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	protected ArrayList<Entry<K, V>> heap = new ArrayList<>();
	/**
	 * Creates an empty priority queue based on the natural ordering of its keys.
	 */
	public HeapPriorityQueue() {
		super();
	}

	/**
	 * Creates an empty priority queue using the given comparator to order keys.
	 * 
	 * @param comp comparator defining the order of keys in the priority queue
	 */
	public HeapPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	/**
	 * Creates a priority queue initialized with the respective key-value pairs. The
	 * two arrays given will be paired element-by-element. They are presumed to have
	 * the same length. (If not, entries will be created only up to the length of
	 * the shorter of the arrays)
	 * 
	 * @param keys   an array of the initial keys for the priority queue
	 * @param values an array of the initial values for the priority queue
	 */
	public HeapPriorityQueue(K[] keys, V[] values) {
		super();
		for (int j = 0; j < Math.min(keys.length, values.length); j++) {
			heap.add(new PQEntry<>(keys[j], values[j]));
		}
		heapify();
	}

	// protected utilities
	protected int parent(int j) {
		return (j-1) / 2;
	}

	protected int left(int j) {
		return (2*j) + 1;
	}

	protected int right(int j) {
		return (2*j) + 2;
	}

	protected boolean hasLeft(int j) {
		return left(j) < heap.size();
	}

	protected boolean hasRight(int j) {
		return right(j) < heap.size();
	}

	/** Exchanges the entries at indices i and j of the array list. */
	protected void swap(int i, int j) {
		Entry<K, V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
		return;
	}

	/**
	 * Moves the entry at index j higher, if necessary, to restore the heap
	 * property.
	 */
	protected void upheap(int j) {
		while (j > 0) { // continue until reaching root (at index 0)
			int p = parent(j);
			if (compare(heap.get(j),heap.get(p)) >= 0) { // j key bigger than p key(parent)
				break;
			}
			swap(j,p); // if not then swap them and continue searching
			j = p;
		}
	}

	/**
	 * Moves the entry at index j lower, if necessary, to restore the heap property.
	 */
	protected void downheap(int j) {
		while (hasLeft(j)) {
 			int leftIndex = left(j);
 			int smallChildIndex = leftIndex; // smallChildIndex tells us which way we should go down
 			if (hasRight(j)) {
				int rightIndex = right(j);
				if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
					smallChildIndex = rightIndex; //right way is smaller
				}
			}
			if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0) {// j key bigger than p key(parent)
				break;
			}
			swap(j, smallChildIndex);
			j = smallChildIndex;
		}
	}

	/** Performs a bottom-up construction of the heap in linear time. */
	protected void heapify() {
		int startIndex = parent(size()-1);
		for (int j = startIndex; j >= 0; j--) { //start from the last element
			downheap(j);
		}
	}

	// public methods

	/**
	 * Returns the number of items in the priority queue.
	 * 
	 * @return number of items
	 */
	@Override
	public int size() {
		return heap.size();
	}

	/**
	 * Returns (but does not remove) an entry with minimal key.
	 * 
	 * @return entry having a minimal key (or null if empty)
	 */
	@Override
	public Entry<K, V> min() {
		if (isEmpty()) {
			return null;
		}
		return heap.get(0);
	}

	/**
	 * Inserts a key-value pair and return the entry created.
	 * 
	 * @param key   the key of the new entry
	 * @param value the associated value of the new entry
	 * @return the entry storing the new key-value pair
	 * @throws IllegalArgumentException if the key is unacceptable for this queue
	 */
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException { // add to end of list then upheap
		 checkKey(key);
		 Entry<K, V> newElement  = new PQEntry<>(key,value);
		 heap.add(newElement); // add element at the end fo the list
		 upheap(heap.size()-1);//index of added element
		 return newElement;
	}

	/**
	 * Removes and returns an entry with minimal key.
	 * 
	 * @return the removed entry (or null if empty)
	 */
	@Override
	public Entry<K, V> removeMin() { // swaps the last element and root element, removes root element then dowheaps swapped element
		if (heap.isEmpty()) {
			return null;
		}
		Entry<K,V> old = heap.get(0);
		swap(0,heap.size()-1);
		heap.remove(heap.size()-1);
		downheap(0);
		return old;
	}
	
	public String toString() {
		return heap.toString();
	}

	/** Used for debugging purposes only */
	private void sanityCheck() {
		for (int j = 0; j < heap.size(); j++) {
			int left = left(j);
			int right = right(j);
			//System.out.println("-> " +left + ", " + j + ", " + right);
			Entry<K, V> e_left, e_right;
			e_left = left < heap.size() ? heap.get(left) : null;
			e_right = right < heap.size() ? heap.get(right) : null;
			if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
				System.out.println("Invalid left child relationship");
				System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
			}
			if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
				System.out.println("Invalid right child relationship");
				System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
			}
		}
	}

	public static < T > String toBinaryTreeString(HeapPriorityQueue<Integer, Integer> pq) {
		LinkedBinaryTree<T> bt = new LinkedBinaryTree<>();
		bt.createLevelOrder((T[]) new ArrayList<T>((Collection<? extends T>) pq).toArray());
		BinaryTreePrinter< T > btp = new BinaryTreePrinter<>(bt);
		return btp.print();
	}

	public static <E> void pqSort(ArrayList<E> S, HeapPriorityQueue<E, E> P) {
		int n = S.size();
		for (int j=0; j < n; j++) {
			E element = S.remove(0);
			P.insert(element, null); // places elements from array S as Keys in Priority queue P
		}
		for (int j=0; j < n; j++) {
			E element = P.removeMin().getKey();
			S.add(element);
		}
	}

	
	public static void main(String [] args) {
		Random rand = new Random();
		Integer[] rands = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};
		Integer[] keys = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		HeapPriorityQueue<Integer,Integer> pq = new HeapPriorityQueue<>(keys,rands);
		PriorityQueue<Integer> pqe = new PriorityQueue<>();
		//Integer [] rands = new Integer[]{44,17,88,8,32,65,97,28,54,82,93,21,29,76,68,80};
		
//		for(Integer i : rands) {
//			pq.add(i);
//			System.out.println(toBinaryTreeString(pq));
//		}
		System.out.println(toBinaryTreeString(pq));

		int n = 1000;
		ArrayList<Integer> arr = new ArrayList<>(n);
		for (int i=0; i < n;i++) {
			arr.add(rand.nextInt(100));
		}
		HeapPriorityQueue<Integer,Integer> p = new HeapPriorityQueue<>();

		long start = System.currentTimeMillis();
		pqSort(arr,p);
		long end = System.currentTimeMillis();

		long timeElapsed = end - start;
		System.out.println(arr.toString());
		System.out.println("Time Elapased: " + timeElapsed);
	}


}

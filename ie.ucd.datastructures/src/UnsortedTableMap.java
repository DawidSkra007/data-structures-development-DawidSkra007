import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a map using an unsorted table.
 */

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
	/** Underlying storage for the map of entries. */
	private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

	/** Constructs an initially empty map. */
	public UnsortedTableMap() {
	}

	// private utility
	/** Returns the index of an entry with equal key, or -1 if none found. */
	private int findIndex(K key) {
		for(int i = 0; i < table.size();i++) {
			if(table.get(i).getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}

	// public methods
	/**
	 * Returns the number of entries in the map.
	 * 
	 * @return number of entries in the map
	 */
	@Override
	public int size() {
		return table.size();
	}

	/**
	 * For testing purposes
	 *
	 * @return 0
	 */
	public int getCollisions() {
		return 0;
	}

	/**
	 * Returns the value associated with the specified key, or null if no such entry
	 * exists.
	 * 
	 * @param key the key whose associated value is to be returned
	 * @return the associated value, or null if no such entry exists
	 */
	@Override
	public V get(K key) {
		int j = findIndex(key);
		if (j == -1) {//not found
			return null;
		}
		return table.get(j).getValue();
	}

	/**
	 * Associates the given value with the given key. If an entry with the key was
	 * already in the map, this replaced the previous value with the new one and
	 * returns the old value. Otherwise, a new entry is added and null is returned.
	 * 
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with the key (or null, if no such
	 *         entry)
	 */
	@Override
	public V put(K key, V value) {
		int j = findIndex(key);
		if (j == -1) {//not found
			table.add(new MapEntry<>(key,value));
			return null;
		} else {//already in map
			V old = table.get(j).getValue();
			table.get(j).setValue(value);
			return old;
		}
	}

	/**
	 * Removes the entry with the specified key, if present, and returns its value.
	 * Otherwise does nothing and returns null.
	 * 
	 * @param key the key whose entry is to be removed from the map
	 * @return the previous value associated with the removed key, or null if no
	 *         such entry exists
	 */
	@Override
	public V remove(K key) {
		int j = findIndex(key);
		if (j == -1) {//not found
			return null;
		} else { // found entry to remove
			V old = table.get(j).getValue();
			if (j != size() - 1) {//if not removing last element, it places that entry last then removes it
				table.set(j,table.get(size() - 1));
			}
			table.remove(size() - 1);
			return old;
		}
	}

	// ---------------- nested EntryIterator class ----------------
	private class EntryIterator implements Iterator<Entry<K, V>> {
		private int j = 0;

		public boolean hasNext() {
			return j < table.size();
		}

		public Entry<K, V> next() {
			if(j == table.size()) {
				throw new NoSuchElementException();
			}
			return table.get(j++);
		}

		public void remove() {
			throw new UnsupportedOperationException("remove not supported");
		}
	} // ----------- end of nested EntryIterator class -----------

	// ---------------- nested EntryIterable class ----------------
	private class EntryIterable implements Iterable<Entry<K, V>> {
		public Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}
	} // ----------- end of nested EntryIterable class -----------

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}
	
	public String toString() {
		return table.toString();
	}
}

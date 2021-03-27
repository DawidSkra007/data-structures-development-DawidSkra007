import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    /**
     * Creates a hash table with capacity 11 and prime factor 109345121.
     */
    public ChainHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ChainHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    public static void main(String[] args) throws FileNotFoundException {
        //Map<Integer, String> m1 = new HashMap<Integer, String>();
//        ChainHashMap<Integer, String> m = new ChainHashMap<Integer, String>();
//
//        m.put(1, "One");
//        m.put(10, "Ten");
//        m.put(11, "Eleven");
//        m.put(20, "Twenty");
//
//
//        System.out.println("m: " + m);
//
//        m.remove(11);
//        System.out.println("m: " + m);
        //File words.txt Q3
//        File file = new File("ie.ucd.datastructures/src/sample_text.txt");
//        Map<String, Integer> words = new ChainHashMap<>();
//        Scanner scan = new Scanner(file).useDelimiter("[^a-zA-Z]+");
//        while(scan.hasNext()) {
//            String word = scan.next().toLowerCase();
//            Integer count = words.get(word);
//            if (count == null) {
//                count = 0;
//            }
//            words.put(word,count + 1);
//        }
        //System.out.println(words);
//        ArrayList<Entry<String,Integer>> large = new ArrayList<>(5);
//        for (int i = 0;i < 10;i++) {
//            int max = 0;
//            String word = null;
//            Entry<String, Integer> ent = null;
//            for (Entry<String, Integer> entry : words.entrySet()) {
//                if (entry.getValue() > max) {
//                    max = entry.getValue();
//                    word = entry.getKey();
//                    ent = entry;
//                }
//            }
//            //large.add(ent);
//            System.out.println("Word: " + word + " " + max);
//            words.remove(word);
//        }
        //File words.txt Q4
        File file2 = new File("ie.ucd.datastructures/src/words.txt");
        Scanner scanFile2 = new Scanner(file2);
        Map<String, Integer> wordFile2 = new ChainHashMap<>();
        while (scanFile2.hasNext()) { // added all words into map, with 1 as their frequency
            String word = scanFile2.next().toLowerCase();
            wordFile2.put(word,1);
        }
        //System.out.println(wordFile2);
        System.out.println("Number of collisions: " + wordFile2.getCollisions());
       // counting number of collisions

    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
       table = new UnsortedTableMap[capacity];
    }

    /**
     * Returns value associated with key k in bucket with hash value h. If no such
     * entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    @Override
    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K,V> bucket = table[h];
        if (bucket == null) {
            return null;
        }
        return bucket.get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h, returning the
     * previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K,V> bucket = table[h];
        if (bucket == null) {
            bucket = new UnsortedTableMap<>();
            table[h] = bucket;
        } else {
            collisions++;
        }
        n++;
        return bucket.put(k,v);
    }

    /**
     * Removes entry having key k from bucket with hash value h, returning the
     * previously associated value, if found.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K,V> bucket = table[h];
        if (bucket == null) {
            return null;
        }
        int oldSize = bucket.size();
        V ans = bucket.remove(k);
        n -= (oldSize - bucket.size());
        return ans;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for (int h = 0; h < capacity;h++) {
		    if (table[h] != null) {
		        for (Entry<K,V> entry : table[h].entrySet()) {
		            buffer.add(entry);
                }
            }
        }
        return buffer;
    }

    public String toString() {
        return entrySet().toString();
    }
}

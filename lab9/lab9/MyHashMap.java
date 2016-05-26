package lab9;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
	
    private static class Entry<K, V> {
		final K key;
		V val;
		Entry<K, V> next;

		Entry(K k, V v, Entry<K, V> n) {
			key = k;
			val = v;
			next = n;
		}

		Entry(K k, V v) {
			key = k;
			val = v;
			next = null;
		}
	}

	private Set<K> st;
	private int buckets;
	private double loadFactor;
	private int size;
	private Entry<K, V>[] hashmap;

	public MyHashMap() {
		st = new HashSet<K>(); 
		buckets = 1000;
		loadFactor = 0.75;
		size = 0;
		hashmap = new Entry[buckets];
	}

	public MyHashMap(int initialSize) {
		st = new HashSet<K>();
		buckets = initialSize;
		loadFactor = 0.75;
		size = 0;
		hashmap = new Entry[buckets];
	}

	public MyHashMap(int initialSize, double loadFactor) {
		st = new HashSet<K>();
		buckets = initialSize;
		this.loadFactor = loadFactor;
		size = 0;
		hashmap = new Entry[buckets];
	}

	// resize the hash table to have the given number of chains b rehashing all of the keys
    private void resize(int chains) {
        Entry<K, V>[] newhashMap = new Entry[chains];
        Entry<K, V>[] oldhashMap = hashmap;
        int oldbuckets = buckets;
        buckets = chains;
        hashmap = newhashMap;

        for (int i = 0; i < oldbuckets; i++) {
        	Entry<K, V> head = oldhashMap[i];
        	while(head != null) {
        		put(head.key, head.val);
        		head = head.next;
        	}
        }
		oldhashMap = null;
    }

	// hash value between 0 and M-1
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % buckets;
    }

	private Entry<K, V> getNode(int storageIndex, K key) {
		Entry<K, V> node = hashmap[storageIndex];
		while (node != null) {
			if (node.key.equals(key)) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	/** Removes all of the mappings from this map. */
    public void clear() {
    	size = 0;
    	hashmap = new Entry[buckets];
    	st = new HashSet<K>();
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
    	if (key == null) throw new NullPointerException("argument to containsKey() is null");
    	int hashcode = hash(key);
    	Entry<K, V> node = getNode(hashcode, key);
    	if(node == null)
    		return false;
    	else
    		return true;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    public V get(K key) {
    	if (key == null) throw new NullPointerException("argument to get() is null");
    	int hashcode = hash(key);
    	Entry<K, V> node = getNode(hashcode, key);
    	if(node == null)
    		return null;
    	else
    		return node.val;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
    	return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
    	int hashcode = hash(key);
    	Entry<K, V> node = getNode(hashcode, key);
		if(node != null) {
			node.val = value;
			return;
		}
		hashmap[hashcode] = new Entry<K, V>(key, value, hashmap[hashcode]);
    	st.add(key);
    	size++;
    	if((double) (size / buckets) > loadFactor) resize(2 * buckets);
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
    	return st;
    }  

    private class MyHashMapIter implements Iterator<K> {
    	private Iterator<K> stiter;

    	public MyHashMapIter() {
    		stiter = st.iterator();
    	}

    	@Override
    	public boolean hasNext() {
    		return stiter.hasNext();
    	}

    	@Override
    	public K next() {
    		K key = stiter.next();
    		return key;
    	}
    }

    @Override
    public Iterator<K> iterator() {
    	return new MyHashMapIter();
    }
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an 
     * UnsupportedOperationException. */
 	@Override   
    public V remove(K key) {
		int hashcode = hash(key);
		Entry<K, V> node = hashmap[hashcode];

		V value = null;
		if(node == null) return null;
		if(node.key.equals(key)) {
			hashmap[hashcode] = node.next;
			value = node.val;
			size--;
			st.remove(key);
		}else {
			while(node.next != null) {
				if(node.next.equals(key)) {
					node.next = node.next.next;
					value = node.val;
					size--;
					st.remove(key);
					break;
				}
				node = node.next;
			}
		}
		return value;

    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
		int hashcode = hash(key);
		Entry<K, V> node = hashmap[hashcode];
		if(node == null) return null;

		V res = null;
		if(node.key.equals(key) && node.val.equals(value)) {
			hashmap[hashcode] = node.next;
			size--;
			res =value;
			st.remove(key);
		}else {
			while (node.next != null) {
				if (node.next.key.equals(key) && node.next.val.equals(value)) {
					node.next = node.next.next;
					size--;
					res = value;
					st.remove(key);
					break;
				}
				node = node.next;
			}
		}
		return res;
    }


}
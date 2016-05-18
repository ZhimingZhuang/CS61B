package lab8;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
	
	private class TreeNode {
		public TreeNode(K k, V v) {
			key = k;
			val = v;
			left = null;
			right = null;
		}

		public K key;
		public V val;
		public TreeNode left;
		public TreeNode right;
	}

	private TreeNode root;
	private int size;

	public BSTMap() {
		clear();
	}

	/** Removes all of the mappings from this map. */
    @Override
    public void clear() {
    	root = null;
    	size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
    	return containHelper(root, key);
    }

    private boolean containHelper(TreeNode cur, K key) {
    	if(cur == null) 
    		return false;
    	
    	if(key.compareTo(cur.key) == 0) 
    			return true;
    	else if(key.compareTo(cur.key) < 0)
    			return containHelper(cur.left, key);
    	else 
    		return containHelper(cur.right, key);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    @Override
    public V get(K key) {
    	return getHelper(root, key);
    }

    private V getHelper(TreeNode cur, K key) {
    	if(cur == null) return null;
    	if(key.compareTo(cur.key) == 0) 
    		return cur.val;
    	else if(key.compareTo(cur.key) < 0)
    			return getHelper(cur.left, key);
    	else
    		return getHelper(cur.right, key);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
    	return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
    	root = putHelper(root, key, value);
    }

    public void printInorder() {
    	dfsInorder(root);
    }
    private void dfsInorder(TreeNode cur) {
    	if(cur == null) return;
    	System.out.println(cur.key);
    	dfsInorder(cur.left);
    	dfsInorder(cur.right);
    }

    private TreeNode putHelper(TreeNode cur, K key, V value) {
    	if(cur == null) {
    		cur = new TreeNode(key, value);
    		size++;
    	}else if(key.compareTo(cur.key) < 0) {
    		cur.left = putHelper(cur.left, key, value);
    	}else if(key.compareTo(cur.key) > 0) {
    		cur.right = putHelper(cur.right, key, value);
    	}else if(key.compareTo(cur.key) == 0) {
    		cur.val = value;
    	}
    	return cur;
    }

    @Override
    public Iterator<K> iterator() {
         return new BSTMapIter();
    }

    private class BSTMapIter implements Iterator<K> {
    	private Queue<TreeNode> que;
    	public BSTMapIter() {
    		que = new LinkedList<TreeNode>();
    		que.offer(root);
    	}

    	@Override
    	public boolean hasNext() {
    		return !que.isEmpty();
    	}

    	@Override
    	public K next() {
    		TreeNode cur = que.poll();
    		K keyNext = cur.key;
    		if(cur.left != null) que.offer(cur.left);
    		if(cur.right != null) que.offer(cur.right);
    		return keyNext;
    	}

    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
    	Set<K> st = new HashSet<K>();
    	dfsInorder(root, st);
    	return st;
    } 

   	private void dfsInorder(TreeNode cur, Set<K> st) {
   		if(cur == null) return;
   		st.add(cur.key);
   		dfsInorder(cur.left, st);
   		dfsInorder(cur.right, st);
   	} 

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an 
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
    	TreeNode pre = null;
    	TreeNode cur = root;
    	while(cur != null) {
    		if(key.compareTo(cur.key) == 0)
    			break;
    		else if(key.compareTo(cur.key) < 0) {
    			pre = cur;
    			cur = cur.left;
    		}else {
    			pre = cur;
    			cur = cur.right;
    		}
    	}
    	if(cur == null) return null;	
    	V deleteNodeVal = cur.val;

    	// if delete root
    	if(pre == null) {
    		if(root.left == null && root.right == null) {
    			root = null;
    		}else if(root.left == null && root.right != null) {
    			root = root.right;
    		}else if(root.left != null && root.right == null) {
    			root = root.left;
    		}else {
    			TreeNode tmp = root.left;
    			TreeNode tmpPre = root;
    			while(tmp.right != null) {
    				tmpPre = tmp;
    				tmp = tmpPre.right;
    			}
    			root.key = tmp.key;
    			root.val = tmp.val;
    			if(tmpPre == root)
    				root.left = tmp.left;
				else
   					tmpPre.right = null;
    		}
    		return deleteNodeVal;
    	}

    	// if cur has no children
    	if(cur.left == null && cur.right == null) {
    		if(pre.left == cur) 
    			pre.left = null;
    		else if(pre.right == cur) 
    			pre.right = null;
    	}else if(cur.left == null && cur.right != null) {
    	// if cur has only one child
    		if(pre.left == cur) 
    			pre.left = cur.right;
    		else if(pre.right == cur)
    			pre.right = cur.right;
    	}else if(cur.left != null && cur.right == null) {
    		if(pre.left == cur)
    			pre.left = cur.left;
    		else if(pre.right == cur)
    			pre.right = cur.left;
    	}else {
    	// if cur has 2 children
    		TreeNode tmp = cur.left;
    		TreeNode tmpPre = cur;
    		while(tmp.right != null) {
    			tmpPre = tmp;
    			tmp = tmp.right;
    		}
    		cur.key = tmp.key;
    		cur.val = tmp.val;
    		tmpPre.right = null;
    	}
    	return deleteNodeVal;

    }


    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
    	throw new UnsupportedOperationException();
    }
}
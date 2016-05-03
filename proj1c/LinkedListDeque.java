/**
 * Created by zhimingzhuang on 4/28/16.
 */
public class LinkedListDeque<Item> implements Deque<Item>{

    /* Class of Node*/
    public class Node {
        public Item val;
        public Node next;
        public Node pre;

        public Node(Item i) {
            val = i;
            next = null;
            pre = null;
        }
    }

    private Node sentinel;
    private int size;

    /* Creat an empty deque*/
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    /*  Adds an item to the front of the Deque.*/
    public void addFirst(Item x) {
        Node cur = new Node(x);
        cur.next = sentinel.next;
        cur.pre = sentinel;
        sentinel.next.pre = cur;
        sentinel.next = cur;
        size++;
    }

    @Override
    /* Adds an item to the back of the Deque.*/
    public void addLast(Item x) {
        Node cur = new Node(x);
        cur.next = sentinel;
        cur.pre = sentinel.pre;
        sentinel.pre.next = cur;
        sentinel.pre = cur;
        size++;
    }

    @Override
    /* Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        if(size == 0)
            return true;
        else
            return false;
    }

    @Override
    /* Returns the number of items in the Deque.*/
    public int size() {
        return size;
    }

    @Override
    /* Prints the items in the Deque from first to last, separated by a space.*/
    public void printDeque() {
        Node p = sentinel.next;
        while(p != sentinel) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    /* Removes and returns the item at the front of the Deque. If no such item exists, returns null.*/
    public Item removeFirst() {
        if(sentinel.next == sentinel)
            return null;
        Node deleNode = sentinel.next;
        deleNode.next.pre = sentinel;
        sentinel.next = deleNode.next;

        deleNode.next = null;
        deleNode.pre = null;
        size--;
        return deleNode.val;
    }

    @Override
    /* Removes and returns the item at the back of the Deque. If no such item exists, returns null.*/
    public Item removeLast() {
        if(sentinel.pre == sentinel)
            return null;
        Node deleNode = sentinel.pre;
        deleNode.pre.next = sentinel;
        sentinel.pre = deleNode.pre;

        deleNode.pre = null;
        deleNode.next = null;
        size--;
        return deleNode.val;
    }

    @Override
    /* Gets the item at the given index, where 0 is the front, 1 is the next item,
        and so forth. If no such item exists, returns null.*/
    public Item get(int index) {
        if(index < 0) return null;

        Node p = sentinel.next;
        int i = -1;
        while(p != sentinel) {
            i++;
            if(i == index) break;
            p = p.next;

        }
        if(i < index) return null;
        return p.val;
    }

    
    /* Same as get, but uses recursion*/
    public Item getRecursive(int index) {
        if(index < 0 || size == 0) return null;
        Node res = recurHelper(sentinel.next, index);
        return res.val;

    }

    private Node recurHelper(Node cur, int index) {
        if(index == 0)
            return cur;
        else
            return recurHelper(cur.next, index - 1);
    }
}

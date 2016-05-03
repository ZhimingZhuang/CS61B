/**
 * Created by zhimingzhuang on 4/28/16.
 */
public class ArrayDeque<Item> implements Deque<Item>{
    private Item[] items;
    private int size;
    private int nextfirst;
    private int nextlast;

    /* Creates an empty list. */
    public ArrayDeque() {
        size = 0;
        items = (Item[]) new Object[8];
        nextfirst = 3;
        nextlast = 4;
    }

    @Override
    /*  Adds an item to the front of the Deque*/
    public void addFirst(Item x) {
        if(nextfirst == 0) {
            Item[] newitems = (Item[]) new Object[items.length * 2];
            System.arraycopy(items, 0, newitems, items.length, items.length);
            nextfirst = nextfirst + items.length;
            nextlast = nextlast + items.length;
            items = newitems;
        }
        items[nextfirst] = x;
        nextfirst--;
        size++;

    }

    @Override
    /* Adds an item to the back of the Deque.*/
    public void addLast(Item x) {
        if(nextlast == items.length - 1) {
            Item[] newitems = (Item[]) new Object[items.length * 2];
            System.arraycopy(items, 0, newitems, 0, items.length);
            items = newitems;
        }
        items[nextlast] = x;
        nextlast++;
        size++;
    }

    @Override
    /* Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }
    /* Returns the number of items in the Deque.*/
    public int size() {
        return size;
    }

    @Override
    /* Prints the items in the Deque from first to last, separated by a space.*/
    public void printDeque() {
        for(int i = nextfirst + 1; i < nextfirst + 1 + size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    @Override
    /* Removes and returns the item at the front of the Deque. If no such item exists, returns null.*/
    public Item removeFirst() {
        if(size == 0) return null;

        Item dele  = items[++nextfirst];
        items[nextfirst] = null;
        size--;
        return dele;
    }

    @Override
    /* Removes and returns the item at the back of the Deque. If no such item exists, returns null.*/
    public Item removeLast() {
        if(size == 0) return null;
        Item dele = items[--nextlast];
        items[nextlast] = null;
        size--;
        return dele;
    }

    @Override
    /* Gets the item at the given index, where 0 is the front, 1 is the next item,
       and so forth. If no such item exists, returns null.*/
    public Item get(int index) {
        if(index >= size) return null;
        return items[nextfirst + 1 + index];
    }
}

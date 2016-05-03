import static org.junit.Assert.assertEquals;

/**
 * Created by zhimingzhuang on 5/3/16.
 */
public class TestLinkedListDeque1B {
    public static void main(String[] args) {
        System.out.println("Running tests.");
        System.out.println("=================");
        StudentLinkedListDeque<Integer> arrdeq = new StudentLinkedListDeque<Integer>();
        //LinkedListDequeSolution<Integer> arrdeq = new LinkedListDequeSolution<>();

        System.out.println("addLast(5)");
        arrdeq.addLast(5);

        System.out.println("get(0)");
        assertEquals(5, (int)arrdeq.get(0));

        System.out.println("size()");
        assertEquals(1, arrdeq.size());

        System.out.println("addFirst(4)");
        arrdeq.addFirst(4);

        System.out.println("size()");
        assertEquals(2, arrdeq.size());

        System.out.println("get(0)");
        assertEquals(4, (int)arrdeq.get(0));

        System.out.println("get(1)");
        assertEquals(5, (int)arrdeq.get(1));

        System.out.println("addFirst(3)");
        arrdeq.addFirst(3);

        System.out.println("size()");
        assertEquals(3, arrdeq.size());

        System.out.println("addLast(6)");
        arrdeq.addLast(6);

        System.out.println("size()");
        assertEquals(4, arrdeq.size());

        System.out.println("get(0)");
        assertEquals(3, (int)arrdeq.get(0));
        System.out.println("get(1)");
        assertEquals(4, (int)arrdeq.get(1));
        System.out.println("get(2)");
        assertEquals(5, (int)arrdeq.get(2));
        System.out.println("get(3)");
        assertEquals(6, (int)arrdeq.get(3));
        System.out.println("get(4)");

        System.out.println("removeFirst()");
        assertEquals(3, (int)arrdeq.removeFirst());

        System.out.println("removeLast()");
        assertEquals(6, (int)arrdeq.removeLast());

        System.out.println("size()");
        assertEquals(2, arrdeq.size());

        System.out.println("isEmpty()");
        assertEquals(false, arrdeq.isEmpty());

        System.out.println("get(0)");
        assertEquals(4, (int)arrdeq.get(0));

        System.out.println("get(1)");
        assertEquals(5, (int)arrdeq.get(1));

        System.out.println("removeLast()");
        assertEquals(5, (int)arrdeq.removeLast());

        System.out.println("removeLast()");
        assertEquals(4, (int)arrdeq.removeLast());


        System.out.println("isEmpty()");
        assertEquals(true, arrdeq.isEmpty());

        System.out.println("removeLast()");
        assertEquals(null, arrdeq.removeLast());

        System.out.println("removeFirst()");
        assertEquals(null, arrdeq.removeFirst());

        System.out.println("addFirst(10)");
        arrdeq.addFirst(10);

        System.out.println("addFirst(9)");
        arrdeq.addFirst(9);

        System.out.println("addFirst(8)");
        arrdeq.addFirst(8);

        System.out.println("addFirst(7)");
        arrdeq.addFirst(7);

        System.out.println("addFirst(6)");
        arrdeq.addFirst(6);

        System.out.println("addFirst(5)");
        arrdeq.addFirst(5);

        System.out.println("addFirst(4)");
        arrdeq.addFirst(4);

        System.out.println("addFirst(3)");
        arrdeq.addFirst(3);

        System.out.println("addFirst(2)");
        arrdeq.addFirst(2);

        System.out.println("addFirst(1)");
        arrdeq.addFirst(1);

        System.out.println("addLast(11)");
        arrdeq.addLast(11);

        System.out.println("addLast(12)");
        arrdeq.addLast(12);

        System.out.println("get(4)");
        assertEquals(5, (int)arrdeq.get(4));

        System.out.println("removeLast()");
        assertEquals(12, (int)arrdeq.removeLast());
        System.out.println("removeLast()");
        assertEquals(11, (int)arrdeq.removeLast());
        System.out.println("removeLast()");
        assertEquals(10, (int)arrdeq.removeLast());
        System.out.println("removeLast()");
        assertEquals(9, (int)arrdeq.removeLast());
        System.out.println("removeLast()");
        assertEquals(8, (int)arrdeq.removeLast());

        System.out.println("get(0)");
        assertEquals(1, (int)arrdeq.get(0));
        System.out.println("get(1)");
        assertEquals(2, (int)arrdeq.get(1));
        System.out.println("get(2)");
        assertEquals(3, (int)arrdeq.get(2));
        System.out.println("get(3)");
        assertEquals(4, (int)arrdeq.get(3));
        System.out.println("get(4)");
        assertEquals(5, (int)arrdeq.get(4));
        System.out.println("get(5)");
        assertEquals(6, (int)arrdeq.get(5));
        System.out.println("get(6)");
        assertEquals(7, (int)arrdeq.get(6));

        System.out.println("size()");
        assertEquals(7, arrdeq.size());
    }
}

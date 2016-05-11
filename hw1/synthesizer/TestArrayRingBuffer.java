package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> x = new ArrayRingBuffer(4);
        x.enqueue(1.1);
        x.enqueue(2.2);
        x.enqueue(3.3);
        x.enqueue(4.4);
        assertEquals(x.peek(), 1.1, 0.01);
        x.dequeue();
        assertEquals(x.peek(), 2.2, 0.01);
        assertEquals(x.fillCount(), 3);
        x.enqueue(5.5);
        assertEquals(x.fillCount(), 4);
        assertEquals(x.peek(), 2.2, 0.01);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 

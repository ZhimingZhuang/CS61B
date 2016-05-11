package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
	public static final String MAGIC_WORD = "";

	@Test
	public void testBasics() {
		Clorus c = new Clorus(2);
		assertEquals(2, c.energy(), 0.001);
		assertEquals(new Color(34, 0, 231), c.color());
		c.move();
        assertEquals(1.97, c.energy(), 0.001);
        c.move();
        assertEquals(1.94, c.energy(), 0.001);
        c.stay();
        assertEquals(1.93, c.energy(), 0.001);
        c.stay();
        assertEquals(1.92, c.energy(), 0.001);		
	}

	@Test 
	public void testReplicate() {
		Clorus c = new Clorus(2);
		Clorus newc = c.replicate();
		assertNotSame(c, newc);
		assertEquals(c.energy(), 1, 0.01);
		assertEquals(newc.energy(), 1, 0.01);
	}

	@Test
	public void testChoose() {
		Clorus c = new Clorus(2);
		HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
		surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        c.stay();
        assertEquals(expected, actual);
        assertEquals(c.energy(), 1.99, 0.001);

        surrounded.clear();

        Plip p = new Plip();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, p);
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.ATTACK, Direction.RIGHT);
        c.attack(p);
        assertEquals(c.energy(), 2.99, 0.001);
        c.stay();

        surrounded.clear();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Empty());
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);
        assertEquals(actual, expected);
        c.replicate();
        assertEquals(c.energy(), 1.49, 0.001);

	}
	public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestClorus.class));
    }

}
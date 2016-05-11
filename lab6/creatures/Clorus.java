package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature {

	/** red color*/
	private int r;
	/** green color*/
	private int g;
	/** blue color*/
	private int b;
    /** fraction of energy to retain when replicating. */
    private double repEnergyRetained = 0.5;
    /** fraction of energy to bestow upon offspring. */
    private double repEnergyGiven = 0.5;

    public Clorus(double e) {
    	super("clorus");
    	r = 0;
    	g = 0;
    	b = 0;
    	energy = e;
    }

    /** creates a clorus with energy equal to 1. */
    public Clorus() {
    	this(1);
    }

    /** Should return a color with red = 34, green = 0, blue = 231 */
    public Color color() {
    	r = 34;
    	g = 0;
    	b = 231;
    	return color(r, g, b);
    }

    /** Clorus should lose 0.03 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = energy - 0.03;
        energy = Math.max(0, energy);
    }

    /** Plips gain 0.01 energy when staying due to photosynthesis. */
    public void stay() {
        energy = energy - 0.01;
    }

    /** If a Clorus attacks another creature, it should gain that creature's energy. */
    public void attack(Creature c) {
    	energy += c.energy();
    }

    /** Clorus and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Plip.
     */
    public Clorus replicate() {
        double originalEnergy = energy;
        energy = originalEnergy * repEnergyRetained;
        double babyEnergy = originalEnergy * repEnergyGiven;
        return new Clorus(babyEnergy);
    }

    /**  If there are no empty squares, the Clorus will STAY (even if there are Plips nearby they could attack).
	 *   Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     *   Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
     *   Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
    	List<Direction> empties = getNeighborsOfType(neighbors, "empty");
    	List<Direction> plips = getNeighborsOfType(neighbors, "plip");

    	if(empties.size() == 0) {
    		return new Action(Action.ActionType.STAY);
    	}else if(plips.size() > 0) {
    		Direction attDir = HugLifeUtils.randomEntry(plips);
    		return new Action(Action.ActionType.ATTACK, attDir);
    	}else if(energy >= 1.0) {
    		Direction repDir = HugLifeUtils.randomEntry(empties);
    		return new Action(Action.ActionType.REPLICATE, repDir);
    	}
    	Direction moveDir = HugLifeUtils.randomEntry(empties);
    	return new Action(Action.ActionType.MOVE, moveDir);
    }


}
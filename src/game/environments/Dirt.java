package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.item.GoldenRunes;

/**
 * A class that represents bare dirt.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Albert Liesman
 *
 */
public class Dirt extends SpawningGround {
	private final static int GOLDEN_RUNE_SPAWN_RATE = 5; // 0.5%
	/**

	 Constructor for Dirt
	 */
	public Dirt() {
		super('.');
	}

	/**
	 * Overides the tick method to execute spawn GoldenRune at 0.5% chance randomly all over dirt.
	 *
	 * @param location the location of the cliff
	 */
	public void tick(Location location) {
		super.spawnItem(new GoldenRunes(), GOLDEN_RUNE_SPAWN_RATE, location);
	}
}

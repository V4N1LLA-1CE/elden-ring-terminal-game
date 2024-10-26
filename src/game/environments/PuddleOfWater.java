package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.GiantCrab;
import game.enemies.GiantCrayfish;

/**
 * Graveyards class represents a spawning ground of GiantCrab and Giant Crayfish enemies in the game.
 * It extends SpawningGround class and overrides the tick method to spawn enemies.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class PuddleOfWater extends SpawningGround {
    private final static int GIANT_CRAB_SPAWN_RATE = 2;
    private final static int GIANT_CRAYFISH_SPAWN_RATE = 1;

    /**

     Constructor for PuddleOfWater
     */
    public PuddleOfWater() {
        super('~');
    }

    /**

     Overrides the tick method to spawn skeletal swordsman and skeletal bandit enemies in the provided location.

     if there are no actors present in the location, spawn the enemy.

     @param location The location of the spawning ground
     */
    @Override
    public void tick(Location location) {
        if (!location.containsAnActor() && location.getGround().hasCapability(MapSide.WEST)){
            super.spawnEnemy(new GiantCrab(), GIANT_CRAB_SPAWN_RATE, location);
        }

        if (!location.containsAnActor() && location.getGround().hasCapability(MapSide.EAST)){
            super.spawnEnemy(new GiantCrayfish(), GIANT_CRAYFISH_SPAWN_RATE, location);
        }
    }
}

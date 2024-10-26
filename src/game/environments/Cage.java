package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Dog;

/**
 * Graveyards class represents a spawning ground of Dog enemies in the game.
 * It extends SpawningGround class and overrides the tick method to spawn enemies.
 *
 * Created by:
 * @author Albert Liesman
 */
public class Cage extends SpawningGround{
    private final static int DOG_SPAWN_RATE = 37;
    /**

     Constructor for Cage
     */
    public Cage() {
        super('<');
    }

    /**

     Overrides the tick method to spawn Dog enemies in the provided location.

     if there are no actors present in the location, spawn the enemy.

     @param location The location of the spawning ground
     */
    @Override
    public void tick(Location location) {
        if (!location.containsAnActor()) {
            super.spawnEnemy(new Dog(), DOG_SPAWN_RATE, location);
        }
    }
}

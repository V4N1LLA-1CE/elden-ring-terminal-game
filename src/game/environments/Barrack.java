package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.GodrickSoldier;

/**
 * Graveyards class represents a spawning ground of Godrick soldier enemies in the game.
 * It extends SpawningGround class and overrides the tick method to spawn enemies.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer
 */
public class Barrack extends SpawningGround{
    private final static int GODRICK_SOLDIER_SPAWN_RATE = 45;
    /**

     Constructor for Barrack
     */
    public Barrack() {
        super('B');
    }

    /**

     Overrides the tick method to spawn Godrick Soldier enemies in the provided location.

     if there are no actors present in the location, spawn the enemy.

     @param location The location of the spawning ground
     */
    @Override
    public void tick(Location location) {
        if (!location.containsAnActor()) {
            super.spawnEnemy(new GodrickSoldier(), GODRICK_SOLDIER_SPAWN_RATE, location);
        }
    }
}

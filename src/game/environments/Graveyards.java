package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.HeavySkeletalSwordsman;
import game.enemies.SkeletalBandit;

/**
 * Graveyards class represents a spawning ground of Skeleton enemies in the game.
 * It extends SpawningGround class and overrides the tick method to spawn enemies.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class Graveyards extends SpawningGround {
    private final static int SKELETAL_SWORDSMAN_SPAWN_RATE = 27;
    private final static int SKELETAL_BANDIT_SPAWN_RATE = 27;

    /**

     Constructor for Graveyards
     */
    public Graveyards() {
        super('n');
    }

    /**

     Overrides the tick method to spawn skeletal swordsman and skeletal bandit enemies in the provided location.

     if there are no actors present in the location, spawn the enemy.

     @param location The location of the spawning ground
     */
    @Override
    public void tick(Location location) {
        if (!location.containsAnActor() && location.getGround().hasCapability(MapSide.EAST)) {
            super.spawnEnemy(new HeavySkeletalSwordsman(), SKELETAL_SWORDSMAN_SPAWN_RATE, location);
        }

        if (!location.containsAnActor() && location.getGround().hasCapability(MapSide.WEST)){
            super.spawnEnemy(new SkeletalBandit(), SKELETAL_BANDIT_SPAWN_RATE, location);
        }
    }
}

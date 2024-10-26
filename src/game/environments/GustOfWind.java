package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.GiantDog;
import game.enemies.LoneWolf;

/**
 * Graveyards class represents a spawning ground of LoneWolf and GiantDog enemies in the game.
 * It extends SpawningGround class and overrides the tick method to spawn enemies.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class GustOfWind extends SpawningGround{
    private final static int LONE_WOLF_SPAWN_RATE = 33;
    private final static int GIANT_DOG_SPAWN_RATE = 4;

    /**

     Constructor for GustOfWind
     */
    public GustOfWind() {
        super('&');
    }

    /**

     Overrides the tick method to spawn LoneWolf and GiantDog enemies in the provided location.

     if there are no actors present in the location, spawn the enemy.

     @param location The location of the spawning ground
     */
    @Override
    public void tick(Location location) {
        if (!location.containsAnActor() && location.getGround().hasCapability(MapSide.EAST)){
            super.spawnEnemy(new LoneWolf(), LONE_WOLF_SPAWN_RATE, location);
        }

        if (!location.containsAnActor() && location.getGround().hasCapability(MapSide.WEST)){
            super.spawnEnemy(new GiantDog(), GIANT_DOG_SPAWN_RATE, location);
        }
    }
}

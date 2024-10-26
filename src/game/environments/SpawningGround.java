package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.RandomNumberGenerator;

/**
 * This abstract class represents a type of ground where enemies can spawn.
 * Includes the probability of the enemies to spawn.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public abstract class SpawningGround extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
    }

    /**
     Spawns an enemy on the given location with a certain spawn rate.
     @param enemy The enemy Actor to spawn.
     @param spawnRate The chance of an enemy spawning, expressed as a percentage from 1 to 100.
     @param location The location to spawn the enemy at.
     */
    public void spawnEnemy(Actor enemy, int spawnRate, Location location){
        if (RandomNumberGenerator.getRandomInt(1, 100) <= spawnRate) {
            // spawn occurs
            location.addActor(enemy);
        }
    }

    public void spawnItem(Item item, int spawnRate, Location location){
        if (RandomNumberGenerator.getRandomInt(1, 1000) <= spawnRate) {
            // spawn occurs
            location.addItem(item);
        }
    }
}

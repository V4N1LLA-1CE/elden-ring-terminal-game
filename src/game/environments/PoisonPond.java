package game.environments;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PoisonAction;
import game.enums.Status;
import game.item.BloodPotion;

/**
 * PoisonPond is a subclass of SpawningGround representing a poisonous pond environment.
 * Actors located in the PoisonPond will be poisoned and take damage over time.
 * The PoisonPond has a 2% chance to spawn Blood Potions.
 *
 * Created by:
 * @author Albert Liesman
 */
public class PoisonPond extends SpawningGround {
    private final static int BLOOD_POTION_SPAWN_RATE = 20; // 2%
    private Display display = new Display();
    /**
     * Constructor.
     */
    public PoisonPond() {
        super(',');
    }

    /**
     * Overrides the tick method from the SpawningGround class to perform the actions of the PoisonPond on each tick.
     * Actors located in the PoisonPond will be poisoned and take damage.
     * The PoisonPond has a chance to spawn Blood Potions.
     *
     * @param location the location of the PoisonPond
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().hasCapability(Status.RESPAWNABLE)){
            new PoisonAction(50).execute(location.getActor(), location.map());
            display.println(location.getActor() + " is poisoned for 50 damage");
        }
        // Spawn Blood Potion
        super.spawnItem(new BloodPotion(), BLOOD_POTION_SPAWN_RATE, location);
    }
}

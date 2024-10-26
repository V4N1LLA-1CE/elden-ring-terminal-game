package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * BloodPotion is a consumable item that can be used to increase an actor's maximum hit points.
 * It extends the Consumables class and implements the ConsumeInterface.
 *
 * Created by:
 * @author Albert Liesman
 */
public class BloodPotion extends Consumables implements ConsumeInterface{
    private final static String NAME = "Blood Potion";
    private final static char DISPLAY_CHAR = '^';
    private int usage = 1;
    private int lifeSpan = 7;

    /**
     * Constructor
     */
    public BloodPotion() {
        super(NAME, DISPLAY_CHAR, true);
        this.addCapability(ItemStatus.CONSUMABLE);
    }

    /**
     * Returns the number of usages left for the BloodPotion.
     *
     * @return the number of usages left
     */
    public int getUsage(){
        return usage;
    }

    /**
     * Decreases the usage count of the BloodPotion by 1.
     */
    public void consumed(){
        usage -= 1;
    }

    /**
     * Performs the ticking action for the BloodPotion.
     * Decreases the lifespan of the potion, and removes it from the location if the lifespan reaches 0.
     *
     * @param currentLocation the current location of the BloodPotion
     */
    @Override
    public void tick(Location currentLocation) {
        // Despawn BloodPotion in 7 turn after being spawned
        if (lifeSpan == 0){
            currentLocation.removeItem(this);
        }
        lifeSpan -= 1;
    }

    /**
     * Consumes the BloodPotion and increases the actor's maximum hit points by 25.
     *
     * @param actor the actor consuming the potion
     * @return a string indicating the result of the consumption
     */
    @Override
    public String consume(Actor actor) {
        String result = "";
        if (this.getUsage() > 0){
            actor.increaseMaxHp(25);
            this.consumed();
            actor.removeItemFromInventory(this);
            result += actor + " consumed " + NAME + ". Max HP is increased by 25";
            return result;
        }
        return NAME + " is not available";
    }

    /**
     * Returns the menu text for consuming the BloodPotion.
     *
     * @param actor the actor consuming the potion
     * @return the menu text
     */
    @Override
    public String menuText(Actor actor) {
        return actor + " consumed " + NAME;
    }
}

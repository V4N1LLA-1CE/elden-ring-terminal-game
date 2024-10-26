package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.runes.RuneManager;
import game.utilities.RandomNumberGenerator;

/**
 * A class representing Golden Runes, a consumable item in the game which will
 * generate a random number of runes for player.
 *
 * Created by:
 * @author Kachun Lee
 */
public class GoldenRunes extends Consumables implements ConsumeInterface {
    private final static String NAME = "Golden Runes";
    private final static char DISPLAY_CHAR = '*';
    private final static int GOLDEN_RUNES_LO = 200;
    private final static int GOLDEN_RUNES_HI = 10000;
    private int usage = 1;
    private int lifeSpan = 7;
    private int value;

    /**
     * Constructor for GoldenRunes.
     */
    public GoldenRunes() {
        super(NAME, DISPLAY_CHAR, true);
        this.value = generateRunes();
        this.addCapability(ItemStatus.CONSUMABLE);
    }

    /**
     * Retrieves the value of the Golden Runes.
     *
     * @return the value of the Golden Runes
     */
    public int getValue(){
        return value;
    }

    /**
     * Retrieves the usage count of the Golden Runes.
     *
     * @return the usage count of the Golden Runes
     */
    public int getUsage() {
        return usage;
    }

    /**
     * Decreases the usage count of the Golden Runes by 1.
     */
    public void consumed(){
        usage -= 1;
    }

    /**
     * Generates a random value for the Golden Runes within a specified range.
     *
     * @return the generated value for the Golden Runes
     */
    public int generateRunes(){
        value = RandomNumberGenerator.getRandomInt(GOLDEN_RUNES_LO, GOLDEN_RUNES_HI);
        return value;
    }

    /**
     * Performs the tick action for the Golden Runes.
     * Decreases the lifespan of the runes, and removes them from the current location if the lifespan reaches 0.
     *
     * @param currentLocation the current location of the Golden Runes
     */
    @Override
    public void tick(Location currentLocation) {
        // Despawn BloodPotion if the lifeSpan hits 0
        if (lifeSpan == 0){
            currentLocation.removeItem(this);
        }
        lifeSpan -= 1;
    }

    /**
     * Consumes the Golden Runes by adding their value to the RuneManager's total runes count.
     * Decreases the usage count of the runes by 1 and removes them from the actor's inventory.
     *
     * @param actor the actor consuming the Golden Runes
     * @return a string describing the consumption of the Golden Runes and the gained value
     */
    @Override
    public String consume(Actor actor) {
        if (this.getUsage() > 0){
            this.consumed();
            RuneManager.addRunes(this.getValue());
            actor.removeItemFromInventory(this);
            return "You gained $" + this.getValue() + " from the Golden Rune.";
        }
        return NAME + " is not available.";
    }

    /**
     * Retrieves the menu text for consuming the Golden Runes.
     *
     * @param actor the actor consuming the Golden Runes
     * @return the menu text for consuming the Golden Runes
     */
    @Override
    public String menuText(Actor actor) {
        return actor + " consumes " + NAME + " (" + this.getUsage() + ")" ;
    }
}

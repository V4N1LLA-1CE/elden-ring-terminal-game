package game.runes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.enums.Status;

/**
 * Represents a currency in the game, which has a rune value.
 * Inherits from Item class and implements RunesInterface.
 *
 * Created by:
 * @author Kachun Lee
 */
public class Runes extends Item implements RunesInterface {
    private int runeValue;
    private final static String RUNE_NAME = "Runes";
    private final static char RUNE_CHAR = '$';

    /**

     Constructs a Runes object with a given rune value.
     @param runeValue the value of the Rune object
     */
    public Runes (int runeValue) {
        super(RUNE_NAME, RUNE_CHAR, true);
        this.runeValue = runeValue;
        this.addCapability(Status.RUNE);
    }

    /**

     Gets the value of the Rune object.
     @return the value of the Rune object
     */
    public int getRuneValue(){
        return runeValue;
    }

    /**

     Returns null as Runes cannot be picked up.
     @param actor the actor attempting to pick up the item
     @return null
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }

    /**

     Returns null as Runes cannot be dropped.
     @param actor the actor attempting to drop the item
     @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }
}

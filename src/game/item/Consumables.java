package game.item;

import edu.monash.fit2099.engine.items.Item;

/**
 * Consumables is an abstract class representing a consumable item in the game.
 * It extends the Item class from the engine and provides a base implementation for consumable items.
 *
 * Created by:
 * @author Kachun Lee
 */
public abstract class Consumables extends Item {
    /**
     * Constructor for the Consumables class.
     *
     * @param name        the name of the consumable item
     * @param displayChar the character that represents the item in the display
     * @param portable    a boolean indicating if the item is portable or not
     */
    public Consumables(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
}

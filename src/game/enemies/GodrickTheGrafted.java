package game.enemies;

import game.item.RemembranceOfTheGrafted;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * GodrickTheGrafted is a subclass of the Enemies class representing a boss enemy character in the game.
 * GodrickTheGrafted has unique items and weapons in its inventory, and it transitions between phases based on its hit points.
 * (NOT FULLY IMPLEMENTED SINCE IT IS OPTIONAL)
 *
 * Created by:
 * Author: Albert Liesman
 */
public class GodrickTheGrafted extends Enemies{
    private final static String NAME = "Godrick the Grafted";
    private final static char DISPLAY_CHAR = 'Y';
    private final static int HIT_POINTS = 6080;

    /**
     * Constructor.
     */
    public GodrickTheGrafted() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
        this.addItemToInventory(new RemembranceOfTheGrafted());
        // Phase 1
        if (this.hitPoints > (this.maxHitPoints/2)){
            this.addWeaponToInventory(new AxeOfGodrick());
        }
        // Phase 2
        if (this.hitPoints < (this.maxHitPoints/2)) {
            this.removeItemFromInventory(new AxeOfGodrick());
            this.addWeaponToInventory(new GraftedDragon());
        }
    }

    /**

     Generates a random number of runes for the Godrick the Grafted and adds them to its inventory.
     */
    @Override
    public void generateRunes() {

    }
}

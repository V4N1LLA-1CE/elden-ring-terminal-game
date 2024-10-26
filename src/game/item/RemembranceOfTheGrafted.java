package game.item;

import edu.monash.fit2099.engine.items.Item;

/**
 * Item that is dropped when the boss enemy Godrick the Grafted is defeated.
 * This item can be exchange with FingerReaderEnia for the unique Weapons
 * of Godrick the Grafted.
 *
 * Created by:
 * @author Albert Liesman
 */
public class RemembranceOfTheGrafted extends Item {
    private final static String NAME = "Remembrance of the Grafted";
    private final static char DISPLAY_CHAR = 'O';
    /**
     * Constructor
     */
    public RemembranceOfTheGrafted() {
        super(NAME, DISPLAY_CHAR, true);
        this.addCapability(ItemStatus.REMEMBRANCE_OF_THE_GRAFTED);
    }
}

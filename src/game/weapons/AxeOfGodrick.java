package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.trading.BuyInterface;

/**
 * Weapon that can is originally carried by GodrickTheGrafted during Phase 1.
 * It has a damage rate of 142, hit rate of 84.
 * Player can exchange RemembranceOfTheGrafted for this weapon.
 *
 * Created by:
 * @author Albert Liesman
 */
public class AxeOfGodrick extends WeaponItem implements BuyInterface {
    private final static String NAME = "Axe of Godrick";
    private final static char DISPLAY_CHAR = 'T';
    private final static int DAMAGE = 142;
    private final static String VERB = "swings";
    private final static int HIT_RATE = 84;

    /**
     * Constructor.
     */
    public AxeOfGodrick() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE);
        this.addCapability(Status.TRADABLE);
    }

    @Override
    public int getBuyPrice() {
        return 0;
    }

    @Override
    public void setBuyPrice(int newPrice) {

    }

    @Override
    public WeaponItem buy() {
        return new AxeOfGodrick();
    }
}

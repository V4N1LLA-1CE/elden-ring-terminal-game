package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.trading.BuyInterface;

/**
 * Weapon that can is originally carried by GodrickTheGrafted during Phase 2.
 * It has a damage rate of 89, hit rate of 90.
 * Player can exchange RemembranceOfTheGrafted for this weapon.
 *
 * Created by:
 * @author Albert Liesman
 */
public class GraftedDragon extends WeaponItem implements BuyInterface {
    private final static String NAME = "Grafted Dragon";
    private final static char DISPLAY_CHAR = 'N';
    private final static int DAMAGE = 89;
    private final static String VERB = "boings";
    private final static int HIT_RATE = 90;

    /**
     * Constructor.
     */
    public GraftedDragon() {
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
        return new GraftedDragon();
    }
}

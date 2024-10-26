package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.trading.BuyInterface;
import game.trading.SellInterface;

/**
 * Astrologer starting weapon that can be used to attack the enemy.
 * It has a damage rate of 274, hit rate of 50, and can perform a ranged attack.
 * It has buy and sell capabilities with specific prices for each.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class AstrologerStaff extends WeaponItem implements BuyInterface, SellInterface {
    private final static String NAME = "Astrologer's Staff";
    private final static char DISPLAY_CHAR = 'f';
    private final static int DAMAGE = 274;
    private final static String VERB = "magics";
    private final static int HIT_RATE = 50;
    private int sellPrice;
    private int buyPrice;
    /**
     * Constructor
     */
    public AstrologerStaff() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE);
        setBuyPrice(800);
        setSellPrice(100);
        this.addCapability(WeaponSkill.STAFF);
        this.addCapability(Status.SELLABLE);
        this.addCapability(Status.BUYABLE);
    }

    /**
     * Retrieves the buy price of the HeavyCrossbow.
     *
     * @return the buy price of the HeavyCrossbow
     */
    @Override
    public int getBuyPrice() {
        return this.buyPrice;
    }

    /**
     * Sets the buy price of the HeavyCrossbow.
     *
     * @param newPrice the new buy price of the HeavyCrossbow
     */
    @Override
    public void setBuyPrice(int newPrice) {
        this.buyPrice = newPrice;
    }

    /**
     * Sets the sell price of the HeavyCrossbow.
     *
     * @param newPrice the new sell price of the HeavyCrossbow
     */
    @Override
    public void setSellPrice(int newPrice) {
        this.sellPrice = newPrice;
    }

    /**
     * Retrieves the sell price of the HeavyCrossbow.
     *
     * @return the sell price of the HeavyCrossbow
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }

    /**
     * Executes the buy action for the HeavyCrossbow.
     *
     * @return a new instance of the HeavyCrossbow
     */
    @Override
    public WeaponItem buy() {
        return new AstrologerStaff();
    }
}

package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.trading.BuyInterface;
import game.trading.SellInterface;
import game.enums.Status;
import game.weaponskills.Unsheathe;

/**
 * Samurai starting weapon that can be used to attack the enemy.
 * It has a damage rate of 115, hit rate of 80, and can perform a skill called Unsheathe.
 * It has buy and sell capabilities with specific prices for each.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class Uchigatana extends WeaponItem implements BuyInterface, SellInterface {
    private int sellPrice;
    private int buyPrice;
    private final static String UCHIGATANA_NAME = "Uchigatana";
    private final static char UCHIGATANA_CHAR = ')';
    private final static int UCHIGATANA_DAMAGE = 115;
    private final static String UCHIGATANA_VERB = "slash";
    private final static int UCHIGATANA_HITRATE = 80;

    /**
        Constructor
     */
    public Uchigatana() {
        super(UCHIGATANA_NAME, UCHIGATANA_CHAR, UCHIGATANA_DAMAGE, UCHIGATANA_VERB, UCHIGATANA_HITRATE);
        this.addCapability(WeaponSkill.HAS_SKILL);
        setBuyPrice(5000);
        setSellPrice(500);
        this.addCapability(Status.SELLABLE);
        this.addCapability(Status.BUYABLE);
    }

    /**

     Returns an Unsheathe action with the target, direction, and this weapon.
     @param target the target Actor.
     @param direction the direction of the target.
     @return an Unsheathe action with the target, direction, and this weapon.
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new Unsheathe(target, direction, this);
    }

    /**

     Gets the selling price of this weapon.
     @return the selling price of this weapon.
     */
    @Override
    public int getSellPrice(){
        return this.sellPrice;
    }

    /**

     Gets the buying price of this weapon.
     @return the buying price of this weapon.
     */
    @Override
    public int getBuyPrice(){
        return this.buyPrice;
    }

    /**

     Sets the selling price of this weapon.
     @param newPrice the new selling price of this weapon.
     */
    @Override
    public void setSellPrice(int newPrice){
        this.sellPrice = newPrice;
    }

    /**

     Sets the buying price of this weapon.
     @param newPrice the new buying price of this weapon.
     */
    @Override
    public void setBuyPrice(int newPrice){
        this.buyPrice = newPrice;
    }

    /**

     Creates a new instance of Uchigatana when bought.
     @return a new instance of Uchigatana when bought.
     */
    @Override
    public WeaponItem buy(){
        return new Uchigatana();
    }
}

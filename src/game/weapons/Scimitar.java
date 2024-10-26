package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.trading.BuyInterface;
import game.trading.SellInterface;
import game.enums.Status;
import game.uniqueattacks.SpinningAttack;

/**
 * Weapon that can is originally carried by SkeletalBandit.
 * It has a damage rate of 118, hit rate of 88, and can perform a skill called SpinningAttack.
 * It has buy and sell capabilities with specific prices for each.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class Scimitar extends WeaponItem implements BuyInterface, SellInterface {
    private final static String SCIMITAR_NAME = "Scimitar";
    private final static char SCIMITAR_DISPLAY_CHAR = 's';
    private final static int SCIMITAR_DAMAGE = 118;
    private final static String SCIMITAR_VERB = "slash";
    private final static int SCIMITAR_HIT_RATE = 88;
    private int sellPrice;
    private int buyPrice;

    /**
        Constructor
     */
    public Scimitar() {
        super(SCIMITAR_NAME, SCIMITAR_DISPLAY_CHAR, SCIMITAR_DAMAGE, SCIMITAR_VERB, SCIMITAR_HIT_RATE);
        this.addCapability(WeaponSkill.HAS_SKILL);
        setBuyPrice(600);
        setSellPrice(100);
        this.addCapability(Status.SELLABLE);
        this.addCapability(Status.BUYABLE);
    }

    /**

     Returns an Action object representing the spinning attack skill of the Scimitar.
     @param target the target of the spinning attack
     @param direction the direction in which to perform the spinning attack
     @return an Action object representing the spinning attack skill of the Scimitar
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new SpinningAttack(target, direction, this);
    }

    /**

     Overrides the tick method in the WeaponItem class and calls the super method.
     @param currentLocation the location of the Scimitar
     @param actor the actor holding the Scimitar
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    /**

     Returns the sell price of the Scimitar.
     @return the sell price of the Scimitar
     */
    @Override
    public int getSellPrice(){
        return this.sellPrice;
    }

    /**

     Returns the buy price of the Scimitar.
     @return the buy price of the Scimitar
     */
    @Override
    public int getBuyPrice(){
        return this.buyPrice;
    }

    /**

     Sets the sell price of the Scimitar to a new value.
     @param newPrice the new sell price of the Scimitar
     */
    @Override
    public void setSellPrice(int newPrice){
        this.sellPrice = newPrice;
    }

    /**

     Sets the buy price of the Scimitar to a new value.
     @param newPrice the new buy price of the Scimitar
     */
    @Override
    public void setBuyPrice(int newPrice){
        this.buyPrice = newPrice;
    }

    /**

     Creates a new instance of Scimitar when bought.
     @return a new instance of Scimitar when bought.
     */
    @Override
    public WeaponItem buy(){
        return new Scimitar();
    }
}

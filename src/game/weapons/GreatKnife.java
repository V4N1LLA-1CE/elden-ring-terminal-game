package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.trading.BuyInterface;
import game.trading.SellInterface;
import game.enums.Status;
import game.weaponskills.Quickstep;

/**
 * Bandit starting weapon that can be used to attack the enemy.
 * It has a damage rate of 75, hit rate of 70, and can perform a skill called QuickStep.
 * It has buy and sell capabilities with specific prices for each.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class GreatKnife extends WeaponItem implements BuyInterface, SellInterface {
    private final static String GREAT_KNIFE_NAME = "Great Knife";
    private final static char GREAT_KNIFE_DISPLAY_CHAR = '/';
    private final static int GREAT_KNIFE_DAMAGE = 75;
    private final static String GREAT_KNIFE_VERB = "stab";
    private final static int GREAT_KNIFE_HIT_RATE = 70;
    private int sellPrice;
    private int buyPrice;

    /**
        Constructor
     */
    public GreatKnife() {
        super(GREAT_KNIFE_NAME, GREAT_KNIFE_DISPLAY_CHAR, GREAT_KNIFE_DAMAGE, GREAT_KNIFE_VERB, GREAT_KNIFE_HIT_RATE);
        this.addCapability(WeaponSkill.HAS_SKILL);
        setBuyPrice(3500);
        setSellPrice(350);
        this.addCapability(Status.SELLABLE);
        this.addCapability(Status.BUYABLE);
    }

    /**

     Returns an action object representing the skill of the GreatKnife.
     The Quickstep skill is returned, which allows the player to move to a nearby location quickly.
     @param target the actor being targeted with the skill
     @param direction the direction in which the skill is being used
     @return an action object representing the Quickstep skill
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new Quickstep(target, direction, this);
    }

    /**

     Updates the state of the GreatKnife after a tick of the game world.
     This method does not change any state of the GreatKnife.
     @param currentLocation the location of the GreatKnife
     @param actor the actor holding the GreatKnife
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    /**

     Returns the sell price of the GreatKnife.
     @return the sell price of the GreatKnife
     */
    @Override
    public int getSellPrice(){
        return this.sellPrice;
    }

    /**

     Returns the buy price of the GreatKnife.
     @return the buy price of the GreatKnife
     */
    @Override
    public int getBuyPrice(){
        return this.buyPrice;
    }

    /**

     Sets the sell price of the GreatKnife to the specified value.
     @param newPrice the new sell price of the GreatKnife
     */
    @Override
    public void setSellPrice(int newPrice){
        this.sellPrice = newPrice;
    }

    /**

     Sets the buy price of the GreatKnife to the specified value.
     @param newPrice the new buy price of the GreatKnife
     */
    @Override
    public void setBuyPrice(int newPrice){
        this.buyPrice = newPrice;
    }

    /**

     Returns a new GreatKnife object when bought by the player.
     @return a new GreatKnife object
     */
    @Override
    public WeaponItem buy(){
        return new GreatKnife();
    }
}

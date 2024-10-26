package game.weapons;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.trading.SellInterface;
import game.enums.Status;
import game.uniqueattacks.SpinningAttack;

/**
 * Weapon that can is originally carried by HeavySkeletalSwordsman.
 * It has a damage rate of 115, hit rate of 80, and can perform a skill called SpinningAttack.
 * It has sell capabilities with specific prices for each.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class Grossmesser extends WeaponItem implements SellInterface {
    private final static String GROSSMESSER_NAME = "Grossmesser";
    private final static char GROSSMESSER_DISPLAY_CHAR = '?';
    private final static int GROSSMESSER_DAMAGE = 115;
    private final static String GROSSMESSER_VERB = "slash";
    private final static int GROSSMESSER_HIT_RATE = 80;
    private int sellPrice;

    /**
     Constructor
     */
    public Grossmesser(){
        super(GROSSMESSER_NAME, GROSSMESSER_DISPLAY_CHAR, GROSSMESSER_DAMAGE, GROSSMESSER_VERB, GROSSMESSER_HIT_RATE);
        this.addCapability(WeaponSkill.HAS_SKILL);
        setSellPrice(100);
        this.addCapability(Status.SELLABLE);
    }

    /**

     Returns an action representing the skill associated with the Grossmesser weapon item.
     In this case, it returns a SpinningAttack action.
     @param target the actor to perform the skill on
     @param direction the direction of the skill
     @return a SpinningAttack action
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new SpinningAttack(target, direction, this);
    }

    /**

     Overrides the tick method in WeaponItem to perform any actions required for the Grossmesser weapon item
     on every game tick.
     @param currentLocation the current location of the actor holding the weapon item
     @param actor the actor holding the weapon item
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    /**

     Returns the sell price of the Grossmesser weapon item.
     @return the sell price of the Grossmesser weapon item
     */
    @Override
    public int getSellPrice(){
        return this.sellPrice;
    }

    /**

     Sets the sell price of the Grossmesser weapon item to the specified value.
     @param newPrice the new sell price of the Grossmesser weapon item
     */
    @Override
    public void setSellPrice(int newPrice){
        this.sellPrice = newPrice;
    }
}

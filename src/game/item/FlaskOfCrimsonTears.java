package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.HealAction;
import game.reset.Resettable;


/**
 * Item that can be consumed to heal an actor's hit points.
 * It has a limited number of uses, which can be set upon creation and reset upon resting.
 * Once the number of uses reaches zero, the item can no longer be consumed.
 *
 * Created by:
 * @author Albert Liesman
 */
public class FlaskOfCrimsonTears extends Consumables implements Resettable, ConsumeInterface {
    private final static String NAME = "Flask of Crimson Tears";
    private final static char DISPLAY_CHAR = 'f';
    private final static int MAX_USAGE = 2;
    private int usage;

    /**

     Constructor for FlaskOfCrimsonTears
     @param usage the number of uses the FlaskOfCrimsonTears should have
     */
    public FlaskOfCrimsonTears(int usage) {
        super(NAME, DISPLAY_CHAR,  false);
        this.usage = usage;
        this.addCapability(ItemStatus.CONSUMABLE);
    }

    /**

     Get the current number of uses of the FlaskOfCrimsonTears.
     @return the number of uses remaining
     */
    public int getUsage() {
        return usage;
    }

    /**

     Decrease the number of uses of the FlaskOfCrimsonTears by one upon consumption.
     */
    public void consumed(){
        usage -= 1;
    }

    /**

     Reset the number of uses of the FlaskOfCrimsonTears to the maximum amount, either when the player rests
     or when the game starts.
     @param resting whether the FlaskOfCrimsonTears is being reset while the actor is resting
     */
    @Override
    public void reset(Boolean resting) {
        this.usage = MAX_USAGE;
    }

    @Override
    public String consume(Actor actor) {
        if (this.getUsage() > 0){
            this.consumed();
            return new HealAction(actor).execute(actor, null);
        }
        return NAME + " is not available.";
    }

    @Override
    public String menuText(Actor actor) {
        return actor + " consumes " + NAME + " (" + this.getUsage() + "/2)" ;
    }
}

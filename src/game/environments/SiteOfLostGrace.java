package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.actions.RestAction;

/**
 * SiteOfLostGrace is a type of Ground that can be used for resting.
 * It allows the RestAction when an actor with the capability Status.RESTING.
 *
 * Created by:
 * @author Albert Liesman
 */
public class SiteOfLostGrace extends Ground {
    /**

     Constructor for SiteOfLostGrace
     */
    public SiteOfLostGrace() {
        super('U');
        this.addCapability(Status.SITE_LOST_GRACE);
    }

    /**

     Returns the allowable actions for an actor on this SiteOfLostGrace object. The RestAction is added to the list of

     allowable actions if the actor has the capability Status.RESTING.

     @param actor the actor on this SiteOfLostGrace object

     @param location the location of this SiteOfLostGrace object

     @param direction the direction of the actor's movement

     @return the list of allowable actions for the actor on this SiteOfLostGrace object
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (actor.hasCapability(Status.RESTING)){
            actions.add(new RestAction(this, location));
        }
        return actions;
    }

    /**

     Performs the default tick behavior of a Ground object on this SiteOfLostGrace object.
     @param location the location of this SiteOfLostGrace object
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
    }
}

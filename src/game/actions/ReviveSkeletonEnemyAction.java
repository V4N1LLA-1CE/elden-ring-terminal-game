package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Action to revive a dead skeleton enemy actor on the game map.
 *
 * Created by:
 * @author Austin Sofaer
 * modified by Albert Liesman
 */
public class ReviveSkeletonEnemyAction extends Action {
    private Location thisLocation;
    private Actor revivedActor;

    /**

     Constructor for ReviveSkeletonEnemyAction with a revivedActor parameter.
     @param revivedActor the actor to be revived
     */
    public ReviveSkeletonEnemyAction(Actor revivedActor){
        this.revivedActor = revivedActor;
    }

    /**

     Removes the current actor from the map and adds the revived actor to the same location.
     @param actor the actor performing the action
     @param map the game map where the action is being performed
     @return the menu description of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        thisLocation = map.locationOf(actor);

        map.removeActor(actor);
        map.addActor(revivedActor, thisLocation);
        return menuDescription(revivedActor);
    }

    /**

     Returns the menu description for the revived actor.
     @param actor the actor to be revived
     @return the menu description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been revived!!!";
    }
}

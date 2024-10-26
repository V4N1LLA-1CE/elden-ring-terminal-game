package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.reset.ResetManager;
import game.environments.SiteOfLostGrace;

/**
 * Represents an action for an actor to rest at a Site of Lost Grace, allowing for a full reset of the game state.
 *
 * Created by:
 * @author Albert Liesman
 */
public class RestAction extends Action {
    private SiteOfLostGrace site;
    private Location location;

    /**

     Constructor for a RestAction object.
     @param site the SiteOfLostGrace object to rest at.
     @param location the Location object representing the location of the SiteOfLostGrace.
     */
    public RestAction(SiteOfLostGrace site, Location location) {
        this.site = site;
        this.location = location;
    }

    /**

     Executes the RestAction by running a full reset of the game state and moving the actor to the SiteOfLostGrace.

     @param actor the Actor object performing the action.
     @param map the GameMap object representing the game map.
     @return a String representing the outcome of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run(true);

        if (map.locationOf(actor) != location){
            map.moveActor(actor, location);
        }
        return menuDescription(actor);
    }

    /**

     Returns a String representing the description of the RestAction object.
     @param actor the Actor object performing the action.
     @return a String representing the description of the RestAction object.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at Site of Lost Grace";
    }
}

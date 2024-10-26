package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action where an actor despawns from the game map.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer
 */
public class DespawnAction extends Action {
    private Actor actor;
    private GameMap map;

    /**

     Constructor for DespawnAction.
     @param actor The actor to despawn from the game map.
     @param map The game map to remove the actor from.
     */
    public DespawnAction(Actor actor, GameMap map) {
        this.actor = actor;
        this.map = map;
    }

    /**

     This method executes the action of despawning an actor from the game map.

     @param actor The actor to despawn.
     @param map The game map to remove the actor from.
     @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        map.removeActor(actor);
        result += menuDescription(actor);
        return result;
    }

    /**

     This method returns a string describing the menu representation of this action.
     @param actor The actor that performs the action.
     @return A string describing the menu representation of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has despawned from the map";
    }
}

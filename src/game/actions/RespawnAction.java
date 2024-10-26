package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utilities.FancyMessage;
import game.reset.ResetManager;

/**
 * Allows an Actor to respawn at the Site of Lost Grace when the player dies and needs to respawn.
 * It uses the ResetManager class to reset the game state to a previous state before the player died.
 *
 * Created by:
 * @author Albert Liesman
 */
public class RespawnAction extends Action {

    /**

     Executes the RespawnAction by running the ResetManager to reset the game state and

     printing a string with a fancy message indicating that the player has died and is respawning.

     @param actor The Actor that is executing this action.

     @param map The GameMap where the action is being executed.

     @return A string with a fancy message indicating that the player has died and is respawning.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run(false);

        String string = "\n";
        string += FancyMessage.YOU_DIED;
        string += menuDescription(actor);

        return string;
    }

    /**

     Generates a string with a message indicating that the Actor is being respawned at the Site of Lost Grace.
     @param actor The Actor that is executing this action.
     @return A string with a message indicating that the Actor is being respawned at the Site of Lost Grace.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is respawned at last visited Site of Lost Grace \n";
    }
}

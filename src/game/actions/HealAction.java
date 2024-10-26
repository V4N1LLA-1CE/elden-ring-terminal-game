package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * This class represents an action that allows an actor to heal themselves
 * when using the Flask of Crimson Tears item.
 *
 * Created by:
 * @author Albert Liesman
 */
public class HealAction extends Action {
    private Actor player;

    /**

     Constructor for the HealAction class.
     @param player - The Actor who will perform the action.
     */
    public HealAction(Actor player) {
        this.player = player;
    }

    /**

     Executes the HealAction by healing the Actor who is performing it by 250 hit points.
     @param actor - The Actor who is performing the action.
     @param map - The current GameMap.
     @return A string description of the action's result.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(250);
        return menuDescription(actor);
    }

    /**

     Returns a string description of the HealAction for the Actor's menu.
     @param actor - The Actor who is performing the action.
     @return A string description of the action for the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " used Flask of Crimson Tears. HP is restored by 250." ;
    }
}

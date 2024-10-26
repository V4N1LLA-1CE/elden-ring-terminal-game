package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;

/**
 * PoisonAction is an action performed on an actor to apply poison damage.
 * It extends the Action class.
 *
 * Created by:
 * @author Albert Liesman
 */
public class PoisonAction extends Action {
    private int damage;

    /**
     * Constructor for the PoisonAction class.
     *
     * @param damage the amount of poison damage to apply
     */
    public PoisonAction(int damage){
        this.damage = damage;
    }

    /**
     * Executes the PoisonAction by applying poison damage to the actor.
     *
     * @param actor the actor affected by the poison
     * @param map   the game map
     * @return a string indicating the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        if (actor.hasCapability(Status.RESPAWNABLE)){
            result += actor + " is poisoned for" + damage + "damage.";
            actor.hurt(damage);
        }
        return result + menuDescription(actor);
    }

    /**
     * Returns the menu description of the PoisonAction.
     *
     * @param actor the actor affected by the poison
     * @return the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " died from poison.";
    }
}

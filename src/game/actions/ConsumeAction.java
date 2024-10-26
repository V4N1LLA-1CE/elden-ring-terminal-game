package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.ConsumeInterface;

/**
 * An Action to consume an item.
 * Created by:
 * @author Ka Chun Lee
 *
 */
public class ConsumeAction extends Action {
    /**
     * The item that is to be consumed
     */
    private ConsumeInterface item;

    /**
     * Constructor.
     *
     * @param item the item to consume
     */
    public ConsumeAction(ConsumeInterface item) {
        this.item = item;
    }

    /**
     * When executed, item will be consumed and whatever effect the item has will be returned by the consume method
     * and applied to as well.
     *
     * @param actor The actor consuming the item.
     * @param map The map the actor is on.
     * @return the result of the item consumed.
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return item.consume(actor);
    }

    /**
     * Describes what the actor is consuming
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return item.menuText(actor);
    }
}

package game.trading;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TradeAction;
import game.enums.Status;

public abstract class Trader extends Actor {

    /**
     * Constructor for Trader.
     * Initializes the trader with a name, display character, and hit points.
     * Adds the friendly capability to the trader.
     *
     * @param name        the name of the trader
     * @param displayChar the character to display for the trader
     * @param hitPoints   the hit points of the trader
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.FRIENDLY);
    }

    /**
     * Performs the trader's turn by returning a DoNothingAction.
     *
     * @param actions    the list of allowable actions
     * @param lastAction the last action performed
     * @param map        the current game map
     * @param display    the display used to show the game
     * @return a DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Retrieves the list of allowable actions for the trader when interacting with another actor.
     * Adds a TradeAction to the list of actions if the other actor is hostile to the trader.
     *
     * @param otherActor  the other actor involved in the interaction
     * @param direction   the direction of the interaction
     * @param map         the current game map
     * @return the list of allowable actions for the trader
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new TradeAction(this.getWeaponInventory()));
        }
        return actions;
    }
}

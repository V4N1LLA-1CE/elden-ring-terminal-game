package game.trading;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ExchangeAction;
import game.enums.Status;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;
import game.weapons.HeavyCrossbow;

/**
 * FingerReaderEnia is a subclass of the Trader class representing a non-playable merchant character in the game.
 * FingerReaderEnia has the ability to trade unique weapons with player.
 *
 * Created by:
 * @author Albert Liesman
 * modified by Austin Sofaer
 */
public class FingerReaderEnia extends Trader {
    private final static String NAME = "Finger Reader Enia";
    private final static char DISPLAY_CHAR = 'E';
    private final static int HIT_POINTS = 10000;

    /**
     Constructor
     */
    public FingerReaderEnia() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
        this.addWeaponToInventory(new AxeOfGodrick());
        this.addWeaponToInventory(new GraftedDragon());
    }

    /**
     * Retrieves the list of allowable actions for the trader when interacting with another actor.
     * Adds an ExchangeAction to the list of actions if the other actor is hostile to the trader.
     *
     * @param otherActor the other actor involved in the interaction
     * @param direction  the direction of the interaction
     * @param map        the current game map
     * @return the list of allowable actions for the trader
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new ExchangeAction(this.getWeaponInventory()));
        }
        return actions;
    }
}

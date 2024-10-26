package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.runes.RuneManager;
import game.runes.Runes;
import game.roles.Player;

/**
 * Represents the action of a player picking up runes from a location on the game map.
 *
 * Created by:
 * @author Kachun Lee
 */
public class PickUpRunesAction extends PickUpAction {
    private Runes runes;

    /**

     Constructor for PickUpRunesAction class.
     @param runes the runes that the player will pick up.
     */
    public PickUpRunesAction(Runes runes) {
        super(runes);
        this.runes = runes;
    }

    /**

     This method executes the action of picking up the runes by the player.
     Adds the value of the runes to the player's rune wallet.
     Removes the runes from the location on the game map.
     Resets player death count to zero.
     @param actor the actor performing the action (the player).
     @param map the map on which the action is performed.
     @return a String describing the action performed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.addRunes(runes.getRuneValue());
        map.locationOf(actor).removeItem(runes);
        Player.deathCount = 0;
        return menuDescription(actor);
    }

    /**

     This method returns a String describing the action of a player picking up runes.
     @param actor the actor performing the action (the player).
     @return a String describing the action performed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up $" + runes.getRuneValue() + " runes.";
    }
}


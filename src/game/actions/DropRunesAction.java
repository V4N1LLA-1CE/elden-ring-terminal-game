package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.runes.RuneManager;
import game.runes.Runes;
import game.roles.Player;

/**
 * The DropRunesAction class is responsible for dropping runes when a player dies in the game.
 *
 * Created by:
 * @author Austin Sofaer, Kachun Lee
 */
public class DropRunesAction extends DropAction {
    private Runes runes;

    /**

     Constructs a new DropRunesAction with the specified Runes object.
     @param runes the Runes object to be dropped
     */
    public DropRunesAction(Runes runes) {
        super(runes);
        this.runes = runes;
    }

    /**

     Executes the DropRunesAction by dropping the runes at the current location of the actor and decreasing the

     player's rune wallet by the same amount.

     @param actor the actor performing the action

     @param map the map containing the actor

     @return a string describing the outcome of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int runeAmount = ((Player) actor).getRuneWallet();

        if (runeAmount != 0) {
            Runes dropRunes = new Runes(runeAmount);
            RuneManager.minusRunes(runeAmount);
            //map.locationOf(actor).addItem(dropRunes);
            map.at(Player.prevLocation.x(), Player.prevLocation.y()).addItem(dropRunes);

            return menuDescription(actor);
        }
        return actor + " has been killed.";
    }

    /**

     Returns a string describing the action that was performed.
     @param actor the actor performing the action
     @return a string describing the action that was performed
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drops $" + runes.getRuneValue() + " runes.";
    }
}

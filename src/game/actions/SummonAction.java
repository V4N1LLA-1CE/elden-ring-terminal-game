package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.summoners.Ally;
import game.summoners.Invader;
import game.utilities.RandomNumberGenerator;

/**
 * An action representing the summoning of an ally or invader by an actor.
 *
 * Created by:
 * @author Ka Chun Lee
 */
public class SummonAction extends Action {
    /**
     * Constructor for the SummonAction class.
     */
    public SummonAction() {
    }

    /**
     * Executes the summon action by randomly summoning an ally or invader to the game map.
     *
     * @param actor the actor performing the summon action
     * @param map   the game map
     * @return a string describing the result of the summon action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        int x = (RandomNumberGenerator.getRandomInt(1, 100));

        if (x <= 50){
            Ally ally = new Ally(actor);

            // Check if the actor is on the ground with the display character '='
            if (map.locationOf(actor).getGround().getDisplayChar() == '='){
                for (Exit exitsAbove : map.locationOf(actor).getExits()){
                    if (!(exitsAbove.getDestination().containsAnActor()) && exitsAbove.getDestination().getDisplayChar() != '#'){
                        map.addActor(ally, exitsAbove.getDestination());
                        result += "An ally has been summoned.";
                        break;
                    }
                }
            }

            // Check if the actor is adjacent to a ground with the display character '='
            for (Exit exit : map.locationOf(actor).getExits()) {
                if (exit.getDestination().getGround().getDisplayChar() == '=') {
                    for (Exit exitOfSummonSign : exit.getDestination().getExits()){
                        if (!(exitOfSummonSign.getDestination().containsAnActor()) && exitOfSummonSign.getDestination().getDisplayChar() != '#') {
                            map.addActor(ally, exitOfSummonSign.getDestination());
                            result += "An ally has been summoned.";
                            break;
                        }
                    }
                }
            }
        }
        else{
            Invader invader = new Invader(actor);

            // Check if the actor is on the ground with the display character '='
            if (map.locationOf(actor).getGround().getDisplayChar() == '='){
                for (Exit exitsAbove : map.locationOf(actor).getExits()){
                    if (!(exitsAbove.getDestination().containsAnActor()) && exitsAbove.getDestination().getDisplayChar() != '#'){
                        map.addActor(invader, exitsAbove.getDestination());
                        result += "An invader has been summoned.";
                        break;
                    }
                }
            }

            // Check if the actor is adjacent to a ground with the display character '=' or if the actor itself has the display character '='
            for (Exit exit : map.locationOf(actor).getExits()) {
                if (exit.getDestination().getGround().getDisplayChar() == '=' || map.locationOf(actor).getDisplayChar() == '=') {
                    for (Exit exitOfSummonSign : exit.getDestination().getExits()){
                        if (!exitOfSummonSign.getDestination().containsAnActor() && exitOfSummonSign.getDestination().getDisplayChar() != '#') {
                            map.addActor(invader, exitOfSummonSign.getDestination());
                            result += "An invader has been summoned.";
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Describes the actor attempting to summon
     *
     * @param actor The actor performing the summon action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attempts to summon ";
    }
}

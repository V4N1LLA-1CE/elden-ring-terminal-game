package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action that represents traveling to a different location on the game map.
 * This action moves the actor from the current map to the target map at the specified coordinates (x, y).
 *
 * Created by:
 * @author Kachun Lee
 */
public class TravelAction extends Action {
    private int x;
    private int y;
    private GameMap targetMap;

    /**
     * Constructor for TravelAction.
     *
     * @param x         the x-coordinate of the target map
     * @param y         the y-coordinate of the target map
     * @param targetMap the target map to travel to
     */
    public TravelAction(int x, int y, GameMap targetMap){
        this.x = x;
        this.y = y;
        this.targetMap = targetMap;
    }

    /**
     * Executes the TravelAction, moving the actor from the current map
     * to the target map at the specified coordinates.
     *
     * @param actor the actor performing the action
     * @param map   the current game map
     * @return a description of the action's execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        if (targetMap.at(x,y).containsAnActor()){
            targetMap.removeActor(targetMap.at(x,y).getActor());
        }

        map.removeActor(actor);
        targetMap.addActor(actor, targetMap.at(x,y));

        result += actor + " travels to " + menuDescription(actor).substring(10) + ".";

        return result;
    }

    /**
     * Retrieves the description of the TravelAction for the menu.
     * Returns different descriptions based on the target map's ground display character.
     *
     * @param actor the actor performing the action
     * @return the description of the TravelAction for the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        if (targetMap.at(0, 0).getGround().getDisplayChar() == '#'){
            return "Travel to Round Table";
        }
        else if (targetMap.at(0, 3).getGround().getDisplayChar() == '#'){
            return "Travel to Snow Veil Castle";
        }
        else if (targetMap.at(0, 0).getGround().getDisplayChar() == '+'){
            return "Travel to Boss Room";
        }
        else if (targetMap.at(0, 0).getGround().getDisplayChar() == '_'){
            return "Travel to Lake of Rot";
        }

        return "Travel to Limgrave";
    }
}

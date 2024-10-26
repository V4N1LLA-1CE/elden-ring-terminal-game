package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.enums.Status;

/**
 * A class that represents a Golden Fog Door in the game environment.
 * The Golden Fog Door allows the player to travel between one map to another.
 *
 * Created by:
 * @author Albert Liesman, Kachun Lee
 */
public class GoldenFogDoor extends Ground {
    private int x;
    private int y;
    private GameMap targetMap;

    /**
     * Constructor for GoldenFogDoor.
     *
     * @param x         the x-coordinate of the target map for travel
     * @param y         the y-coordinate of the target map for travel
     * @param targetMap the target map to travel to
     */
    public GoldenFogDoor(int x, int y, GameMap targetMap) {
        super('D');
        this.x = x;
        this.y = y;
        this.targetMap = targetMap;
    }

    /**
     * Retrieves the allowable actions for an actor on the Golden Fog Door.
     * Only Player can perform TravelAction.
     *
     * @param actor     the actor attempting to perform actions
     * @param location  the location of the Golden Fog Door
     * @param direction the direction of the actor's movement
     * @return the list of allowable actions for the actor
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new TravelAction(x, y, targetMap));
        }
        return actions;
    }

    /**
     * Performs the tick action for the Golden Fog Door.
     *
     * @param location the location of the Golden Fog Door
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
    }
}

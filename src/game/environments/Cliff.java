package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.enums.Status;

/**
 * A class that represents a cliff which kills to those who walks in.
 * Created by:
 * @author Albert Liesman
 * Modified by: Austin Sofaer
 *
 */
public class Cliff extends Ground {
    /**

     Constructor for Cliff
     */
    public Cliff() {
        super('+');
        this.addCapability(Status.CLIFF);
    }

    /**
     * Overides the tick method to execute DeathAction to the actor that has the 'RESPAWNABLE' capability.
     * The DeathAction is executed to kill the actor.
     *
     * @param location the location of the cliff
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().hasCapability(Status.RESPAWNABLE)){
            new DeathAction(this).execute(location.getActor(), location.map());
        }
    }
    /**
     * Determines if an actor can enter the cliff.
     * An Enemies cannot enter the cliff
     *
     * @param actor the actor attempting to enter the cliff
     * @return true if the actor can enter the cliff, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }
}

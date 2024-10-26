package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;
import game.enums.Status;

public class SummonSign extends Ground {
    /**
     * Constructor.
     *
     */
    public SummonSign() {
        super('=');
    }


    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (actor.hasCapability(Status.RESPAWNABLE)){
            actions.add(new SummonAction());
        }
        return actions;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
    }
}

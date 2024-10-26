package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;

public class DespawnBehaviour implements Behaviour {
    private Actor actor;
    public DespawnBehaviour(Actor actor) {
        this.actor = actor;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new DespawnAction(this.actor, map);
    }
}

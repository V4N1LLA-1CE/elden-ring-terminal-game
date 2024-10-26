package game.item;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A Consume interface
 *
 * Created by:
 * @author Kachun Lee
 */
public interface ConsumeInterface {
    String consume (Actor actor);
    String menuText (Actor actor);
}
package game.weaponskills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Abilities;
import game.utilities.RandomNumberGenerator;
import game.actions.DeathAction;
import game.actions.SpawnPileOfBonesAction;

/**
 *  A skill action from GreatKnife.
 *  Actor stabs an opponent with a weapon and then moves to a different location.
 *
 * Created by:
 * @author Kachun Lee
 */
public class Quickstep extends Action {
    private Actor target;
    private String direction;
    private Weapon weapon;

    /**

     Constructor for Quickstep.
     @param target the actor being attacked
     @param direction the direction the attacking actor should move after the attack
     @param weapon the weapon used for the attack
     */
    public Quickstep (Actor target, String direction, Weapon weapon){
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     Executes the Quickstep action, which involves an actor stabbing an opponent with a weapon and then moving to a different location.

     @param actor the actor performing the action
     @param map the gamemap the actor is on
     @return a string description of the action that was performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (RandomNumberGenerator.getRandomInt(1, 100) >= weapon.chanceToHit()){
            return actor + " missed the " + weapon.verb() + " attack.";
        }

        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + "s " + target + " for " + damage + " damage.\n";
        target.hurt(damage);

        for (Exit exit : map.locationOf(actor).getExits()) {
            if (!exit.getDestination().containsAnActor()) {
                map.moveActor(actor, exit.getDestination());
                result += actor + " moves to " + exit.getName() + " (" + exit.getDestination().x() + ", " + exit.getDestination().y() + ")";
                break;
            }
        }
        // IF TARGET ISN'T SKELETAL SWORDSMAN
        if (!target.isConscious() && !target.hasCapability(Abilities.SPAWN_PILE_OF_BONES)) {
            result += new DeathAction(actor).execute(target, map);
        }
        // IF TARGET IS A SKELETAL SWORDSMAN
        else if (!target.isConscious() && target.hasCapability(Abilities.SPAWN_PILE_OF_BONES)){
            result += new SpawnPileOfBonesAction().execute(target, map);
        }
        return result;
    }

    /**

     Returns a string describing the Quickstep action.
     @param actor the actor performing the action
     @return a string describing the Quickstep action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " stabs " + target + " with " + weapon;
    }
}

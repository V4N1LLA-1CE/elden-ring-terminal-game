package game.uniqueattacks;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Abilities;
import game.utilities.RandomNumberGenerator;
import game.actions.DeathAction;
import game.actions.SpawnPileOfBonesAction;

/**
 * The SlamAttackAOE class represents an action that allows an actor to perform a slam attack with an area of effect (AOE).
 * This action will target all Actors within one space in all directions from the Actor performing the action, and deal damage
 * to each Actor hit with a chance to miss based on the weapon's chance to hit.
 *
 * Created by:
 * @author Kachun Lee
 */
public class SlamAttackAOE extends Action {
    private Actor target;
    private String direction;
    private IntrinsicWeapon intrinsicWeapon;

    /**

     Constructor for the SlamAttackAOE class.
     @param target the target actor being attacked
     @param direction the direction of the attack
     @param intrinsicWeapon the weapon used for the attack
     */
    public SlamAttackAOE(Actor target, String direction, IntrinsicWeapon intrinsicWeapon) {
        this.target = target;
        this.direction = direction;
        this.intrinsicWeapon = intrinsicWeapon;
    }

    /**

     Executes the slam attack on all Actors within one space in all directions from the Actor performing the action.

     @param actor the actor performing the slam attack

     @param map the game map containing the Actor and target locations

     @return a String describing the result of the slam attack
     */
    @Override
    public String execute (Actor actor, GameMap map) {
        String result = "";
        int damage = intrinsicWeapon.damage();

        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor target = exit.getDestination().getActor();
                if (target != null) {
                    if ((RandomNumberGenerator.getRandomInt(1, 100) >= intrinsicWeapon.chanceToHit())) {
                        result += actor + " missed the " + intrinsicWeapon.verb() + " (AOE) attack on " + target + ".\n";
                    }
                    else {
                        target.hurt(damage);
                        result += actor + " " + intrinsicWeapon.verb() + " (AOE) " + target + " for " + damage + " damage.\n";
                        if (!target.isConscious() && !target.hasCapability(Abilities.SPAWN_PILE_OF_BONES)) {
                            result += new DeathAction(actor).execute(target, map);
                        }
                        else if (!target.isConscious() && target.hasCapability(Abilities.SPAWN_PILE_OF_BONES)) {
                            result += new SpawnPileOfBonesAction().execute(target, map);
                        }
                    }
                }
            }
        }
        return result.trim();
    }

    /**

     Returns a String describing the slam attack action.
     @param actor the actor performing the slam attack
     @return a String describing the slam attack action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " slams " + target + " for " + intrinsicWeapon.damage() + " damage.";
    }
}

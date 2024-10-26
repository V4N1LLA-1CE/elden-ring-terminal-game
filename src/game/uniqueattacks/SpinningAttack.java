package game.uniqueattacks;

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
 * An action that allows an actor to perform a spinning attack, hitting anything in the surrounding area with a given weapon.
 *
 * Created by:
 * @author Kachun Lee
 */
public class SpinningAttack extends Action {
    private Actor target;
    private String direction;
    private Weapon weapon;

    /**

     Constructor for the SpinningAttack class.
     @param target the target actor to attack
     @param direction the direction in which the attack is performed
     @param weapon the weapon used to perform the attack
     */
    public SpinningAttack(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**

     Executes the spinning attack action on the given target and surrounding actors using the given weapon.

     @param actor the actor performing the action

     @param map the gamemap containing the actors

     @return a string message describing the result of the action
     */
    @Override
    public String execute (Actor actor, GameMap map){
        String result = "";

        int damage = weapon.damage();

        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor target = exit.getDestination().getActor();
                if (target != null) {
                    if ((RandomNumberGenerator.getRandomInt(1, 100) >= weapon.chanceToHit())) {
                        result += actor + " missed the " + weapon.verb() + " (AOE) attack on " + target + ".\n";
                    }
                    else {
                        target.hurt(damage);
                        result += actor + " " + weapon.verb() + " (AOE) " + target + " for " + damage + " damage.\n";
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

     Returns a description of the SpinningAttack action for display in the menu.
     @param actor the actor performing the action
     @return a string description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks anything in the surrounding with " + weapon;
    }
}

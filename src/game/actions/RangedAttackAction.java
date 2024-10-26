package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Abilities;
import game.utilities.RandomNumberGenerator;

/**
 * An action representing a ranged attack by an actor using a weapon.
 *
 * Created by:
 * @author Ka Chun Lee
 */
public class RangedAttackAction extends Action {
    private Actor target;
    private Weapon weapon;

    /**
     * Constructor for the RangedAttackAction class.
     *
     * @param target the target actor to be attacked
     * @param weapon the weapon used for the ranged attack
     */
    public RangedAttackAction(Actor target, Weapon weapon) {
        this.target = target;
        this.weapon = weapon;
    }

    /**
     * Retrieves the target actor of the ranged attack.
     *
     * @return the target actor
     */
    public Actor getTarget() {
        return target;
    }

    /**
     * Retrieves the weapon used for the ranged attack.
     *
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Executes the ranged attack action by calculating the damage and applying it to the target actor.
     * It also checks if the target actor is unconscious or defeated after the attack.
     *
     * @param actor the actor performing the ranged attack
     * @param map   the game map
     * @return a string describing the result of the ranged attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int damage = weapon.damage();
        String result = "";


        if ((RandomNumberGenerator.getRandomInt(1, 100) >= weapon.chanceToHit())) {
            result = actor + " missed the shot on " + target + ".";
        }
        else{
            target.hurt(damage);
            result += actor + " " + weapon.verb() + "s " + target + " for " + damage + " damage.\n";

            if (!target.isConscious() && !target.hasCapability(Abilities.SPAWN_PILE_OF_BONES)) {
                result += new DeathAction(actor).execute(target, map);
            }
            else if (!target.isConscious() && target.hasCapability(Abilities.SPAWN_PILE_OF_BONES)) {
                result += new SpawnPileOfBonesAction().execute(target, map);
            }
        }
        return result;
    }

    /**
     * Describes which target the actor is attacking with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " shoots " + target + " at range using " + weapon;
    }
}
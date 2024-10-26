package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Abilities;
import game.summoners.SummonerType;
import game.utilities.RandomNumberGenerator;
import game.actions.AttackAction;
import game.uniqueattacks.SlamAttackAOE;

/**
 * This class implements the Behaviour interface and represents the behavior of attacking an enemy.
 * It selects the appropriate action based on the situation and the actor's capabilities, such as the
 * type of weapon and the existence of specific abilities.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class AttackBehavior implements Behaviour {

    private final Actor target;

    /**
     * Constructs an AttackBehavior object with the specified target.
     *
     * @param subject the target of the attack
     */
    public AttackBehavior(Actor subject) {
        this.target = subject;
    }

    /**
     * Returns the action to be performed when the actor encounters an enemy.
     * If the actor and target are not present in the same location, returns null.
     * If the actor does not have any weapon in its inventory, uses the intrinsic weapon.
     * If the actor has a weapon, uses the first weapon in its inventory.
     * If the target is hostile and in the actor's line of sight, uses the weapon's skill or a SlamAttackAOE.
     * If the actor has the Slam ability and is non-hostile to a certain enemy type, uses a SlamAttackAOE.
     * If the target is not hostile, returns null.
     *
     * @param actor the actor performing the attack
     * @param map   the GameMap containing the actors
     * @return the AttackAction or SlamAttackAOE action to be performed, or null if the actor and target are not in the same location or the target is not hostile
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor)) {
            return null;
        }

        Weapon weapon = null;

        if (actor.getWeaponInventory().isEmpty()) {
            weapon = actor.getIntrinsicWeapon();
        } else {
            weapon = actor.getWeaponInventory().get(0);
        }

        int x = (RandomNumberGenerator.getRandomInt(1, 100));

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            // IF TARGET IS AROUND
            if (destination.containsAnActor() && destination.getActor() == target) {
                String direction = exit.getName();
                // IF ALLY OR INVADER IS ATTACKING
                if (actor.hasCapability(SummonerType.ALLY) || actor.hasCapability(SummonerType.INVADER)){
                    return new AttackAction(target, direction, weapon);
                }
                // rng pass if x <= 50
                if (x <= 50) {
                    // check whether actor can spin attack or slam
                    if (actor.hasCapability(Abilities.SLAM)) {
                        return new SlamAttackAOE(target, direction, actor.getIntrinsicWeapon());
                    } else if (weapon.getSkill(target, direction) != null) {
                        return weapon.getSkill(target, direction);
                    }
                }
                // rng fail if x > 50 :
                return new AttackAction(target, direction, weapon);
            }
        }
        return null;
    }
}

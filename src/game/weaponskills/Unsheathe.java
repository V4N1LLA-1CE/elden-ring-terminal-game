package game.weaponskills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Abilities;
import game.utilities.RandomNumberGenerator;
import game.actions.DeathAction;
import game.actions.SpawnPileOfBonesAction;

/**
 *  A skill action from Uchigatana.
 *  Actor attacks an opponent with double damage but lower accuracy.
 *
 * Created by:
 * @author Kachun Lee
 */
public class Unsheathe extends Action {
    private Actor target;
    private String direction;
    private Weapon weapon;

    /**

     Constructor for the Unsheathe class.
     @param target the target Actor that is being attacked.
     @param direction the direction of the attack.
     @param weapon the weapon used for the attack.
     */
    public Unsheathe (Actor target, String direction, Weapon weapon){
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**

     Executes the Unsheathe action.

     @param actor the Actor performing the action.

     @param map the GameMap containing the Actors and locations.

     @return a String message describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (RandomNumberGenerator.getRandomInt(1, 100) >= weapon.chanceToHit()){
            return actor + " missed the " + weapon.verb() + " attack.";
        }
        int damage = (weapon.damage()) * 2;
        String result = actor + " " + weapon.verb() + "es " + target + " for " + damage + " damage.";
        target.hurt(damage);
        // IF TARGET ISN'T SKELETAL SWORDSMAN
        if (!target.isConscious() && !target.hasCapability(Abilities.SPAWN_PILE_OF_BONES)) {
            result += new DeathAction(actor).execute(target, map);
        }
        // IF TARGET IS A SKELETAL SWORDSMAN
        else if (!target.isConscious() && target.hasCapability(Abilities.SPAWN_PILE_OF_BONES)){
            result += new SpawnPileOfBonesAction().execute(target, map);
        }
        //result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    /**

     Returns a String message describing the action.
     @param actor the Actor performing the action.
     @return a String message describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathes " + weapon + " on " + target;
    }
}

package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.runes.RuneManager;
import game.runes.RunesInterface;
import game.summoners.SummonerType;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Albert Liesman, Austin Sofaer, Kachun Lee
 *
 */
public class DeathAction extends Action {
    private Actor attacker = null;
    private Ground groundAttacker = null;
    private int runesToAdd = 0;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }
    public DeathAction(Ground groundAttacker){
        this.groundAttacker = groundAttacker;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        ActionList dropActions = new ActionList();

        // IF AN ACTOR DIES TO A GROUND
        if (this.groundAttacker != null){
            // IF THE GROUND IS A CLIFF
            if (target.hasCapability(Status.RESPAWNABLE) && map.locationOf(target).getGround().hasCapability(Status.CLIFF)){
                target.hurt(100000);
                return "\n" + target + " fell off a " + groundAttacker + ".\n";
            }
        }

        // IF PLAYER DIES TO AN ACTOR
        if (target.hasCapability(Status.RESPAWNABLE) && target.hasCapability(Status.HOSTILE_TO_ENEMY)){
            return "\n" + target + " died to " + attacker + ".\n";
        }
        // IF ALLY DIES
        if (target.hasCapability(SummonerType.ALLY)){
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);
        }

        // IF PLAYER IS ATTACKER
        if (attacker.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);

            for (Item item: target.getItemInventory()){
                if (item != null && item.hasCapability(Status.RUNE)){
                    runesToAdd = ((RunesInterface) item).getRuneValue();
                    RuneManager.addRunes(runesToAdd);
                    result += System.lineSeparator() + target + " transferred $" + runesToAdd + " runes";
                }
            }
        }
        result += System.lineSeparator() + menuDescription(target);
        // remove actor
        map.removeActor(target);
        return result;
    }

    /**
     * Describes the death of actor
     *
     * @param actor The actor that died.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.\n";
    }
}

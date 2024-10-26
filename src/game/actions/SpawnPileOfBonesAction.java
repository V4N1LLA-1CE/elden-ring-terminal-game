package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.PileOfBones;
import game.enemies.SkeletonType;
import game.weapons.Grossmesser;
import game.weapons.Scimitar;

/**
 * An action that spawns a PileOfBones on the map in the place of a defeated skeleton enemy.
 *
 * Created by:
 * @author Austin Sofaer
 * modified by Albert Liesman
 */
public class SpawnPileOfBonesAction extends Action {
    private Location thisLocation;
    private PileOfBones pileOfBones;

    public SpawnPileOfBonesAction(){}

    /**

     Executes the action of spawning a PileOfBones on the map in the place of a defeated skeleton enemy.

     @param actor the actor performing the action

     @param map the map where the action is taking place

     @return a description of the action performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        thisLocation = map.locationOf(actor);
        pileOfBones = new PileOfBones(actor);

        // getting the weapon according to the skeleton type
        if (actor.hasCapability(SkeletonType.SWORDSMAN)){
            pileOfBones.addWeaponToInventory(new Grossmesser());
        }
        if (actor.hasCapability(SkeletonType.BANDIT)){
            pileOfBones.addWeaponToInventory(new Scimitar());
        }

        map.removeActor(actor);
        map.addActor(pileOfBones, thisLocation);
        return menuDescription(pileOfBones);
    }

    /**

     Returns a description of the action that will be displayed in the menu.
     @param actor the actor performing the action
     @return a description of the action that will be displayed in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "\n" + actor + " has spawned on the map!\n";
    }
}

package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.RangedAttackAction;
import game.behaviours.*;
import game.enums.Status;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utilities.ObstacleCheckForRanged;
import game.utilities.RandomNumberGenerator;
import game.weapons.WeaponSkill;
import java.util.HashMap;
import java.util.Map;

/**
 * This abstract class represents a hostile NPC in a game.
 * It has several behaviors that dictate its actions during its turn.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */

public abstract class Enemies extends Actor implements Resettable {
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();
    private Actor target;
    private int attackBehaviorCount = 10;
    private GameMap map;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemies(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(999, new WanderBehaviour());
        this.generateRunes();
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     Abstract method to generate runes for enemies.
     */
    public abstract void generateRunes();

    /**

     Returns a list of allowable actions for this NPC given its current state and the game map.

     @param otherActor the other actor in the game

     @param direction the direction the NPC is facing

     @param map the game map

     @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && this.isConscious()) {
            for (WeaponItem weapon : otherActor.getWeaponInventory()) {
                if (weapon.hasCapability(WeaponSkill.HAS_SKILL)){
                    actions.add(weapon.getSkill(this, direction));
                    actions.add(new AttackAction(this, direction, weapon));
                }
                else {
                    actions.add(new AttackAction(this, direction, weapon));
                }
            }
        }
        return actions;
    }

    /**

     Plays the turn for this NPC, including selecting an action based on its behaviors.

     @param actions a list of allowable actions for this NPC

     @param lastAction the last action taken by this NPC

     @param map the game map

     @param display the game display

     @return the selected action for this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.map = map;
        boolean playerFound = false;
        if (this.behaviours.get(998) != null){
            this.behaviours.remove(998);
        }
        // 10% chance of despawning each turn
        if (RandomNumberGenerator.getRandomInt(1, 100) <= 10){
            this.behaviours.put(998, new DespawnBehaviour(this));
        }

        Actor otherActor;
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            // CHECK TO SEE IF PLAYER IS AROUND
            if (destination.containsAnActor() && (map.getActorAt(destination).hasCapability(Status.HOSTILE_TO_ENEMY))){
                target = map.getActorAt(destination);
                playerFound = true;
                // IF PLAYER IS AROUND, FOLLOW AND ATTACK
                if (this.behaviours.get(3) == null){
                    this.behaviours.put(3, new AttackBehavior(target));
                }
                if (this.behaviours.get(2) == null){
                    this.behaviours.put(2, new FollowBehaviour(target));
                }
            }
        }

        for (Exit exit: map.locationOf(this).getExits()){
            Location destination = exit.getDestination();
            if (destination.containsAnActor()){
                otherActor = map.getActorAt(destination);
                if (this.hasCapability(Status.NON_HOSTILE_TO_WOLF) && otherActor.hasCapability(Status.NON_HOSTILE_TO_WOLF)){
                    break;
                }
                else if (this.hasCapability(Status.NON_HOSTILE_TO_SKELETON) && otherActor.hasCapability(Status.NON_HOSTILE_TO_SKELETON)){
                    break;
                }
                else if (this.hasCapability(Status.NON_HOSTILE_TO_CRAB) && otherActor.hasCapability(Status.NON_HOSTILE_TO_CRAB)){
                    break;
                }
                else if (this.hasCapability(Status.STORMVEIL_ENEMIES) && otherActor.hasCapability(Status.STORMVEIL_ENEMIES)){
                    break;
                }
                else{
                    this.behaviours.put(attackBehaviorCount, new AttackBehavior(otherActor));
                    attackBehaviorCount++;
                }
            }
        }

        // Ranged attack for enemies
        for (WeaponItem weaponItem : this.getWeaponInventory()){
            if (weaponItem != null && weaponItem.hasCapability(WeaponSkill.CROSSBOW)){
                int currLocForRangedCheckX = map.locationOf(this).x();
                int currLocForRangedCheckY = map.locationOf(this).y();

                boolean obstacleFlag = false;
                boolean playerFound1 = false;

                Actor actor = null;

                for (int i = Math.max(currLocForRangedCheckX - 2, 0); i <= Math.min(currLocForRangedCheckX + 2, map.getXRange().max()); i++) {
                    for (int j = Math.max(currLocForRangedCheckY - 2, 0); j <= Math.min(currLocForRangedCheckY + 2, map.getYRange().max()); j++) {
                        if (i == currLocForRangedCheckX && j == currLocForRangedCheckY) {
                            continue;
                        }
                        if (map.at(i, j).containsAnActor() && (map.at(i, j).getActor().hasCapability(Status.HOSTILE_TO_ENEMY))){
                            obstacleFlag = ObstacleCheckForRanged.ObstacleCheck(map, map.locationOf(this), map.at(i, j));
                            if (!obstacleFlag){
                                //this.behaviours.put(1, new AttackBehavior(map.at(i, j).getActor()));
                                actor = map.at(i, j).getActor();
                                playerFound1 = true;
                                break;
                            }
                        }
                    }
                }
                if (playerFound1 && actor != null){
                    //this.behaviours.put(1, new RangedAttackBehaviour(actor));
                    return new RangedAttackAction(actor, actor.getWeaponInventory().get(0));
                }
            }
            else {
                break;
            }
        }


        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     Despawn the NPC when reset is happening.

     @param resting whether this NPC is resting
     */
    @Override
    public void reset(Boolean resting) {
        this.behaviours.put(0, new DespawnBehaviour(this));
    }
}

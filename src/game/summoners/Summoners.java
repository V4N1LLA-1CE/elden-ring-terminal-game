package game.summoners;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.roles.RoleAbstract;
import game.weapons.WeaponSkill;
import java.util.HashMap;
import java.util.Map;

/**
 * Summoners is an abstract class representing a summoner character in the game.
 * It extends the Actor class and provides common functionality for summoners.
 *
 * Created by:
 * @author Kachun Lee
 */
public abstract class Summoners extends Actor {
    protected RoleAbstract role;
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();
    private Actor target;
    private GameMap map;
    private Actor player;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param player      the Actor that summons this summoner
     */
    public Summoners(String name, char displayChar, int hitPoints, Actor player) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(999, new WanderBehaviour());
        // starting role setup
        this.role = generateRole();
        this.addWeaponToInventory(this.role.getWeapon());
        this.resetMaxHp(this.role.getHitPoints());
        this.player = player;
    }

    /**
     * Overrides the playTurn method from the Actor class to determine the action to be performed by the summoner on its turn.
     *
     * @param actions    the list of possible actions for the summoner
     * @param lastAction the last action performed by the summoner
     * @param map        the current game map
     * @param display    the display object for rendering the game
     * @return the chosen action to be performed by the summoner
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Overrides the allowableActions method from the Actor class to determine the list of allowable actions for the summoner.
     *
     * @param otherActor the other actor involved in the interaction
     * @param direction  the direction of the interaction
     * @param map        the current game map
     * @return the list of allowable actions for the summoner
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actions = new ActionList();
        if (this.hasCapability(SummonerType.INVADER)){
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
        }

        return actions;
    }

    /**
     * Retrieves the player that summoned this summoner.
     *
     * @return the player actor
     */
    public Actor getPlayer(){
        return this.player;
    }

    /**
     * Generates the role for the summoner.
     * Subclasses must implement this method to provide specific role generation logic.
     *
     * @return the generated role for the summoner
     */
    public abstract RoleAbstract generateRole();
}

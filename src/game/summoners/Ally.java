package game.summoners;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.*;
import game.enums.Status;
import game.roles.*;
import game.utilities.RandomNumberGenerator;

/**
 * Ally is a subclass of Summoners representing a friendly summoner character that supports the player.
 * The Ally has various behaviors and roles that determine its actions during the game.
 * Ally can be summoned in SummonedSign.
 *
 * Created by:
 * @author Austin Sofaer
 */
public class Ally extends Summoners {
    private final static String NAME = "Ally";
    private final static char DISPLAY_CHAR = 'A';
    private final static int HITPOINTS = 100;
    private int behaviourCount = 10;

    /**
     * Constructor for the Ally class.
     *
     * @param player the actor that summons the Ally
     */
    public Ally(Actor player) {
        super(NAME, DISPLAY_CHAR, HITPOINTS, player);
        this.addCapability(Status.FRIENDLY);
        this.addCapability(SummonerType.ALLY);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Overrides the playTurn method from the Summoners class to determine the action to be performed by the Ally on its turn.
     * The Ally can add attack behaviors and check if the player is still conscious.
     *
     * @param actions    the list of possible actions for the Ally
     * @param lastAction the last action performed by the Ally
     * @param map        the current game map
     * @param display    the display object for rendering the game
     * @return the chosen action to be performed by the Ally
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (this.behaviours.get(0) != null){
            this.behaviours.remove(0);
        }

        // IF PLAYER DIES, THEN ALLY DESPAWNS
        if (!this.getPlayer().isConscious()){
            this.behaviours.put(0, new DespawnBehaviour(this));
        }

        // SEE IF ENEMY IS AROUND AND ADD ATTACK BEHAVIOUR
        for (Exit exit : map.locationOf(this).getExits()){
            // IF EXIT CONTAINS AN ACTOR THAT IS NOT FRIENDLY, WE ADD ATTACK BEHAVIOUR
            if (exit.getDestination().containsAnActor() && !exit.getDestination().getActor().hasCapability(Status.FRIENDLY)){
                this.behaviours.put(behaviourCount, new AttackBehavior(exit.getDestination().getActor()));
                behaviourCount++;
            }
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Generates a random role for the Ally from a predefined set of available roles.
     *
     * @return the generated RoleAbstract object representing the role of the Ally
     */
    @Override
    public RoleAbstract generateRole() {
        RoleAbstract[] availableRoles = {new Astrologer(), new Bandit(), new Samurai(), new Wretch()};
        int randomIndex = RandomNumberGenerator.getRandomInt(availableRoles.length);
        return availableRoles[randomIndex];
    }
}

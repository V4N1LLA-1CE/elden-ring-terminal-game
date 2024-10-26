package game.summoners;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.AttackBehavior;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.roles.*;
import game.runes.Runes;
import game.utilities.RandomNumberGenerator;

/**
 * Invader is a subclass of Summoners representing an enemy summoner character that invades the game world.
 * The Invader has various behaviors and roles that determine its actions during the game.
 * Invader can be summoned in SummonSign.
 *
 * Created by:
 * @author Austin Sofaer
 */
public class Invader extends Summoners {
    private final static String NAME = "Invader";
    private final static char DISPLAY_CHAR = 'ඞ';
    private final static int HITPOINTS = 100;
    private final static int INVADER_RUNES_LO = 1358;
    private final static int INVADER_RUNES_HI = 5578;
    private int behaviourCount = 10;

    /**
     * Constructor for the Invader class.
     *
     * @param player the actor that summons the Invader
     */
    public Invader(Actor player) {
        super(NAME, DISPLAY_CHAR, HITPOINTS, player);
        this.generateRunes();
        this.addCapability(SummonerType.INVADER);
        this.behaviours.put(1, new AttackBehavior(this.getPlayer()));
    }

    /**
     * Overrides the playTurn method from the Summoners class to determine the action to be performed by the Invader on its turn.
     * The Invader can add follow behaviors, attack behaviors, and check if the player or other enemies are nearby.
     *
     * @param actions    the list of possible actions for the Invader
     * @param lastAction the last action performed by the Invader
     * @param map        the current game map
     * @param display    the display object for rendering the game
     * @return the chosen action to be performed by the Invader
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.behaviours.get(0) != null){
            this.behaviours.remove(0);
        }
        // CHECK TO SEE IF PLAYER IS IN THE EXIT, IF THEY ARE THEN FOLLOW
        for (Exit exit : map.locationOf(this).getExits()){
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(Status.PLAYER)){
                this.behaviours.put(0, new FollowBehaviour(exit.getDestination().getActor()));
                break;
            }
        }

        // SEE IF ENEMY IS AROUND AND ADD ATTACK BEHAVIOUR
        for (Exit exit : map.locationOf(this).getExits()){
            // IF EXIT CONTAINS: ACTOR THATS NOT FRIENLY, ACTOR THAT IS AN ALLY, AND ACTOR ISN'T INVADER
            if (exit.getDestination().containsAnActor() && (!exit.getDestination().getActor().hasCapability(Status.FRIENDLY) || exit.getDestination().getActor().hasCapability(SummonerType.ALLY) )){
                if (!exit.getDestination().getActor().hasCapability(SummonerType.INVADER)){
                    this.behaviours.put(behaviourCount, new AttackBehavior(exit.getDestination().getActor()));
                    behaviourCount++;
                }
            }
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Generates a random role for the Invader from a predefined set of available roles.
     *
     * @return the generated RoleAbstract object representing the role of the Invader
     */
    @Override
    public RoleAbstract generateRole() {
        RoleAbstract[] availableRoles = {new Astrologer(), new Bandit(), new Samurai(), new Wretch()};
        int randomIndex = RandomNumberGenerator.getRandomInt(availableRoles.length);

        return availableRoles[randomIndex];
    }

    /**
     * Generates a random number of runes
     */
    public void generateRunes(){
        this.addItemToInventory(new Runes(RandomNumberGenerator.getRandomInt(INVADER_RUNES_LO, INVADER_RUNES_HI)));
    }
}

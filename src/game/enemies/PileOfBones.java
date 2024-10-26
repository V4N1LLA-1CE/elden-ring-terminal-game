package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Abilities;
import game.utilities.RandomNumberGenerator;
import game.runes.Runes;
import game.enums.Status;
import game.actions.ReviveSkeletonEnemyAction;

/**
 * This class represents a Pile of Bones, which is an enemy that spawns when a Skeletal enemy dies.
 * The Pile of Bones will revive the original enemy after three turns, and has the capability of generating runes.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class PileOfBones extends Enemies {
    private final static String PILE_OF_BONES_SWORDSMAN_NAME = "Pile Of Bones";
    private final static char PILE_OF_BONES_SWORDSMAN_DISPLAY_CHAR = 'X';
    private final static int PILE_OF_BONES_SWORDSMAN_HIT_POINTS = 1;
    private Actor originalEnemy;
    private int turnCounter;

    /**

     Constructor for the PileOfBones class.
     @param originalEnemy the original enemy that the Pile of Bones will revive
     */
    public PileOfBones(Actor originalEnemy) {
        super(PILE_OF_BONES_SWORDSMAN_NAME, PILE_OF_BONES_SWORDSMAN_DISPLAY_CHAR, PILE_OF_BONES_SWORDSMAN_HIT_POINTS);
        this.hasCapability(Abilities.REVIVE_HEAVY_SKELETAL_ENEMY);
        this.addCapability(Status.NON_HOSTILE_TO_SKELETON);
        this.addCapability(Status.PILE_OF_BONES);
        this.originalEnemy = originalEnemy;
    }

    /**

     Overrides the playTurn method from the Actor class to execute the Pile of Bones' behavior each turn.
     The Pile of Bones will revive the original enemy after three turns, or do nothing if it hasn't been three turns yet.
     @param actions a collection of possible actions for the Pile of Bones
     @param lastAction the previous action taken by the Pile of Bones
     @param map the map containing the Pile of Bones
     @param display the display that will show the game
     @return an action to be executed by the Pile of Bones
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        turnCounter++;
        if (turnCounter == 3){
            originalEnemy.heal(999999);
            return new ReviveSkeletonEnemyAction(originalEnemy);
        }
        return new DoNothingAction();
    }

    /**

     Generates a random number of runes and adds them to the Pile of Bones' inventory.
     */
    public void generateRunes(){
        this.addItemToInventory(new Runes(RandomNumberGenerator.getRandomInt(318, 4961)));
    }
}

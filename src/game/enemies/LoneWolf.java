package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Status;
import game.runes.Runes;
import game.utilities.RandomNumberGenerator;

/**
 * LoneWolf is a subclass of Enemies, representing a lone wolf enemy in the game.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class LoneWolf extends Enemies {

    private final static String LONE_WOLF_NAME =  "Lone wolf";
    private final static char LONE_WOLF_DISPLAY_CHAR = 'h';
    private final static int LONE_WOLF_HIT_POINTS = 102;
    private final static int LONE_WOLF_DAMAGE = 97;
    private final static String LONE_WOLF_ATTACK_VERB = "bites";
    private final static int LONE_WOLF_ATTACK_ACCURACY = 95;
    private final static int LONE_WOLF_RUNES_LO = 55;
    private final static int LONE_WOLF_RUNES_HI = 1470;

    /**

     Constructor for LoneWolf.
     */
    public LoneWolf() {
        super(LONE_WOLF_NAME, LONE_WOLF_DISPLAY_CHAR, LONE_WOLF_HIT_POINTS);
        this.addCapability(Status.NON_HOSTILE_TO_WOLF);
    }
    /**

     Generates a random number of runes for the LoneWolf and adds them to its inventory.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(LONE_WOLF_DAMAGE, LONE_WOLF_ATTACK_VERB, LONE_WOLF_ATTACK_ACCURACY);
    }
    /**

     Returns an IntrinsicWeapon object representing the LoneWolf's attack.
     @return The LoneWolf's IntrinsicWeapon object.
     */
    public void generateRunes(){
        this.addItemToInventory(new Runes(RandomNumberGenerator.getRandomInt(LONE_WOLF_RUNES_LO, LONE_WOLF_RUNES_HI)));
    }
}

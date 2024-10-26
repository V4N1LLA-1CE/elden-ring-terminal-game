package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Abilities;
import game.enums.Status;
import game.runes.Runes;
import game.utilities.RandomNumberGenerator;

/**
 * GiantCrab is a subclass of Enemies, representing a giant crab enemy in the game.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class GiantCrab extends Enemies {
    private final static String GIANT_CRAB_NAME = "Giant Crab";
    private final static char GIANT_CRAB_DISPLAY_CHAR = 'C';
    private final static int GIANT_CRAB_HIT_POINTS = 407;
    private final static int GIANT_CRAB_DAMAGE = 208;
    private final static String GIANT_CRAB_ATTACK_VERB = "slams";
    private final static int GIANT_CRAB_ATTACK_ACCURACY = 90;
    private final static int GIANT_CRAB_RUNES_LO = 318;
    private final static int GIANT_CRAB_RUNES_HI = 4961;

    /**

     Constructor for GiantCrab.
     */
    public GiantCrab() {
        super(GIANT_CRAB_NAME, GIANT_CRAB_DISPLAY_CHAR, GIANT_CRAB_HIT_POINTS);
        this.addCapability(Status.NON_HOSTILE_TO_CRAB);
        this.addCapability(Abilities.SLAM);
    }

    /**

     Generates a random number of runes for the GiantCrab and adds them to its inventory.
     */
    public void generateRunes(){
        this.addItemToInventory(new Runes(RandomNumberGenerator.getRandomInt(GIANT_CRAB_RUNES_LO, GIANT_CRAB_RUNES_HI)));
    }

    /**

     Returns an IntrinsicWeapon object representing the GiantCrab's attack.
     @return The GiantCrab's IntrinsicWeapon object.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(GIANT_CRAB_DAMAGE, GIANT_CRAB_ATTACK_VERB, GIANT_CRAB_ATTACK_ACCURACY);
    }
}


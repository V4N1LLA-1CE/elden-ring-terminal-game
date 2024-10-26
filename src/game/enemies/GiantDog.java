package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Abilities;
import game.utilities.RandomNumberGenerator;
import game.runes.Runes;
import game.enums.Status;

/**
 * GiantDog is a subclass of Enemies, representing a giant dog enemy in the game.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class GiantDog extends Enemies{
    private final static String GIANT_DOG_NAME = "Giant Dog";
    private final static char GIANT_DOG_DISPLAY_CHAR = 'G';
    private final static int GIANT_DOG_HIT_POINTS = 693;
    private final static int GIANT_DOG_DAMAGE = 314;
    private final static String GIANT_DOG_ATTACK_VERB = "slams";
    private final static int GIANT_DOG_ATTACK_ACCURACY = 90;
    private final static int GIANT_DOG_RUNES_LO = 313;
    private final static int GIANT_DOG_RUNES_HI = 1808;

    /**

     Constructor for GiantDog.
     */
    public GiantDog() {
        super(GIANT_DOG_NAME, GIANT_DOG_DISPLAY_CHAR, GIANT_DOG_HIT_POINTS);
        this.addCapability(Status.NON_HOSTILE_TO_WOLF);
        this.addCapability(Abilities.SLAM);
    }

    /**

     Generates a random number of runes and adds them to its inventory.
     */
    public void generateRunes(){
        this.addItemToInventory(new Runes(RandomNumberGenerator.getRandomInt(GIANT_DOG_RUNES_LO, GIANT_DOG_RUNES_HI)));
    }

    /**

     Returns an IntrinsicWeapon object representing the GiantDog's attack.
     @return The GiantDog's IntrinsicWeapon object.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(GIANT_DOG_DAMAGE, GIANT_DOG_ATTACK_VERB, GIANT_DOG_ATTACK_ACCURACY);
    }
}

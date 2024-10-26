package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Abilities;
import game.utilities.RandomNumberGenerator;
import game.runes.Runes;
import game.enums.Status;

/**
 * GiantCrayfish is a subclass of Enemies, representing a giant crayfish enemy in the game.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class GiantCrayfish extends Enemies{
    private final static String GIANT_CRAY_FISH_NAME = "Giant Crayfish";
    private final static char GIANT_CRAY_FISH_DISPLAY_CHAR = 'R';
    private final static int GIANT_CRAY_FISH_HIT_POINTS = 4803;
    private final static int GIANT_CRAY_FISH_DAMAGE = 527;
    private final static String GIANT_CRAY_FISH_ATTACK_VERB = "slams";
    private final static int GIANT_CRAY_FISH_ATTACK_ACCURACY = 100;
    private final static int GIANT_CRAY_FISH_RUNES_LO = 200;
    private final static int GIANT_CRAY_FISH_RUNES_HI = 2374;

    /**

     Constructor for GiantCrab.
     */
    public GiantCrayfish() {
        super(GIANT_CRAY_FISH_NAME, GIANT_CRAY_FISH_DISPLAY_CHAR, GIANT_CRAY_FISH_HIT_POINTS );
        this.addCapability(Status.NON_HOSTILE_TO_CRAB);
        this.addCapability(Abilities.SLAM);
    }

    /**

     Generates a random number of runes for the GiantCrab and adds them to its inventory.
     */
    public void generateRunes(){
        this.addItemToInventory(new Runes(RandomNumberGenerator.getRandomInt(GIANT_CRAY_FISH_RUNES_LO, GIANT_CRAY_FISH_RUNES_HI)));
    }

    /**

     Returns an IntrinsicWeapon object representing the GiantCrayfish's attack.
     @return The GiantCrayfish's IntrinsicWeapon object.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(GIANT_CRAY_FISH_DAMAGE, GIANT_CRAY_FISH_ATTACK_VERB, GIANT_CRAY_FISH_ATTACK_ACCURACY);
    }
}

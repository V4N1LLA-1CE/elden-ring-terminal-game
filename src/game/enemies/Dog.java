package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Status;
import game.runes.Runes;
import game.utilities.RandomNumberGenerator;

public class Dog extends Enemies{
    private final static String NAME = "Dog";
    private final static char DISPLAY_CHAR = 'a';
    private final static int HIT_POINTS = 104;
    private final static int DAMAGE = 101;
    private final static String ATTACK_VERB = "bites";
    private final static int ATTACK_ACCURACY = 93;
    private final static int RUNES_LO = 52;
    private final static int RUNES_HI = 1390;

    /**
     * Constructor.
     *
     */
    public Dog() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
        this.addCapability(Status.STORMVEIL_ENEMIES);
    }

    /**

     Returns an IntrinsicWeapon object representing the Dog's attack.
     @return The Dog's IntrinsicWeapon object.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DAMAGE, ATTACK_VERB, ATTACK_ACCURACY);
    }

    /**

     Generates a random number of runes for the Dog and adds them to its inventory.
     */
    @Override
    public void generateRunes() {
        this.addItemToInventory(new Runes(RandomNumberGenerator.getRandomInt(RUNES_LO, RUNES_HI)));
    }
}

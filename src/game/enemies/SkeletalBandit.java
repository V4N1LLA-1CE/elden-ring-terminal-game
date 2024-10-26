package game.enemies;

import game.enums.Abilities;
import game.utilities.RandomNumberGenerator;
import game.runes.Runes;
import game.enums.Status;
import game.weapons.Scimitar;

/**
 * SkeletalBandit is a subclass of Enemies, representing a skeletal bandit enemy in the game.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class SkeletalBandit extends Enemies{
    private final static String SKELETAL_BANDIT_NAME =  "Skeletal Bandit";
    private final static char SKELETAL_BANDIT_DISPLAY_CHAR = 'b';
    private final static int SKELETAL_BANDIT_HIT_POINTS = 102;
    private final static int SKELETAL_BANDIT_RUNES_LO = 55;
    private final static int SKELETAL_BANDIT_RUNES_HI = 1470;

    /**
     Constructor for SkeletalBandit

     Constructs a SkeletalBandit object with the given name, display character and hit points.
     Adds the capabilities of spawn pile of bones, bandit skeleton and non-hostile to skeleton.
     Adds the Scimitar weapon to inventory.
     */
    public SkeletalBandit() {
        super(SKELETAL_BANDIT_NAME, SKELETAL_BANDIT_DISPLAY_CHAR, SKELETAL_BANDIT_HIT_POINTS);
        this.addWeaponToInventory(new Scimitar());
        this.addCapability(Abilities.SPAWN_PILE_OF_BONES);
        this.addCapability(SkeletonType.BANDIT);
        this.addCapability(Status.NON_HOSTILE_TO_SKELETON);
    }

    /**

     Generates a random number of runes for the HeavySkeletalSwordsman and adds it to its inventory.
     */
    public void generateRunes(){
        this.addItemToInventory(new Runes(RandomNumberGenerator.getRandomInt(SKELETAL_BANDIT_RUNES_LO, SKELETAL_BANDIT_RUNES_HI)));
    }
}

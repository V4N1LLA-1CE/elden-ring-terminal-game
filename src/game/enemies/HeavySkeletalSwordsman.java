package game.enemies;

import game.enums.Abilities;
import game.enums.Status;
import game.runes.Runes;
import game.utilities.RandomNumberGenerator;
import game.weapons.Grossmesser;

/**
 * HeavySkeletalSwordsman is a subclass of Enemies, representing a heavy skeleton enemy in the game.
 *
 * Created by:
 * @author Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class HeavySkeletalSwordsman extends Enemies {
    private final static String HEAVY_SKELETAL_SWORDSMAN_NAME = "Heavy Skeletal Swordsman";
    private final static char HEAVY_SKELETAL_SWORDSMAN_DISPLAY_CHAR = 'q';
    private final static int HEAVY_SKELETAL_SWORDSMAN_HIT_POINTS = 153;
    private final static int HEAVY_SKELETAL_SWORDSMAN_RUNES_LO = 35;
    private final static int HEAVY_SKELETAL_SWORDSMAN_RUNES_HI = 892;

    /**
    Constructor for HeavySkeletalSwordsman

     Constructs a HeavySkeletalSwordsman object with the given name, display character and hit points.
     Adds the capabilities of spawn pile of bones, swordsman skeleton and non-hostile to skeleton.
     Adds the Grossmesser weapon to inventory.
     */
    public HeavySkeletalSwordsman() {
        super(HEAVY_SKELETAL_SWORDSMAN_NAME, HEAVY_SKELETAL_SWORDSMAN_DISPLAY_CHAR, HEAVY_SKELETAL_SWORDSMAN_HIT_POINTS);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(Abilities.SPAWN_PILE_OF_BONES);
        this.addCapability(SkeletonType.SWORDSMAN);
        this.addCapability(Status.NON_HOSTILE_TO_SKELETON);
    }

    /**

     Generates a random number of runes for the HeavySkeletalSwordsman and adds it to its inventory.
     */
    public void generateRunes(){
        this.addItemToInventory(new Runes(RandomNumberGenerator.getRandomInt(HEAVY_SKELETAL_SWORDSMAN_RUNES_LO, HEAVY_SKELETAL_SWORDSMAN_RUNES_HI)));
    }
}

package game.enemies;


import game.enums.Status;
import game.runes.Runes;
import game.utilities.RandomNumberGenerator;
import game.weapons.HeavyCrossbow;

public class GodrickSoldier extends Enemies {
    private final static String NAME = "Godrick Soldier";
    private final static char DISPLAY_CHAR = 'p';
    private final static int HIT_POINTS = 198;
    private final static int RUNES_LO = 38;
    private final static int RUNES_HI = 70;

    /**
     * Constructor.
     *
     */
    public GodrickSoldier() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
        this.addWeaponToInventory(new HeavyCrossbow());
        this.addCapability(Status.STORMVEIL_ENEMIES);
    }

    /**

     Generates a random number of runes for the Godrick Soldier and adds them to its inventory.
     */
    @Override
    public void generateRunes() {
        this.addItemToInventory(new Runes(RandomNumberGenerator.getRandomInt(RUNES_LO, RUNES_HI)));
    }
}

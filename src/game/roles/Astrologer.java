package game.roles;

import game.enums.Role;
import game.weapons.AstrologerStaff;

/**
 *  A role where the player can choose at the start of the game with a unique weapon and starting hit points.
 *  Astrologer get Astrologer's Staff as starting weapon.
 *
 * Created by:
 * @author Austin Sofaer
 */
public class Astrologer extends RoleAbstract {
    private final static Role ROLE = Role.ASTROLOGER;
    private final static int HITPOINTS = 396;

    public Astrologer() {
        super(ROLE, HITPOINTS, new AstrologerStaff());
    }
}

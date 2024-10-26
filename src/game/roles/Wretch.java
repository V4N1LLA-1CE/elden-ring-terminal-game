package game.roles;

import game.enums.Role;
import game.weapons.Club;

/**
 *  A role where the player can choose at the start of the game with a unique weapon and starting hit points.
 *  Wretch get Club as starting weapon.
 *
 * Created by:
 * @author Kachun Lee
 */
public class Wretch extends RoleAbstract{
    private final static Role ROLE = Role.WRETCH;
    private final static int HITPOINTS = 414;

    public Wretch() {
        super(ROLE, HITPOINTS, new Club());
    }
}

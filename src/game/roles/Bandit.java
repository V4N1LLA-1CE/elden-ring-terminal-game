package game.roles;

import game.enums.Role;
import game.weapons.GreatKnife;

/**
 *  A role where the player can choose at the start of the game with a unique weapon and starting hit points.
 *  Bandit get GreatKnife as starting weapon.
 *
 * Created by:
 * @author Kachun Lee
 */
public class Bandit extends RoleAbstract {
    private final static Role ROLE = Role.BANDIT;
    private final static int HITPOINTS = 414;

    public Bandit() {
        super(ROLE, HITPOINTS, new GreatKnife());
    }
}

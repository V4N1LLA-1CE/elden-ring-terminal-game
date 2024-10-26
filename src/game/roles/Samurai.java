package game.roles;

import game.enums.Role;
import game.weapons.Uchigatana;

/**
 *  A role where the player can choose at the start of the game with a unique weapon and starting hit points.
 *  Samurai get Uchigatana as starting weapon.
 *
 * Created by:
 * @author Kachun Lee
 */
public class Samurai extends RoleAbstract {
    private final static Role ROLE = Role.SAMURAI;
    private final static int HITPOINTS = 454;

    public Samurai() {
        super(ROLE, HITPOINTS, new Uchigatana());
    }
}

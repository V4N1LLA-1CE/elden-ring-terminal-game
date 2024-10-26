package game.roles;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Role;

/**
 * RoleAbstract is an abstract class representing a role in the game.
 * It contains information such as the role type, hit points, and weapon.
 *
 * Created by:
 * @author Austin Sofaer
 */
public abstract class RoleAbstract {
    private Role role;
    private int hitPoints;
    private WeaponItem weapon;

    /**
     * Constructor for RoleAbstract.
     * Creates a RoleAbstract object with the specified role, hit points, and weapon.
     *
     * @param role      the role type of the character
     * @param hitPoints the hit points of the character
     * @param weapon    the weapon carried by the character
     */
    public RoleAbstract(Role role, int hitPoints, WeaponItem weapon){
        this.role = role;
        this.hitPoints = hitPoints;
        this.weapon = weapon;
    }

    /**
     * Retrieves the role type of the character.
     * @return the role type of the character
     */
    public Role getRole(){
        return this.role;
    }

    /**
     * Retrieves the hit points of the character.
     * @return the hit points of the character
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Retrieves the weapon carried by the character.
     * @return the weapon carried by the character
     */
    public WeaponItem getWeapon() {
        return weapon;
    }
}

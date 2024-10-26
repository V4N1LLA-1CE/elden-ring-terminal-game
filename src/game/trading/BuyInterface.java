package game.trading;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A Buying interface
 *
 * Created by:
 * @author Austin Sofaer
 */
public interface BuyInterface {
    int getBuyPrice();
    void setBuyPrice(int newPrice);
    WeaponItem buy();
}

package game.trading;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.actions.TradeAction;
import game.weapons.*;

/**
 * MerchantKale is a subclass of the Trader class representing a non-playable merchant character in the game.
 * MerchantKale has the ability to trade weapons with player.
 *
 * Created by:
 * @author Albert Liesman
 * modified by Austin Sofaer
 */
public class MerchantKale extends Trader {
    private final static String MERCHANT_NAME = "Merchant Kale";
    private final static char MERCHANT_CHAR = 'K';
    private final static int MERCHANT_HIT_POINTS = 10000;

    /**
        Constructor
     */
    public MerchantKale() {
        super(MERCHANT_NAME, MERCHANT_CHAR, MERCHANT_HIT_POINTS);
        this.addCapability(Status.TRADE);
        this.addWeaponToInventory(new Club());
        this.addWeaponToInventory(new GreatKnife());
        this.addWeaponToInventory(new Uchigatana());
        this.addWeaponToInventory(new Scimitar());
        this.addWeaponToInventory(new HeavyCrossbow());
        this.addWeaponToInventory(new AstrologerStaff());
    }
}

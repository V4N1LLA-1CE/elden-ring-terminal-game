package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.trading.BuyInterface;
import game.trading.SellInterface;
import game.enums.Status;

/**
 * Wretch starting weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * It has buy and sell capabilities with specific prices for each.
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Albert Liesman, Austin Sofaer, Kachun Lee
 */
public class Club extends WeaponItem implements BuyInterface, SellInterface {
    private final static String NAME = "Club";
    private final static char DISPLAY_CHAR = '!';
    private final static int DAMAGE = 103;
    private final static String VERB = "bonks";
    private final static int HIT_RATE = 80;
    private int sellPrice;
    private int buyPrice;

    /**
     * Constructor
     */
    public Club() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE);
        setBuyPrice(600);
        setSellPrice(100);
        this.addCapability(Status.SELLABLE);
        this.addCapability(Status.BUYABLE);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    @Override
    public int getSellPrice(){
        return this.sellPrice;
    }
    @Override
    public int getBuyPrice(){
        return this.buyPrice;
    }

    @Override
    public void setSellPrice(int newPrice){
        this.sellPrice = newPrice;
    }
    @Override
    public void setBuyPrice(int newPrice){
        this.buyPrice = newPrice;
    }
    @Override
    public WeaponItem buy(){
        return new Club();
    }
}

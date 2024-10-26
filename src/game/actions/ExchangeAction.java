package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.item.ItemStatus;
import game.runes.RuneManager;
import game.trading.BuyInterface;
import game.trading.SellInterface;

import java.util.List;
import java.util.Scanner;

/**
 * ExchangeAction is an action performed by an actor to exchange weapons with Finger Reader Enia.
 * It allows the actor to buy weapons using the "Remembrance of the Grafted Essence" item or sell weapons for a price.
 * It extends the Action class.
 *
 * Created by:
 * @Author Austin Sofaer
 */
public class ExchangeAction extends Action {
    private List<WeaponItem> weaponInventory;

    /**
     * Constructor for the ExchangeAction class.
     *
     * @param weaponInventory the list of weapons available for exchange
     */
    public ExchangeAction(List<WeaponItem> weaponInventory){
        this.weaponInventory = weaponInventory;
    }

    /**
     * Performs the exchange of a weapon with Finger Reader Enia using the "Remembrance of the Grafted Essence" item.
     *
     * @param actor  the actor performing the exchange
     * @param weapon the weapon to be exchanged
     * @return a string indicating the result of the exchange
     */
    public String exchangeWeapon(Actor actor, WeaponItem weapon){
        WeaponItem thisWeapon;
        Item graftedEssence = null;
        int remembranceOfTheGraftedEssenceAmount = 0;
        thisWeapon = ((BuyInterface)weapon).buy();

        for (Item item : actor.getItemInventory())
            if (item.hasCapability(ItemStatus.REMEMBRANCE_OF_THE_GRAFTED)){
                graftedEssence = item;
                remembranceOfTheGraftedEssenceAmount ++;
            }

        if (remembranceOfTheGraftedEssenceAmount >= 1){
            actor.addWeaponToInventory(thisWeapon);
            actor.removeItemFromInventory(graftedEssence);
            return actor + " has exchanged a Remembrance of the Grafted Essence for " + thisWeapon;
        }
        return actor + " does not have Remembrance of the Grafted Essence to exchange for " + thisWeapon + " " + actor + " has not bought anything.";
    }

    /**
     * Sells a weapon to Finger Reader Enia and receives a price in return.
     *
     * @param actor       the actor selling the weapon
     * @param weaponIndex the index of the weapon in the actor's weapon inventory
     * @return a string indicating the result of the sale
     */
    public String sellWeapon(Actor actor, int weaponIndex){
        int weaponPrice;
        WeaponItem thisWeapon;
        thisWeapon = actor.getWeaponInventory().get(weaponIndex);
        weaponPrice = ((SellInterface)thisWeapon).getSellPrice();
        actor.removeWeaponFromInventory(thisWeapon);
        RuneManager.addRunes(weaponPrice);
        return actor + " has sold " + thisWeapon + " for $" + weaponPrice;
    }

    /**
     * Executes the ExchangeAction by presenting the exchange menu to the actor and performing the chosen action.
     *
     * @param actor the actor performing the action
     * @param map   the game map
     * @return a string indicating the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int buyOptions = 0;
        int buyOptionStart = 0;
        int sellOptions = 0;
        int sellOptionStart;
        int playerChoice = 0;
        int graftedEssence = 0;
        WeaponItem weaponToGet;
        String optionsMenu = "";
        String result = "";

        // exchange for boss weapon menu
        for (WeaponItem weapon : weaponInventory) {
            if (weapon.hasCapability(Status.TRADABLE)){
                optionsMenu += "[" + (buyOptions+1) + "] EXCHANGE 'Remembrance of the Grafted Essence' FOR " + weapon + "\n";
                buyOptions++;
            }
        }
        sellOptionStart = buyOptions;
        sellOptions = buyOptions;
        for (WeaponItem weapon : actor.getWeaponInventory()){
            if (weapon.hasCapability(Status.SELLABLE)){
                optionsMenu += "[" + (sellOptions+1) + "] SELL " + weapon + " FOR ($" + ((SellInterface)weapon).getSellPrice() + ")\n";
                sellOptions++;
            }
        }
        optionsMenu += "[0] EXIT TRADING MENU";
        for (Item item : actor.getItemInventory()){
            if (item.hasCapability(ItemStatus.REMEMBRANCE_OF_THE_GRAFTED)){
                graftedEssence++;
            }
        }
        optionsMenu += "\nYOU HAVE [ " + graftedEssence + " GRAFTED ESSENCE ] CURRENTLY.\n";

        int[] buyOptionChoices = new int[sellOptionStart];
        for (int i = buyOptionStart; i < sellOptionStart; i++){
            buyOptionChoices[i] = i + 1;
        }
        int[] sellOptionChoices = new int[(sellOptions - sellOptionStart)];
        for (int i = 0; i < sellOptionChoices.length; i++){
            sellOptionChoices[i] = i + sellOptionStart + 1;
        }
        boolean valid = false;
        Scanner action = new Scanner(System.in);
        do {
            System.out.println(optionsMenu);
            System.out.println("Please choose an option: ");
            playerChoice = action.nextInt();
            if (playerChoice == 0){
                valid = true;
                result += actor + " hasn't traded anything";
                break;
            }
            for (int i = 0; i < buyOptionChoices.length; i++){
                if (playerChoice == buyOptionChoices[i]){
                    valid = true;
                    weaponToGet = weaponInventory.get(i);
                    result += exchangeWeapon(actor, weaponToGet);
                    break;
                }
            }
            for (int i = 0; i < sellOptionChoices.length; i++){
                if (playerChoice == sellOptionChoices[i]){
                    valid = true;
                    result += sellWeapon(actor, i);
                }
            }
        } while (valid != true);
        // TradeAction happen here
        return result;
    }

    /**
     * Returns the menu description of the ExchangeAction.
     *
     * @param actor the actor performing the action
     * @return the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " exchanges with Finger Reader Enia";
    }
}

package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.runes.RuneManager;
import game.trading.BuyInterface;
import game.trading.SellInterface;


import java.util.List;
import java.util.Scanner;

/**
 * Represents a TradeAction that allows the Actor to buy or sell weapons with Merchant Kale.
 * The TradeAction displays a menu of buyable and sellable weapons, along with their prices.
 * The Actor can then choose to buy or sell a weapon, or exit the menu.
 *
 * Created by:
 * @author Austin Sofaer
 *
 */
public class TradeAction extends Action {
    private List<WeaponItem> weaponInventory;

    /**

     Constructor for TradeAction that takes in a List of WeaponItems to use as inventory for trading.
     @param weaponInventory List of WeaponItems available for trading.
     */
    public TradeAction(List<WeaponItem> weaponInventory){
        this.weaponInventory = weaponInventory;
    }

    /**

     Buys a weapon item for an actor, if they have enough runes to afford it.
     @param actor the actor buying the weapon item
     @param weapon the weapon item being bought
     @return a string representing the result of the purchase, including the actor's name, the purchased weapon's name,
     and the price of the weapon. If the actor cannot afford the weapon, returns a string indicating thatthey cannot
     afford the weapon and have not bought anything.
     */
    public String buyWeapon(Actor actor, WeaponItem weapon){
        int runes = RuneManager.getRunes();
        int weaponPrice;
        WeaponItem thisWeapon;
        thisWeapon = ((BuyInterface)weapon).buy();
        weaponPrice = ((BuyInterface)weapon).getBuyPrice();

        if (runes >= weaponPrice){
            actor.addWeaponToInventory(thisWeapon);
            RuneManager.minusRunes(weaponPrice);
            return actor + " has bought " + thisWeapon + " for $" + weaponPrice;
        }
        return actor + " does not have enough runes to buy " + thisWeapon + " " + actor + " has not bought anything.";
    }

    /**

     Sells a weapon from the actor's inventory and adds the selling price to the rune manager.
     @param actor the actor who sells the weapon
     @param weaponIndex the index of the weapon to be sold in the actor's inventory
     @return a string indicating the success or failure of the selling operation
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

     Executes the TradeAction by displaying a menu of buyable and sellable weapons, along with their prices.
     The Actor can then choose to buy or sell a weapon, or exit the menu.

     @param actor The Actor that is trading.
     @param map The GameMap where the trading takes place.
     @return A string indicating the outcome of the trade.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int buyOptions = 0;
        int buyOptionStart = 0;
        int sellOptions = 0;
        int sellOptionStart;
        int playerChoice = 0;
        WeaponItem weaponToBuy;
        String optionsMenu = "";
        String result = "";

        // BUY MENU
        for (WeaponItem weapon : weaponInventory) {
            if (weapon.hasCapability(Status.BUYABLE)){
                optionsMenu += "[" + (buyOptions+1) + "] BUY " + weapon + " FOR ($" + ((BuyInterface)weapon).getBuyPrice() + ")\n";
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
                    weaponToBuy = weaponInventory.get(i);
                    result += buyWeapon(actor, weaponToBuy);
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

     Returns a String describing the menu option for a given Actor, which is to trade with Merchant Kale.
     @param actor The Actor who will perform the menu option.
     @return A String describing the menu option.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades with Merchant Kale";
    }
}

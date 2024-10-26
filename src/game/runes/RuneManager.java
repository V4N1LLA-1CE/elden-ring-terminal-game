package game.runes;

import game.roles.Player;

/**
 * The RuneManager class is responsible for managing the number of runes that the player possesses.
 * It provides methods for adding and subtracting runes from the player's wallet, as well as getting the current
 * number of runes the player has.
 *
 * Created by:
 * @author Kachun Lee
 * modified by Austin Sofaer
 */
public class RuneManager {
    private static Player player;

    /**

     Sets the player object for the RuneManager to use.
     @param player the player object to set
     */
    public static void setPlayer(Player player){
        RuneManager.player = player;
    }

    /**

     Adds the specified number of runes to the player's wallet.
     @param value the number of runes to add
     */
    public static void addRunes(int value){
        int newRuneValue = player.getRuneWallet() + value;
        player.setRuneWallet(newRuneValue);
    }

    /**

     Subtracts the specified number of runes from the player's wallet.
     @param value the number of runes to subtract
     */
    public static void minusRunes(int value) {
        int newRuneValue = player.getRuneWallet() - value;
        player.setRuneWallet(newRuneValue);
    }

    /**

     Gets the current number of runes in the player's wallet.
     @return the number of runes in the player's wallet
     */
    public static int getRunes(){
        return player.getRuneWallet();
    }
}

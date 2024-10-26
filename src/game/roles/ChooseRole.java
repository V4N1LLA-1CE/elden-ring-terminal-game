package game.roles;

import java.util.Scanner;

/**
 * ChooseRole class is a singleton class that handles user's role selection
 *
 * Created by:
 * @author Kachun Lee
 */
public class ChooseRole {
    private static ChooseRole chooseRole = null;

    /**

     Gets the instance of ChooseRole, creating a new one if it does not already exist.
     @return ChooseRole instance
     */
    public static ChooseRole getInstance(){
        if (chooseRole == null){
            chooseRole = new ChooseRole();
        }
        return chooseRole;
    }

    /**

     Prints the role options and gets the user's selection via input.

     @return the integer value of the user's selected role
     */
    public int menuItem(){
        Scanner roleInput = new Scanner(System.in);

        System.out.println("Select your role: \n[1] SAMURAI\n[2] BANDIT\n[3] WRETCH\n[4] ASTROLOGER\nSelect one:");
        int choice;
        try{
            choice = roleInput.nextInt();
        }
        catch (Exception e){
            System.out.println("Please enter a number from 1 - 4");
            choice = menuItem();
        }
        return choice;
    }
}

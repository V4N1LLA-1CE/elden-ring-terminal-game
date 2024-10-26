package game.reset;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Albert Liesman
 *
 */
public class ResetManager {
    private List<Resettable> resettablesList;
    private static ResetManager instance;

    /**

     Private constructor for the singleton instance of the ResetManager class.
     It initializes the list of Resettable instances.
     */
    private ResetManager() {
        this.resettablesList = new ArrayList<>();
    }

    /**

     Returns the singleton instance of the ResetManager class.
     If the instance does not exist, it creates a new one.
     @return The ResetManager instance
     */
    public static ResetManager getInstance() {
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }

    /**

     Resets all registered Resettable instances.
     @param resting A boolean value indicating if the reset is due to resting.
     */
    public void run(Boolean resting) {
        //reset all instances that in the resettableList
        for (Resettable instance : resettablesList) {
            instance.reset(resting);
        }
    }

    /**

     Adds a Resettable instance to the list of registered instances.
     @param resettable The Resettable instance to be registered.
     */
    public void registerResettable(Resettable resettable) {
        resettablesList.add(resettable);
    }

    /**

     Removes a Resettable instance from the list of registered instances.
     @param resettable The Resettable instance to be removed.
     */
    public void removeResettable(Resettable resettable) {
        resettablesList.remove(resettable);
    }
}

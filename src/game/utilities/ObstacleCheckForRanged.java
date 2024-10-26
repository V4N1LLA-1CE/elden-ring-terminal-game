package game.utilities;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;

/**
 * A utility class for checking obstacles between two locations for ranged attacks.
 *
 * Created by:
 * @author Ka Chun Lee
 */
public class ObstacleCheckForRanged {
    /**
     * Checks if there are any obstacles between two locations for ranged attacks.
     *
     * @param map   the game map
     * @param here  the starting location
     * @param there the target location
     * @return true if there are obstacles, false otherwise
     */
    public static boolean ObstacleCheck(GameMap map, Location here, Location there) {
        NumberRange xs, ys;

        xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
        ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

        for (int x : xs) {
            for (int y : ys) {
                if(map.at(x, y).getGround().blocksThrownObjects()){
                    return true;
                }
            }
        }
        return false;
    }
}

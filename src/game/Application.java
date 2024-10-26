package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.enemies.GodrickSoldier;
import game.environments.*;
import game.item.BloodPotion;
import game.item.GoldenRunes;
import game.reset.ResetManager;
import game.roles.*;
import game.runes.RuneManager;
import game.summoners.Ally;
import game.summoners.Invader;
import game.trading.FingerReaderEnia;
import game.trading.MerchantKale;
import game.utilities.FancyMessage;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Albert Liesman, Austin Sofaer, Kachun Lee
 *
 */
public class Application {

	public static void main(String[] args) {
		// Setting the map
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyards(), new GustOfWind(), new PuddleOfWater(),
				new SiteOfLostGrace(), new Cliff(), new Barrack(), new Cage(), new SummonSign(), new PoisonPond());

		List<String> map = Arrays.asList(
				"..nnnn.................................................~~~~~~~~~~~~~~~~~~~~",
				"......................#####....######...++..............~~~~~~~~~~~~~~~~~~~",
				"..nnnn................#..___....____#....++++............~~~~~~~~~~~~~~~~~~",
				"......................_.....U.....__#......++.............~~~~~~~~~~~~~~~~~",
				"......++..............______........#........+.............~~~~~~~~~~~~~~~~",
				".....+................#............_#......................................",
				"......++..............###_________###......................................",
				".......+................=...............................................+..",
				"......................................................................++...",
				"~~~~~~~~~~~.......................###___###.........................++.....",
				"~~~~~~~~~~~~......................________#.....nnnn.................+++...",
				"~~~~~~~~~~~~~.....................#___U____...........................++...",
				"~~~~~~~~~~~~~.....................#_______#.....nnnn................++.....",
				"~~~~~~~~~~~~......................###___###................................",
				"~~~~~~~~~~~.........................#___#..................................",
				".................................=.........................................",
				"...........................................................................",
				"..................................................................=........",
				"..####__##....................+.....................&&&.......######..##...",
				"..#..U..__...................+++....................&&&.......#....____....",
				"..#___..............&&&.......+.....................&&&.........__U....#...",
				"..####__###.........&&&........++............................._.....__.#...",
				"....................&&&..........+............................###..__###...",
				"...........................................................................");
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		List<String> stormVeilMap = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");
		GameMap stormVeilCastle = new GameMap(groundFactory, stormVeilMap);
		world.addGameMap(stormVeilCastle);

		List<String> roundTableMap = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________U_______#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");
		GameMap roundTable = new GameMap(groundFactory, roundTableMap);
		world.addGameMap(roundTable);

		List<String> bossRoomMap = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");
		GameMap bossRoom = new GameMap(groundFactory, bossRoomMap);
		world.addGameMap(bossRoom);

		List<String> lakeOfRotMap = Arrays.asList(
				"_++++++++++++++++++++++++",
				"..........,,,,,,,,,,,,,,,",
				"........,,,,,,,,,,,,,,,,,",
				".......,,,,,,,,,,,,,,,,,,",
				"......,,,,,,,.,,,,,,+,,,,",
				".....,,,,,,,,,..,,,+,,,,,",
				"...,,,,,,,,,,...,,,,+,,,,",
				".,,,,,,,,,,,,,..,,,,,,,,,",
				"+++++++++++++++++++++++++");
		GameMap lakeOfRot = new GameMap(groundFactory, lakeOfRotMap);
		world.addGameMap(lakeOfRot);

		// Setting the Golden Fog Door position
		gameMap.at(30, 2).setGround(new GoldenFogDoor(31, 22, stormVeilCastle));
		gameMap.at(7, 20).setGround(new GoldenFogDoor(9, 10, roundTable));
		gameMap.at(63, 21).setGround(new GoldenFogDoor(2, 2, lakeOfRot));

		stormVeilCastle.at(31, 22).setGround(new GoldenFogDoor(30, 2, gameMap));
		stormVeilCastle.at(8, 1).setGround(new GoldenFogDoor(12, 6, bossRoom));

		roundTable.at(9, 10).setGround(new GoldenFogDoor(7, 20, gameMap));
		lakeOfRot.at(2,2).setGround(new GoldenFogDoor(63,21, gameMap));


		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")){
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		// Map split to differentiate the EAST and WEST side of the map
		int width = map.get(0).length();
		int height = map.size();
		int widthMedian = Math.floorDiv(width, 2);

		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				if (x < widthMedian){
					gameMap.at(x,y).getGround().addCapability(MapSide.WEST);
				} else {
					gameMap.at(x,y).getGround().addCapability(MapSide.EAST);
				}
			}
		}

		// Choosing a playable role when the game start
		int choice;
		RoleAbstract chosenRole = null;

		do {
			choice = ChooseRole.getInstance().menuItem();

			switch (choice) {
				case 1:
					chosenRole = new Samurai();
					System.out.println("You have chosen " + chosenRole.getRole());
					break;

				case 2:
					chosenRole = new Bandit();
					System.out.println("You have chosen " + chosenRole.getRole());
					break;

				case 3:
					chosenRole = new Wretch();
					System.out.println("You have chosen " + chosenRole.getRole());
					break;

				case 4:
					chosenRole = new Astrologer();
					System.out.println("You have chosen " + chosenRole.getRole());
					break;

				default:
					System.out.println("Please choose a valid role.\n");
			}
		}
		while (choice < 1 || choice > 4);

		// spawn Trader
		gameMap.at(41, 12).addActor(new MerchantKale());
		gameMap.at(67, 21).addActor(new FingerReaderEnia());

		// create player with role chosen
		Player player = new Player("Tarnished", '@', 500, chosenRole);
		RuneManager.setPlayer(player);
		world.addPlayer(player, gameMap.at(36, 10)); //Original position  (36,10)
		player.setRespawnLoc(gameMap.at(38,11));
		ResetManager.getInstance().registerResettable(player);

		// Run the program
		world.run();
	}
}

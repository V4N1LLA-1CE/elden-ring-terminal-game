package game.roles;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.*;
import game.enums.Abilities;
import game.enums.Role;
import game.enums.Status;
import game.item.*;
import game.reset.Resettable;
import game.runes.Runes;
import game.uniqueattacks.SlamAttackAOE;
import game.utilities.ObstacleCheckForRanged;
import game.weapons.*;

import java.util.List;


/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Albert Liesman, Austin Sofaer, Kachun Lee
 *
 */
public class Player extends Actor implements Resettable {
	private final Menu menu = new Menu();
	private final static int MAX_FLASK_OF_CRIMSON_TEARS = 2;
	private int runeWallet;
	private RoleAbstract thisRole;
	private Role role;
	private Location restingLocation;
	private Runes droppingRunes;
	public static int deathCount;
	private Location prevDeathLocation;
	public static Location prevLocation = null;

	/**

	 Constructor

	 @param name The name of the player.
	 @param displayChar The character that represents the player in the game world.
	 @param hitPoints The hit points of the player.
	 @param role The role of the player (e.g. Samurai, Bandit, etc.).
	 */
	public Player(String name, char displayChar, int hitPoints, RoleAbstract role) {
		super(name, displayChar, hitPoints);
		this.thisRole = role;
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.RESTING);
		this.addCapability(Status.RESPAWNABLE);
		this.addCapability(Status.FRIENDLY);
		this.addCapability(Status.PLAYER);
		// Adding Flask of Crimson Tears
		this.addItemToInventory(new FlaskOfCrimsonTears(MAX_FLASK_OF_CRIMSON_TEARS));
		// starting role setup
		this.role = thisRole.getRole();
		this.addWeaponToInventory(thisRole.getWeapon());
		this.resetMaxHp(thisRole.getHitPoints());

		this.addWeaponToInventory(new HeavyCrossbow());
	}

	/**

	 Sets the amount of runes in the player's wallet.
	 @param runeWallet the amount of runes to set.
	 */
	public void setRuneWallet(int runeWallet) {
		this.runeWallet = runeWallet;
	}

	/**

	 Returns the amount of runes in the player's wallet.
	 @return the amount of runes in the player's wallet.
	 */
	public int getRuneWallet() {
		return runeWallet;
	}

	/**

	 Sets the location where the player will respawn.
	 @param location the location where the player will respawn.
	 */
	public void setRespawnLoc(Location location){
		this.restingLocation = location;
	}

	/**

	 Method that is called when it is the Player's turn to act.

	 @param actions The list of possible actions the player can take.
	 @param lastAction The last action taken by the player.
	 @param map The game map.
	 @param display The game display.
	 @return The Action the Player will take.
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		int graftedEssence = 0;
		for (Item item : this.getItemInventory()){
			if (item.hasCapability(ItemStatus.REMEMBRANCE_OF_THE_GRAFTED)){
				graftedEssence++;
			}
		}
		// Displaying player's Hp
		display.println("Tarnished " + super.printHp() + ", Class: " + role + ", Rune: $" + runeWallet + ", Grafted Essence: " + graftedEssence);

		// Variables for dropping runes when player dies
		String displayDropRunesAction = "";
		Runes thisRunes;
		List<Item> itemsInPrevLoc;

		Location currentLocation = map.locationOf(this);

		// Handle player death
		if (!this.isConscious()){
			// Drop runes and respawn
			droppingRunes = new Runes(runeWallet);
			DropRunesAction dropRunesAction = new DropRunesAction(droppingRunes);
			displayDropRunesAction += dropRunesAction.execute(this, map);
			deathCount++;

			// Save previous location every second death
			if (deathCount % 2 != 0){
				prevDeathLocation = prevLocation;
			}
			// Remove runes from previous location every other death
			if (deathCount % 2 == 0){
				itemsInPrevLoc = map.at(prevDeathLocation.x(), prevDeathLocation.y()).getItems();
				for (Item item : itemsInPrevLoc)
					if(item != null && item.hasCapability(Status.RUNE)){
						map.at(prevDeathLocation.x(), prevDeathLocation.y()).removeItem(item);
						break;
				}
			}
			// Move player to resting location and respawn
			map.moveActor(this, restingLocation);
			System.out.println(displayDropRunesAction);
			return new RespawnAction();
		}

		// location of the player
		Location here = map.locationOf(this);

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Getting skill from weapon
		for (Weapon weapon : this.getWeaponInventory()){
			actions.add(weapon.getSkill(this));
		}

		// Set the restingLocation
		if (here.getGround().hasCapability(Status.SITE_LOST_GRACE)){
			this.restingLocation = here;
		}

		// Pick up runes of available
		for (Item item : map.locationOf(this).getItems())	{
			if(item.hasCapability(Status.RUNE)){
				thisRunes = (Runes) item;
				actions.add(new PickUpRunesAction(thisRunes));
			}
		}

		// Consumable item in the player's inventory
		for (Item item : this.getItemInventory()) {
			if (item.hasCapability(ItemStatus.CONSUMABLE)) {
				ConsumeInterface consumableItem = (ConsumeInterface) item;
				actions.add(new ConsumeAction(consumableItem));
			}
		}

		// Crossbow attack range check
		for (WeaponItem weaponItem : this.getWeaponInventory()){
			if (weaponItem.hasCapability(WeaponSkill.CROSSBOW)){
				int currLocForRangedCheckX = map.locationOf(this).x();
				int currLocForRangedCheckY = map.locationOf(this).y();

				boolean obstacleFlag = false;

				for (int i = Math.max(currLocForRangedCheckX - 2, 0); i <= Math.min(currLocForRangedCheckX + 2, map.getXRange().max()); i++) {
					for (int j = Math.max(currLocForRangedCheckY - 2, 0); j <= Math.min(currLocForRangedCheckY + 2, map.getYRange().max()); j++) {
						if (i == currLocForRangedCheckX && j == currLocForRangedCheckY) {
							continue;
						}
						if (map.at(i, j).containsAnActor() && !(map.at(i, j).getActor().hasCapability(Status.FRIENDLY))){
							obstacleFlag = ObstacleCheckForRanged.ObstacleCheck(map, map.locationOf(this), map.at(i, j));
							if (!obstacleFlag){
								actions.add(new RangedAttackAction(map.at(i, j).getActor(), weaponItem));
							}
						}
					}
				}
			}
		}

		// Staff attack range check
		for (WeaponItem weaponItem : this.getWeaponInventory()){
			if (weaponItem.hasCapability(WeaponSkill.STAFF)){
				int currLocForRangedCheckX = map.locationOf(this).x();
				int currLocForRangedCheckY = map.locationOf(this).y();

				for (int i = Math.max(currLocForRangedCheckX - 3, 0); i <= Math.min(currLocForRangedCheckX + 3, map.getXRange().max()); i++) {
					for (int j = Math.max(currLocForRangedCheckY - 3, 0); j <= Math.min(currLocForRangedCheckY + 3, map.getYRange().max()); j++) {
						if (i == currLocForRangedCheckX && j == currLocForRangedCheckY) {
							continue;
						}
						if (map.at(i, j).containsAnActor() && !(map.at(i, j).getActor().hasCapability(Status.FRIENDLY))){
							actions.add(new RangedAttackAction(map.at(i, j).getActor(), weaponItem));
						}
					}
				}
			}
		}

		prevLocation = currentLocation;

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**

	 Returns a list of allowable actions for this actor in a given direction, based on their current state and the current game map.

	 @param otherActor the actor that the actions are being generated against
	 @param direction the direction in which the actions will take place
	 @param map the game map
	 @return a list of allowable actions for this actor in a given direction
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		IntrinsicWeapon intrinsicWeapon;

		if (this.isConscious()) {
			for (WeaponItem weapon : otherActor.getWeaponInventory()) {
				// Use weapon skill if available
				if (weapon.hasCapability(WeaponSkill.HAS_SKILL)){
					actions.add(weapon.getSkill(this, direction));
				}
				else{
					// When its crab or giant dog
					if (otherActor.hasCapability(Abilities.SLAM)) {
						intrinsicWeapon = otherActor.getIntrinsicWeapon();
						actions.add(new SlamAttackAOE(this, direction, intrinsicWeapon));
					}
				}
			}
		}
		return actions;
	}

	/**

	 Resets the player's HP to its maximum value and restores the Flask's usage to its maximum value.

	 @param resting boolean indicating if the player is resting or not.
	 */
	@Override
	public void reset(Boolean resting) {
		// Reset player's HP to max HP
		this.hitPoints = this.getMaxHp();

		// Reset the Flask into Max Usage
		for (Item item: this.getItemInventory()){
			if (item != null){
				((Resettable) item).reset(resting);
			}
		}
	}
}


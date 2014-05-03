package campuscreatures.battleMechanics;

import java.io.Serializable;
import java.util.ArrayList;

import campuscreatures.database.Creatures;

public class BattleCreature implements Serializable {
	public enum statType {
		attack, defense, speed
	}
	
	public enum creatureType {
		fire, space, normal, electric, psychic, spirit, earth
	}
	
	//Creatures will have a title, level, max health, current health
	//experience, and arrayList of attacks.
	private int id;
	private String Title;
	private String region;
	private String district;
	private creatureType type;
	private int CurrentHealth;
	private int MaxHealth;
	private int attack;
	private int defense;
	private int Level;
	private int speed;
	private int CreatureExperience;
	private ArrayList<BattleAction> BattleActions;
	private BattleAction currentBattleAction;
	private ArrayList<Integer> BattleActionsUseCount;
	private int CurrentBattleActionUseCount;
	private int upgradePoints;
	
	//TODO add all values which 'Creature' class has which 'BattleCreature' does not have.
	//TODO create function/s to convert between 'Creature' and 'BattleCreature' classes
	
	//construct a creature with all stats
	public BattleCreature(int id, String title, String region, String district, 
			String type, int attack, int defense, int level, int speed, int maxH, int currentH, int xp, ArrayList<BattleAction> actions) {
		this.id = id;
		this.region = region;
		this.district = district;
		this.type = creatureTypeFromString(type);
		this.attack = attack;
		this.defense = defense;
		Title = title;
		Level = level;
		MaxHealth = maxH;
		this.speed = speed;
		System.out.println("Got here H");
		//set CurrentHealth and make sure not to make it larger than MaxHealth
		if(currentH > maxH) {
			CurrentHealth = maxH;
		}
		else {
			CurrentHealth = currentH;
		}
		
		CreatureExperience = xp;
		BattleActions = actions;
		if(BattleActions.size() > 0) {
			currentBattleAction = BattleActions.get(0);
		}
		BattleActionsUseCount = new ArrayList();
		System.out.println(BattleActions.size());
		for(int i=0; i<BattleActions.size();i++) {
			BattleActionsUseCount.add(0);
		}
		System.out.println("Got here K");
		resetBattleActionsCount(-1); //set all counts to 0;
		System.out.println("Got here J");
		upgradePoints = 0;
	}
	
	//constructor from a 'Creatures' instance
	public BattleCreature(Creatures c) {
		//TODO the below conversion gives an empty battleActions ArrayList.
		this(c.getId(), c.getName(), c.getRegion(), c.getDistrict(), c.getType(), c.getAttack(),
				c.getDefense(), c.getLevel(), c.getSpeed(), c.getHealth(),
				c.getHealth(), 0, new ArrayList());
	}
	
	//returns the creatureType enumeration for a certain String 
	private creatureType creatureTypeFromString(String string) {
		if(string.equals("earth")){
			return creatureType.earth;
		} else if (string.equals("fire")) {
			return creatureType.fire;
		} else if (string.equals("space")) {
			return creatureType.space;
		} else if (string.equals("normal")) {
			return creatureType.normal;
		} else if (string.equals("electric")) {
			return creatureType.electric;
		} else if (string.equals("psychic")) {
			return creatureType.psychic;
		} else if (string.equals("spirit")) {
			return creatureType.spirit;
		}else {
			return creatureType.normal;
		}
	}
	
	
	//set of getter methods
	public int getId() {
		return id;
	}
	
	public String getTitle(){
		return Title;
	}
	
	public String getRegion() {
		return region;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public creatureType getType() {
		return type;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public int getLevel(){
		return Level;
	}
	
	public int getMaxHealth(){
		return MaxHealth;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getCurrentHealth(){
		return CurrentHealth;
	}
	
	public int getCreatureExperience(){
		return CreatureExperience;
	}
	
	public ArrayList<BattleAction> getBattleActions(){
		return BattleActions;
	}
	
	public BattleAction getCurrentBattleAction() {
		return currentBattleAction;
	}
	
	public ArrayList<Integer> getBattleActionsUseCount() {
		return BattleActionsUseCount;
	}
	
	public int getCurrentBattleActionUseCount() {
		return CurrentBattleActionUseCount;
	}
	
	public int getUpgradePoints() {
		return upgradePoints;
	}
	
	
	//modifier methods for certain attributes
	public void incrementLevel(){
		Level++;
	}
	
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	
	public void setAttack(int newAttack) {
		attack = newAttack;
	}
	
	public void setDefense(int newDefense) {
		defense = newDefense;
	}
	
	public void incrementAttack() {
		attack++;
	}
	
	public void incrementDefense() {
		defense++;
	}
	
	public void incrementSpeed() {
		speed++;
	}
	
	public void addUpgradePoints(int upgrade) {
		upgradePoints += upgrade;
	}
	
	public void incrementStat(statType stat) {
		if(upgradePoints > 0) {
			switch(stat) {
			case attack:
				incrementAttack();
				break;
			case defense:
				incrementDefense();
				break;
			case speed:
				incrementSpeed();
				break;
			default:
				return;
			}
			upgradePoints--;
		}		
	}
	
	public int adjustHealth(int val){
		int oldHealth = CurrentHealth;
		CurrentHealth = CurrentHealth + val;
		//make sure never to make CurrentHealth greater than max health
		if(CurrentHealth > MaxHealth) {
			CurrentHealth = MaxHealth;
		}
		//don't allow currentHealth to ever go below 0
		else if(CurrentHealth<0) {
			CurrentHealth = 0;
		}
		return CurrentHealth-oldHealth;
	}
	
	public void resetHealth(){
		CurrentHealth = MaxHealth;
	}
	
	public void increaseCreatureExperience(int val) {
		CreatureExperience += val;
	}
	
	public void addBattleAction(BattleAction action) {
		BattleActions.add(action);
	}
	
	public void chooseBattleAction(int i){
		currentBattleAction = BattleActions.get(i);
		BattleActionsUseCount.set(i, BattleActionsUseCount.get(i) +1);
		CurrentBattleActionUseCount = BattleActionsUseCount.get(i);
		resetBattleActionsCount(i);
	}
	
	private void resetBattleActionsCount(int i) {//reset all battle actions count except for the one with index i
		System.out.println("Got here L");
		for(int j = 0; j< BattleActions.size(); j++) {
			System.out.println("Got here M: " + j);
			if (i!=j) {
				System.out.println("Got here N: " + j);
				BattleActionsUseCount.set(j,0);
				System.out.println("Got here O");
			}
		}
	}
	

}

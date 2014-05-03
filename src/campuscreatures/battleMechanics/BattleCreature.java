package campuscreatures.battleMechanics;

import java.io.Serializable;
import java.util.ArrayList;

import campuscreatures.database.Creatures;

public class BattleCreature implements Serializable {
	//Creatures will have a title, level, max health, current health
	//experience, and arrayList of attacks.
	private String Title;
	private int Level;
	private int MaxHealth;
	private int speed;
	private int CurrentHealth;
	private int CreatureExperience;
	private ArrayList<BattleAction> BattleActions;
	private BattleAction currentBattleAction;
	private ArrayList<Integer> BattleActionsUseCount;
	private int CurrentBattleActionUseCount;
	
	//TODO add all values which 'Creature' class has which 'BattleCreature' does not have.
	//TODO create function/s to convert between 'Creature' and 'BattleCreature' classes
	
	//construct a creature with all stats
	public BattleCreature(String title, int level, int speed, int maxH, int currentH, int xp, ArrayList<BattleAction> actions) {
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
	}
	
	//constructor from a 'Creatures' instance
	public BattleCreature(Creatures c) {
		//TODO the below conversion gives an empty battleActions ArrayList.
		this(c.getName(), c.getLevel(), c.getSpeed(), c.getHealth(), c.getHealth(), 0, new ArrayList());
	}
	
	
	
	//set of getter methods
	public String getTitle(){
		return Title;
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
	
	
	//modifier methods for certain attributes
	public void incrementLevel(){
		Level++;
	}
	
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
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
	
	public ArrayList<Integer> getBattleActionsUseCount() {
		return BattleActionsUseCount;
	}
	
	public int getCurrentBattleActionUseCount() {
		return CurrentBattleActionUseCount;
	}
	public boolean equals(BattleCreature creature){
		if (this.getTitle() == creature.getTitle() &&
			this.getLevel() == creature.getLevel() &&
			this.getMaxHealth() == creature.getMaxHealth() &&
			this.getSpeed() == creature.getSpeed() &&
			this.getCurrentHealth() == creature.getCurrentHealth() &&
			this.getCreatureExperience() == creature.getCreatureExperience())
		{
			return true;
		} else {
			return false;
		}
	}
}

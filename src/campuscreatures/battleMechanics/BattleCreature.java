package campuscreatures.battleMechanics;

import java.util.ArrayList;

public class BattleCreature {
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
	
	
	//instantiate a creature with all stats
	public BattleCreature(String title, int level, int speed, int maxH, int currentH, int xp, ArrayList<BattleAction> actions) {
		Title = title;
		Level = level;
		MaxHealth = maxH;
		this.speed = speed;
		
		//set CurrentHealth and make sure not to make it larger than MaxHealth
		if(currentH > maxH) {
			CurrentHealth = maxH;
		}
		else {
			CurrentHealth = currentH;
		}
		
		CreatureExperience = xp;
		BattleActions = actions;
		currentBattleAction = BattleActions.get(0);
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
	}
}

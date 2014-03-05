package battleMechanics;

import java.util.ArrayList;

public class Creature {
	//Creatures will have a title, level, max health, current health
	//experience, and arrayList of attacks.
	private String Title;
	private int Level;
	private int MaxHealth;
	private int CurrentHealth;
	private int CreatureExperience;
	private ArrayList<BattleAction> BattleActions;
	
	
	//instantiate a creature with all stats
	public Creature(String title, int level, int maxH, int currentH, int xp, ArrayList<BattleAction> actions) {
		Title = title;
		Level = level;
		MaxHealth = maxH;
		
		//set CurrentHealth and make sure not to make it larger than MaxHealth
		if(currentH > maxH) {
			CurrentHealth = maxH;
		}
		else {
			CurrentHealth = currentH;
		}
		
		CreatureExperience = xp;
		BattleActions = actions;
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
	
	public int getCurrentHealth(){
		return CurrentHealth;
	}
	
	public int getCreatureExperience(){
		return CreatureExperience;
	}
	
	public ArrayList<BattleAction> getBattleActions(){
		return BattleActions;
	}
	
	
	//modifier methods for certain attributes
	public void incrementLevel(){
		Level++;
	}
	
	public void adjustHealth(int val){
		CurrentHealth = CurrentHealth + val;
		//make sure never to make CurrentHealth greater than max health
		if(CurrentHealth > MaxHealth) {
			CurrentHealth = MaxHealth;
		}
		//don't allow currentHealth to ever go below 0
		else if(CurrentHealth<0) {
			CurrentHealth = 0;
		}
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
	
	//other methods
	public void doBattleAction(Creature opp, int i) {
		BattleAction currentBattleAction = BattleActions.get(i);
		//adjust creature health and opponent health. simple implementation.
		opp.adjustHealth(-currentBattleAction.getAttackVal());
		this.adjustHealth(currentBattleAction.getRecoverVal());
	}
}

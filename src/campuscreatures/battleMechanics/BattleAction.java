package campuscreatures.battleMechanics;

import java.io.Serializable;

public class BattleAction implements Serializable {
	
	//a BattleAction will either attack the other creature
	//or recover the creature calling the action
	private String Title;
	private float AttackVal;
	private float RecoverVal;
	private int Effectiveness;
	
	public BattleAction(String title, int attack, int recover, int effect) {
		Title = title;
		AttackVal = (float)attack;
		if(attack == 0) {  //we won't allow both attack and recover in same action
			RecoverVal = (float)recover;
		}
		else {
			RecoverVal = (float)0; //so if both parameters aren't zero, we default to accept the attack value
		}
		Effectiveness = effect;
	}
	
	
	//set of getter methods
	public String getTitle(){
		return Title;
	}
	
	private float getAttackVal(){
		return AttackVal;
	}
	
	private float getRecoverVal(){
		return RecoverVal;
	}
	
	public int getEffectiveness(){
		return Effectiveness;
	}
	
	
	//set of setter methods for certain attributes
	public void setAttackVal(int attack){
		AttackVal = attack;
	}
	
	public void setRecoverVal(int recover){
		RecoverVal = recover;
	}
	
	public void setEffectiveness(int effect){
		Effectiveness = effect;
	}
	
	public float getModifiedAttackValue(int mod) {
		if(mod == 0) {
			return AttackVal;
		}
		System.out.println("mod = " + mod);
		return AttackVal/(float)mod;
	}
	
	public float getModifiedRecoverValue(int mod) {
		if(mod == 0) {
			return RecoverVal;
		}
		return RecoverVal/(float)mod;
	}
	
}

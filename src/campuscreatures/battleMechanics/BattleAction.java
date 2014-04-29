package campuscreatures.battleMechanics;

import java.io.Serializable;

public class BattleAction implements Serializable {
	
	//a BattleAction will either attack the other creature
	//or recover the creature calling the action
	private String Title;
	private int AttackVal;
	private int RecoverVal;
	private int Effectiveness;
	
	public BattleAction(String title, int attack, int recover, int effect) {
		Title = title;
		AttackVal = attack;
		if(attack == 0) {  //we won't allow both attack and recover in same action
			RecoverVal = recover;
		}
		else {
			RecoverVal = 0; //so if both parameters aren't zero, we default to accept the attack value
		}
		Effectiveness = effect;
	}
	
	
	//set of getter methods
	public String getTitle(){
		return Title;
	}
	
	private int getAttackVal(){
		return AttackVal;
	}
	
	private int getRecoverVal(){
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
	
	public int getModifiedAttackValue(int mod) {
		if(mod == 0) {
			return AttackVal;
		}
		System.out.println("mod = " + mod);
		return AttackVal/(mod);
	}
	
	public int getModifiedRecoverValue(int mod) {
		if(mod == 0) {
			return RecoverVal;
		}
		return RecoverVal/mod;
	}
	
}

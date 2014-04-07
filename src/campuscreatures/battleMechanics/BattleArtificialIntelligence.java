package campuscreatures.battleMechanics;

import java.io.Serializable;
import java.util.Random;

/*
 * Simple AI in order to have single-player mode, which is going to end up being an
 * important component of this game.
 */
public class BattleArtificialIntelligence implements Serializable {
	
	
	private int difficulty; //difficulty describes the level of intelligence
	private Battle AIBattle;
	private BattleCreature AICreature; //assumed to be the opponent creature
	
	public BattleArtificialIntelligence(int diff, Battle battle) {
		this.difficulty = diff;
		this.AIBattle = battle;
		this.AICreature = AIBattle.getOpponentBattleCreature();
	}
	
	/*
	 * choose strongest attack until Health is under 3 points
	 */
	public void calculateNextMove() {
		System.out.println("Got to calcMove A");
		int chooseIndex;
		if(AICreature.getCurrentHealth() < 3) {
			chooseIndex = findBestHeal();
			System.out.println("Got to calcMove B");
		}
		else {
			System.out.println("Got to calcMove B.1");
			chooseIndex = findStrongestMove();
		}
		System.out.println("Got to calcMove B.2");
		AICreature.chooseBattleAction(chooseIndex);
		System.out.println("Got to calcMove C");
	}
	
	private int findStrongestMove() {
		int sizeOfBattleActions = AICreature.getBattleActions().size();
		int strongestIndex = 0;
		int strongestModValue = AICreature.getBattleActionsUseCount().get(0);
		System.out.println("Got to findStrongMove A");
		for(int i=1; i<sizeOfBattleActions; i++) {
			System.out.println("Got to findStrongMove B." + i);
			int attemptedModValue = AICreature.getBattleActionsUseCount().get(i);
			System.out.println("Got to findStrongMove B.A." + i);
			int AttemptedValue = AICreature.getBattleActions().get(i).getModifiedAttackValue(attemptedModValue);
			int StrongestValue = AICreature.getBattleActions().get(strongestIndex).getModifiedAttackValue(strongestModValue);
			if(AttemptedValue > StrongestValue){
				strongestIndex = i;
			}
			else if(AttemptedValue == StrongestValue) {
				if(Math.random() < 0.5) {
					strongestIndex = AttemptedValue;
				}
			}
			System.out.println("Got to findstrongMove C." + i);
		}
		return strongestIndex;
	}
	
	private int findBestHeal() {
		int sizeOfBattleActions = AICreature.getBattleActions().size();
		int bestIndex = 0;
		int bestModValue = AICreature.getBattleActionsUseCount().get(0);
		for(int i=1; i<sizeOfBattleActions; i++) {
			int attemptedModValue = AICreature.getBattleActionsUseCount().get(i);
			if(AICreature.getBattleActions().get(i).getModifiedRecoverValue(bestModValue) > AICreature.getBattleActions().get(bestIndex).getModifiedRecoverValue(attemptedModValue)) {
				bestIndex = i;
			}
		}
		return bestIndex;
	}
	
}

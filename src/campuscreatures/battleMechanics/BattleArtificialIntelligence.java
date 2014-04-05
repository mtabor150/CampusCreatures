package campuscreatures.battleMechanics;

import java.util.Random;

/*
 * Simple AI in order to have single-player mode, which is going to end up being an
 * important component of this game.
 */
public class BattleArtificialIntelligence {
	
	
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
		int chooseIndex;
		if(AICreature.getCurrentHealth() < 3) {
			chooseIndex = findBestHeal();
		}
		else {
			chooseIndex = findStrongestMove();
		}
		AICreature.chooseBattleAction(chooseIndex);
	}
	
	private int findStrongestMove() {
		int sizeOfBattleActions = AICreature.getBattleActions().size();
		int strongestIndex = 0;
		for(int i=1; i<sizeOfBattleActions; i++) {
			if(AICreature.getBattleActions().get(i).getAttackVal() > AICreature.getBattleActions().get(strongestIndex).getAttackVal()) {
				strongestIndex = i;
			}
		}
		return strongestIndex;
	}
	
	private int findBestHeal() {
		int sizeOfBattleActions = AICreature.getBattleActions().size();
		int bestIndex = 0;
		for(int i=1; i<sizeOfBattleActions; i++) {
			if(AICreature.getBattleActions().get(i).getRecoverVal() > AICreature.getBattleActions().get(bestIndex).getRecoverVal()) {
				bestIndex = i;
			}
		}
		return bestIndex;
	}
	
}

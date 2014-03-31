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
	 * For now, calculateNextMove() will just randomly choose a move from the arrayList of moves
	 */
	public void calculateNextMove() {
		int numMoves = AICreature.getBattleActions().size();
		if(numMoves == 0) {
			return; //each creature should have at least one move;
		}
		Random random = new Random();
		int randomNumber = random.nextInt(numMoves-1);
		AICreature.chooseBattleAction(randomNumber);
	}
	
}

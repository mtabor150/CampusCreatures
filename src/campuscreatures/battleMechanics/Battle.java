package campuscreatures.battleMechanics;

import java.io.Serializable;
import java.util.Random;

public class Battle implements Serializable {

	//two creatures
	private transient BattleCreature playerCreature;
	private transient BattleCreature oppCreature;
	//round==0 will mean the battle is over
	private int round;
	private boolean isSinglePlayer;
	private transient BattleArtificialIntelligence battleAI;
	private transient BattlePrompt battlePrompt;
	private boolean playerChoseActionForRound;
	private boolean opponentChoseActionForRound;
	
	//the only unknown attributes are playerCreature and oppCreature
	public Battle(BattleCreature player, BattleCreature opp, Boolean isSinglePlayer) {
		playerCreature = player;
		oppCreature = opp;
		round = 1;
		this.isSinglePlayer = isSinglePlayer;
		if(isSinglePlayer) {
			battleAI = new BattleArtificialIntelligence(0,this);
		}
		battlePrompt = new BattlePrompt(10);
		playerChoseActionForRound = false;
		opponentChoseActionForRound = false;
	}
	
	//get the round
	public int getRound(){
		return round;
	}
	
	//return whether it is single player or not
	public boolean isSinglePlayer() {
		return isSinglePlayer;
	}
	
	//doBattleAction should implement chance of hitting other creature as well
	public void doBattleAction(BattleCreature thisCreature, BattleAction battleAction) {
		//adjust creature health and opponent health. simple implementation.
		BattleCreature otherCreature; //other Creature is used to understand who the other creature is
		if(thisCreature == playerCreature) {
			otherCreature = oppCreature;
			
		}
		else {
			otherCreature = playerCreature;
		}
		
		int damage = handleBattleAction(battleAction, true);
		int regeneration = handleBattleAction(battleAction, false);
		
		damage = otherCreature.adjustHealth(damage);
		regeneration = thisCreature.adjustHealth(regeneration);
		
		
		if (damage != 0) {
			battlePrompt.addIndexedMessage(thisCreature.getTitle() + " was able to inflict " + damage + " damage on " + otherCreature.getTitle());
		}
		else if (regeneration != 0) {
			battlePrompt.addIndexedMessage(thisCreature.getTitle() + " was able to regenerate " + regeneration + " healthpoints");
		}
		else {
			battlePrompt.addIndexedMessage(thisCreature.getTitle() + " was not able to effectively do anything");
		}
	}
	
	/*we differentiate between player action and opponent action to separate control between the
	 *two creatures. Then we have one function to carry out both of these Actions.
	 */
	public void playerAction() {
		if(round != 0) {
			doBattleAction(playerCreature, playerCreature.getCurrentBattleAction());
			checkEndGame();
		}
		
	}
	
	public void oppAction() {
		if(round != 0) {
			if(isSinglePlayer) { //if isSinglePlayer, make sure to automate a move
				battleAI.calculateNextMove();
				battlePrompt.addIndexedMessage(oppCreature.getTitle() + " chose " + oppCreature.getCurrentBattleAction().getTitle());
			}
			doBattleAction(oppCreature, oppCreature.getCurrentBattleAction());
			checkEndGame();
		}	
	}
	
	/*
	 * choose the creature whose speed is greater to move first.
	 * the second action won't be carried out if the first action ends the game.
	 * ****need to consider when speeds are the same; for now give the advantage to player.
	 * ****perhaps make a more complicated algorithm to decide who goes first.
	 * ****In the end, might need to add an element of randomization.
	 */
	public void implementRound() {
		if (round == 0) { //end of game, so no more rounds to implement
			return;
		}
		round++;
		
		/*
		 * player opponent must choose a new action for each round
		 */
		if (playerChoseActionForRound == false) {
			battlePrompt.addIndexedMessage(playerCreature.getTitle() + " must choose an action for this round");
			return;
		}
		else if (opponentChoseActionForRound == false & !isSinglePlayer()) {
			battlePrompt.addIndexedMessage(oppCreature.getTitle() + " must choose an action for this round");
			return;
		}
		
		
		if(playerCreature.getSpeed() >= oppCreature.getSpeed()) {
			battlePrompt.addIndexedMessage(playerCreature.getTitle() + " moved faster and acted first!");
			playerAction();
			if(round == 0) return; //end of battle
			battlePrompt.addIndexedMessage(oppCreature.getTitle() + " moved second...");
			oppAction();
			
		}
		else {
			battlePrompt.addIndexedMessage(oppCreature.getTitle() + " moved faster and acted first");
			oppAction();
			if(round == 0) return; //end of battle
			battlePrompt.addIndexedMessage(playerCreature.getTitle() + " moved second...");
			playerAction();
			
		}
		playerChoseActionForRound = false;
		opponentChoseActionForRound = false;
		battlePrompt.addRoundHeader(round);
	}
	
	
	//check end game possibilities, if it is end of game, set round to 0
	public void checkEndGame() {
		if(playerCreature.getCurrentHealth() == 0) {
			round = 0;
			battlePrompt.creatureWon(oppCreature);
		}
		else if(oppCreature.getCurrentHealth() == 0) {
			round = 0;
			battlePrompt.creatureWon(playerCreature);
		}
	}
	
	/*
	 * return the player creature or opponent creature
	 */
	public BattleCreature getPlayerBattleCreature(){
		return playerCreature;
	}
	
	public BattleCreature getOpponentBattleCreature(){
		return oppCreature;
	}
	

	public String battlePromptAsString() {
		return battlePrompt.battlePromptAsString();
	}
	
	public void choosePlayerBattleAction(int i) {
		if(round ==0) {
			return;
		}
		playerChoseActionForRound = true;
		playerCreature.chooseBattleAction(i);
		battlePrompt.addIndexedMessage(playerCreature.getTitle() + " chose " + playerCreature.getCurrentBattleAction().getTitle());
	}
	
	public void chooseOppBattleAction(int i) {
		if(round ==0) {
			return;
		}
		opponentChoseActionForRound = true;
		oppCreature.chooseBattleAction(i);
	}
	
	
	/*
	 * handleBattleAction will interpret how the value of a battleAction will affect the creatures
	 * in the battle based on creature stats, types, and other considerations
	 * For now, we are just going to return a [0,2] random modification range
	 */
	private int handleBattleAction(BattleAction battleAction, boolean isAttack){
		int MinVal;
		int MaxVal;
		if(isAttack) {
			MinVal = -2*battleAction.getAttackVal();
			MaxVal = 0;
		}
		else {
			MinVal = 0;
			MaxVal = 2*battleAction.getRecoverVal();
		}
		
		return MinVal + (int)(Math.random() * ((MaxVal - MinVal) + 1));
	}
}

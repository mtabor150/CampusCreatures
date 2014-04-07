package campuscreatures.battleMechanics;

import java.io.Serializable;
import java.util.Random;

import campuscreatures.main.BattleActivity;
import android.content.Intent;
import android.view.View;

public class Battle implements Serializable {

	//two creatures
	private BattleCreature playerCreature;
	private BattleCreature oppCreature;
	//round==0 will mean the battle is over
	private int round;
	private boolean isSinglePlayer;
	private BattleArtificialIntelligence battleAI;
	private BattlePrompt battlePrompt;
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
		
		int damage = handleBattleAction(thisCreature, battleAction, true);
		int regeneration = handleBattleAction(thisCreature, battleAction, false);
		
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
			System.out.println("Got to playerAction()");
			doBattleAction(playerCreature, playerCreature.getCurrentBattleAction());
			checkEndGame();
			System.out.println("Got out of playerAction()");
		}
		
	}
	
	public void oppAction() {
		System.out.println("Got to oppAction()");
		if(round != 0) {
			if(isSinglePlayer) { //if isSinglePlayer, make sure to automate a move
				System.out.println("Got to calculateNextMove()");
				battleAI.calculateNextMove();
				System.out.println("Got out of calculateNextMove()");
				battlePrompt.addIndexedMessage(oppCreature.getTitle() + " chose " + oppCreature.getCurrentBattleAction().getTitle());
			}
			doBattleAction(oppCreature, oppCreature.getCurrentBattleAction());
			checkEndGame();
		}
		System.out.println("Got out of oppAction()");
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
		BattleCreature winningCreature = null;
		BattleCreature losingCreature = null;
		if(playerCreature.getCurrentHealth() == 0) {
			round = 0;
			battlePrompt.creatureWon(oppCreature);
			winningCreature = oppCreature;
			losingCreature = playerCreature;
		}
		else if(oppCreature.getCurrentHealth() == 0) {
			round = 0;
			battlePrompt.creatureWon(playerCreature);
			winningCreature = playerCreature;
			losingCreature = oppCreature;
		}
		if(round == 0) {
			modifyExperienceAndLevel(winningCreature, losingCreature);
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
		if(round ==0 | (playerChoseActionForRound & (playerCreature.getCurrentBattleAction() == playerCreature.getBattleActions().get(i)))) {
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
	private int handleBattleAction(BattleCreature thisCreature, BattleAction battleAction, boolean isAttack){
		int MinVal;
		int MaxVal;
		int actionUseCount = thisCreature.getCurrentBattleActionUseCount();
		if(isAttack) {
			MinVal = (-2)*battleAction.getModifiedAttackValue(thisCreature.getCurrentBattleActionUseCount());
			MaxVal = 0;
		}
		else {
			MinVal = 0;
			MaxVal = 2*battleAction.getModifiedRecoverValue(thisCreature.getCurrentBattleActionUseCount())/actionUseCount;
		}
		System.out.println("MinVal = " + MinVal);
		System.out.println("MaxVal = " + MaxVal);
		
		return MinVal + (int)(Math.random() * ((MaxVal - MinVal) + 1));
	}
	
	/*
	 * inccreases creature experience and level accordingly
	 */
	private void modifyExperienceAndLevel(BattleCreature winner, BattleCreature loser) {
		int winExpIncrease = loser.getLevel()*10;
		winner.increaseCreatureExperience(winExpIncrease);
		battlePrompt.addMessage(winner.getTitle() + "'s experience increased by " + winExpIncrease);
		int loseExpIncrease = winner.getLevel()*3;
		loser.increaseCreatureExperience(winner.getLevel()*3);
		battlePrompt.addMessage(loser.getTitle() + "'s experience increased by " + loseExpIncrease);
		while(winner.getCreatureExperience() > winner.getLevel()*100) {
			winner.incrementLevel();
			battlePrompt.addMessage(winner.getTitle() + " increased to level " + winner.getLevel());
		}
		while(loser.getCreatureExperience() > winner.getLevel()*100) {
			loser.incrementLevel();
			battlePrompt.addMessage(loser.getTitle() + " increased to level " + loser.getLevel());
		}
	}
}

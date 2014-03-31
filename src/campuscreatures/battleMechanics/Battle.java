package campuscreatures.battleMechanics;

public class Battle {

	//two creatures
	private BattleCreature playerCreature;
	private BattleCreature oppCreature;
	//round==0 will mean the battle is over
	private int round;
	private boolean isSinglePlayer;
	private BattleArtificialIntelligence battleAI;
	
	//the only unknown attributes are playerCreature and oppCreature
	public Battle(BattleCreature player, BattleCreature opp, Boolean isSinglePlayer) {
		playerCreature = player;
		oppCreature = opp;
		round = 1;
		this.isSinglePlayer = isSinglePlayer;
		if(isSinglePlayer) {
			battleAI = new BattleArtificialIntelligence(0,this);
		}
	}
	
	//get the round
	public int getRound(){
		return round;
	}
	
	//return whether it is single player or not
	public boolean isSinglePlayer() {
		return isSinglePlayer;
	}
	
	//other methods
	public void doBattleAction(BattleCreature creature, BattleAction battleAction) {
		//adjust creature health and opponent health. simple implementation.
		if(creature == playerCreature) {
			oppCreature.adjustHealth(-battleAction.getAttackVal());
			playerCreature.adjustHealth(battleAction.getRecoverVal());
		}
		else {
			playerCreature.adjustHealth(-battleAction.getAttackVal());
			oppCreature.adjustHealth(battleAction.getRecoverVal());
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
				doBattleAction(oppCreature,oppCreature.getCurrentBattleAction());
			}
			else {
				doBattleAction(oppCreature, oppCreature.getCurrentBattleAction());
			}
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
		round++;
		if(playerCreature.getSpeed() >= oppCreature.getSpeed()) {
			playerAction();
			oppAction();
		}
		else {
			oppAction();
			playerAction();
		}
	}
	
	
	//check end game possibilities, if it is end of game, set round to 0
	public void checkEndGame() {
		if(playerCreature.getCurrentHealth() == 0 | oppCreature.getCurrentHealth() == 0) {
			round = 0;
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
}

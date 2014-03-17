package battleMechanics;

public class Battle {

	//two creatures
	private BattleCreature playerCreature;
	private BattleCreature oppCreature;
	//round==0 will mean the battle is over
	private int round;
	private boolean isPlayerTurn;
	private boolean isSinglePlayer;
	private BattleArtificialIntelligence battleAI;
	
	//the only unknown attributes are playerCreature and oppCreature
	public Battle(BattleCreature player, BattleCreature opp, Boolean isSinglePlayer) {
		playerCreature = player;
		oppCreature = opp;
		round = 1;
		isPlayerTurn = true;
		this.isSinglePlayer = isSinglePlayer;
		if(isSinglePlayer) {
			battleAI = new BattleArtificialIntelligence(0,this);
		}
	}
	
	//get the round
	public int getRound(){
		return round;
	}
	
	//return whether it is the player turn
	public boolean isPlayerTurn() {
		if(isPlayerTurn) {
			return true;
		}
		else{
			return false;
		}
	}
	
	//return whether it is single player or not
	public boolean isSinglePlayer() {
		return isSinglePlayer;
	}
	
	
	//we differentiate between player action and opponent action to seperate control between the
	//two creatures.
	
	//player action
	public void playerAction(int i) {
		if(isPlayerTurn & round != 0) {
			playerCreature.doBattleAction(oppCreature, i);
			isPlayerTurn = false;
			checkEndGame();
		}
		
	}
	
	//opponent action
	public void oppAction(int i) {
		if(!isPlayerTurn & round != 0) {
			if(isSinglePlayer) { //if isSinglePlayer, make sure to automate a move
				int AIMove = battleAI.calculateNextMove();
				oppCreature.doBattleAction(playerCreature,AIMove);
			}
			else {
				oppCreature.doBattleAction(playerCreature, i);
			}
			isPlayerTurn = true;
			round++;
			checkEndGame();
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

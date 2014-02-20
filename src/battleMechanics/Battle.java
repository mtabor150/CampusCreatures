package battleMechanics;

public class Battle {

	//two creatures
	private Creature playerCreature;
	private Creature oppCreature;
	//round==0 will mean the battle is over
	private int round;
	private boolean isPlayerTurn;
	
	//the only unknown attributes are playerCreature and oppCreature
	public Battle(Creature player, Creature opp) {
		playerCreature = player;
		oppCreature = opp;
		round = 1;
		isPlayerTurn = true;
	}
	
	public int getRound(){
		return round;
	}
	
	//player action
	public void playerAction(int i) {
		if(isPlayerTurn & round != 0) {
			playerCreature.doBattleAction(oppCreature, i);
			isPlayerTurn = false;
		}
		else {
			return;
		}
	}
	
	//opponent action
	public void oppAction(int i) {
		if(!isPlayerTurn & round != 0) {
			oppCreature.doBattleAction(playerCreature, i);
			isPlayerTurn = true;
			round++;
		}
		else{
			return;
		}
	}
	
}

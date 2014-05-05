package campuscreatures.battleMechanics;

public class BattleUpgrade {
	private int totalUpgrade;
	private int upgradeUsed;
	private int healthPlus;
	private int attackPlus;
	private int speedPlus;
	private int defensePlus;
	
	public BattleUpgrade(int upgrade) {
		totalUpgrade = upgrade;
		upgradeUsed = 0;
		healthPlus = 0;
		attackPlus = 0;
		speedPlus = 0;
		defensePlus = 0;
	}
	
	public void incrementHealth() {
		if(totalUpgrade != upgradeUsed) {
			healthPlus++;
			upgradeUsed++;
		}
	}
	
	public void incrementAttack() {
		if(totalUpgrade != upgradeUsed) {
			attackPlus++;
			upgradeUsed++;
		}
	}
	
	public void incrementSpeed() {
		if(totalUpgrade != upgradeUsed) {
			speedPlus++;
			upgradeUsed++;
		}
	}
	
	public void incrementDefense() {
		if(totalUpgrade != upgradeUsed) {
			defensePlus++;
			upgradeUsed++;
		}
	}
	
	public void decrementHealth() {
		if(healthPlus>0) {
			healthPlus--;
			upgradeUsed--;
		}
	}
	
	public void decrementAttack() {
		if(attackPlus>0) {
			attackPlus--;
			upgradeUsed--;
		}
	}
	
	public void decrementSpeed() {
		if(speedPlus >0) {
			speedPlus--;
			upgradeUsed--;
		}
	}
	
	public void decrementDefense() {
		if(defensePlus >0) {
			defensePlus--;
			upgradeUsed--;
		}
	}
	
	public boolean upgradeComplete() {
		if(upgradeUsed == totalUpgrade) {
			return true;
		}
		return false;
	}
	
	public int getHealthPlus() {
		return healthPlus;
	}
	
	public int getAttackPlus() {
		return attackPlus;
	}
	
	public int getSpeedPlus() {
		return speedPlus;
	}
	
	public int getDefensePlus() {
		return defensePlus;
	}
	
	public int getUpgradeUsed() {
		return upgradeUsed;
	}
	
	public int getUpgradeLeft() {
		return totalUpgrade - upgradeUsed;
	}
}

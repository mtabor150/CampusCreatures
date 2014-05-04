package campuscreatures.database;

import java.util.ArrayList;

import campuscreatures.battleMechanics.BattleAction;

/**
 * 	Creatures class: 
 * 	Private variables:
 *  - 13 total attributes per creature
 *  - 4 string attribute values including name
 *  - 9 integer attribute values, including id
 */
public class Creatures {
	
	int _id;			//primary key of the database
	String _name;
	String _region;
	String _district;
	String _type;
	int _health;
	int _magic;
	int _attack;
	int _defense;
	int _speed;
	int _moves_per_turn;
	int _experience;
	int _level;
	
	//Empty constructor
	public Creatures(){}
	
	//constructor
	public Creatures(String name, String region, String district, String type,
			int health, int magic, int attack, int defense, 
			int speed, int moves_per_turn, int exp, int level) {
		this._name = name;
		this._region = region;
		this._district = district;
		this._type = type;
		this._health = health;
		this._magic = magic;
		this._attack = attack;
		this._defense = defense;
		this._speed = speed;
		this._moves_per_turn = moves_per_turn;
		this._experience = exp;
		this._level = level;
	}
	
	//constructor
	public Creatures(int id, String name, String region, String district, 
			String type, int health, int magic, int attack, int defense, 
			int speed, int moves_per_turn, int exp, int level) {
		this._id = id;
		this._name = name;
		this._region = region;
		this._district = district;
		this._type = type;
		this._health = health;
		this._magic = magic;
		this._attack = attack;
		this._defense = defense;
		this._speed = speed;
		this._moves_per_turn = moves_per_turn;
		this._experience = exp;
		this._level = level;
	}
		
	public String toString() {
//		return  "Creatures toString() function: " +
//				" ID: " + _id +
//				", name: " + _name +
//				", region: " + _region +
//				", district: " + _district +
//				", type: " + _type +
//				", health: " + _health +
//				", magic: " + _magic +
//				", attack: " + _attack +
//				", defense: " + _defense +
//				", speed: " + _speed +
//				", moves per turn: " + _moves_per_turn +
//				", experience: " + _experience +
//				", level: " + _level;	
		return  " ID: " + _id + ". " + _name + ", " + _region + "\n";	
	}
	
	/*  All the attribute functions: set and get values */
	
	//ID attribute
	public int getId() {
		return this._id;
	}
	public void setId(int id){
		this._id = id;
	}

	//name attribute
	public String getName() {
		return this._name;
	}
	public void setName(String name){
		this._name = name;
	}
	
	//region attribute
	public String getRegion(){
		return this._region;
	}
	public void setRegion(String region) {
		this._region = region;
	}
	
	//district attribute
	public String getDistrict(){
		return this._district;
	}
	public void setDistrict(String district) {
		this._district = district;
	}
	
	//type attribute
	public String getType(){
		return this._type;
	}
	public void setType(String type) {
		this._type = type;
	}
	
	//health attribute
	public int getHealth(){
		return this._health;
	}
	public void setHealth(int health) {
		this._health = health;
	}
	
	//magic attribute
	public int getMagic(){
		return this._magic;
	}
	public void setMagic(int magic) {
		this._magic = magic;
	}
	
	//attack attribute
	public int getAttack(){
		return this._attack;
	}
	public void setAttack(int attack) {
		this._attack = attack;
	}
	
	//defense attribute
	public int getDefense(){
		return this._defense;
	}
	public void setDefense(int defense){
		this._defense = defense;
	}
	
	// speed attribute
	public int getSpeed(){
		return this._speed;
	}
	public void setSpeed(int speed){
		this._speed = speed;
	}
	
	//moves per turn attribute
	public int getMovesPerTurn(){
		return this._moves_per_turn;
	}
	public void setMovesPerTurn(int moves_per_turn){
		this._moves_per_turn = moves_per_turn;
	}
	
	//experience attribute
	public int getExperience(){
		return this._experience;
	}
	public void setExperience(int experience){
		this._experience = experience;
	}
	
	//level attribute
	public int getLevel(){
		return this._level;
	}
	public void setLevel(int level){
		this._level = level;
	}
	
	public ArrayList<BattleAction> getMoveSet(Creatures creature){
		int level = creature.getLevel();
		String type = creature.getType();
		ArrayList<BattleAction> move_set = new ArrayList();
		
		//Initialize all the moves and their battle values
		//Basic Abilities
		BattleAction kick_1 = new BattleAction("Scratch",1,0,10);
		BattleAction kick_2 = new BattleAction("Swipe",2,0,10);
		BattleAction kick_3 = new BattleAction("Gouge",3,0,10);
		BattleAction kick_4 = new BattleAction("Shred",4,0,10);
		//Recovery Abilities
		BattleAction heal_1 = new BattleAction("First Aid",0,2,10);
		BattleAction heal_2 = new BattleAction("Recover",0,4,10);
		BattleAction heal_3 = new BattleAction("Heal",0,6,10);
		BattleAction heal_4 = new BattleAction("Regenerate",0,8,10);
		//Better Basic Abilities
		BattleAction intimidate_1 = new BattleAction("Slap",2,0,10);
		BattleAction intimidate_2 = new BattleAction("Punch",3,0,10);
		BattleAction intimidate_3 = new BattleAction("Kick",3,0,10);
		BattleAction intimidate_4 = new BattleAction("Throw",4,0,10);
		//Earth Type Abilities
		BattleAction earth_1 = new BattleAction("Pebble Shot",1,0,10);
		BattleAction earth_2 = new BattleAction("Rock Throw", 2, 0, 10);
		BattleAction earth_3 = new BattleAction("Rock Slide",3,0,10);
		BattleAction earth_4 = new BattleAction("Earthquake", 4, 0, 10);
		//Lightning Type Abilities
		BattleAction shock_1 = new BattleAction("Static",1,0,10);
		BattleAction shock_2 = new BattleAction("Shock",2,0,10);
		BattleAction shock_3 = new BattleAction("Electrocute",3,0,10);
		BattleAction shock_4 = new BattleAction("Lightning Storm",4,0,10);
		//Space Type Abilities
		BattleAction space_1 = new BattleAction("Cosmic Dust",1,0,10);
		BattleAction space_2 = new BattleAction("Comet",2,0,10);
		BattleAction space_3 = new BattleAction("Meteor Shower",3,0,10);
		BattleAction space_4 = new BattleAction("Black Hole",4,0,10);
		//Spirit Type Abilities
		BattleAction spirit_1 = new BattleAction("Spook",1,0,10);
		BattleAction spirit_2 = new BattleAction("Haunt",2,0,10);
		BattleAction spirit_3 = new BattleAction("Horrify",3,0,10);
		BattleAction spirit_4 = new BattleAction("Soul Steal",4,0,10);
		//Psychic Type Abilities
		BattleAction psych_1 = new BattleAction("Confound",1,0,10);
		BattleAction psych_2 = new BattleAction("Bewitch",2,0,10);
		BattleAction psych_3 = new BattleAction("Mind Spike",3,0,10);
		BattleAction psych_4 = new BattleAction("Thought Sieze",4,0,10);
		//Normal Type Abilities
		BattleAction normal_1 = new BattleAction("Cut", 1,0,10);
		BattleAction normal_2 = new BattleAction("Bite", 2,0,10);
		BattleAction normal_3 = new BattleAction("Hyper Fang", 2,0,10);
		BattleAction normal_4 = new BattleAction("Rollout", 4,0,10);
		//Fire Type Abilities
		BattleAction fire_1 = new BattleAction("Burn", 1,0,10);
		BattleAction fire_2 = new BattleAction("Scorch", 2,0,10);
		BattleAction fire_3 = new BattleAction("Conflagrate", 2,0,10);
		BattleAction fire_4 = new BattleAction("Immolate", 4,0,10);
		
		//Assign basic three moves
		if (level < 5){
			move_set.add(kick_1);
			move_set.add(heal_1);
			move_set.add(intimidate_1);
		}
		else{
			if (level < 16){
				move_set.add(kick_2);
				move_set.add(heal_2);
				move_set.add(intimidate_2);
			}
			else{
				if (level < 25){
					move_set.add(kick_3);
					move_set.add(heal_3);
					move_set.add(intimidate_3);
				}
				else{
					move_set.add(kick_4);
					move_set.add(heal_4);
					move_set.add(intimidate_4);
				}
			}
		}
		//Assign Psychic moves
		if (type == "psychic"){
			if (level < 2){
				move_set.add(psych_1);
			}
			else{
				if (level < 9){
					move_set.add(psych_2);
				}
				else{
					if (level < 21){
						move_set.add(psych_3);
					}
					else{
						move_set.add(psych_4);
					}
				}
			}
		}
		//Assign Earth moves
		if (type == "earth"){
			if (level < 2){
				move_set.add(earth_1);
			}
			else{
				if (level < 9){
					move_set.add(earth_2);
				}
				else{
					if (level < 21){
						move_set.add(earth_3);
					}
					else{
						move_set.add(earth_4);
					}
				}
			}
		}
		//Assign Fire moves
		if (type == "fire"){
			if (level < 2){
				move_set.add(fire_1);
			}
			else{
				if (level < 9){
					move_set.add(fire_2);
				}
				else{
					if (level < 21){
						move_set.add(fire_3);
					}
					else{
						move_set.add(fire_4);
					}
				}
			}
		}
		//Assign Space moves
		if (type == "space"){
			if (level < 2){
				move_set.add(space_1);
			}
			else{
				if (level < 9){
					move_set.add(space_2);
				}
				else{
					if (level < 21){
						move_set.add(space_3);
					}
					else{
						move_set.add(space_4);
					}
				}
			}
		}
		//Assign Normal moves
		if (type == "normal"){
			if (level < 2){
				move_set.add(normal_1);
			}
			else{
				if (level < 9){
					move_set.add(normal_2);
				}
				else{
					if (level < 21){
						move_set.add(normal_3);
					}
					else{
						move_set.add(normal_4);
					}
				}
			}
		}
		//Assign Spirit moves
		if (type == "spirit"){
			if (level < 2){
				move_set.add(spirit_1);
			}
			else{
				if (level < 9){
					move_set.add(spirit_2);
				}
				else{
					if (level < 21){
						move_set.add(spirit_3);
					}
					else{
						move_set.add(spirit_4);
					}
				}
			}
		}
		//Assign Electric moves
		if (type == "electric"){
			if (level < 2){
				move_set.add(shock_1);
			}
			else{
				if (level < 9){
					move_set.add(shock_2);
				}
				else{
					if (level < 21){
						move_set.add(shock_3);
					}
					else{
						move_set.add(shock_4);
					}
				}
			}
		}
		
		//Return ArrayList of the moves
		return move_set;
	}
}

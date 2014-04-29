package campuscreatures.database;
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
}

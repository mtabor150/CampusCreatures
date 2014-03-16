package database;

public class Creatures {
	
	/*
	 * Private variables:
	 *  - 13 total attributes per creature
	 *  - 4 string attribute values including name
	 *  - 9 integer attribute values, including ID 
	 */
	
	private int id;			//primary key of the database
	private String name;
	
	private String region;
	private String district;
	private String type;
	
	private int health;
	private int magic;
	private int attack;
	private int defense;
	private int speed;
	private int moves_per_turn;
	private int experience;
	private int level;
	
	//Empty constructor
	public Creatures(){}
	
	//constructor
	public Creatures(String name, String region, String district, String type,
			int health, int magic, int attack, int defense, int speed, int moves_per_turn, int exp, int level) {
		this.name = name;
		this.region = region;
		this.district = district;
		this.type = type;
		this.health = health;
		this.magic = magic;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.moves_per_turn = moves_per_turn;
		this.experience = exp;
		this.level = level;
	}
	
	
	public String toString() {
		return  "This is the toString() function" + "\n" +
				"Creature [id= " + id +
				", name = " + name +
				", region = " + region +
				", district = " + district +
				", type = " + type +
				", health = " + health +
				", magic = " + magic +
				", attack = " + attack +
				", defense = " + defense +
				", speed = " + speed +
				", moves per turn = " + moves_per_turn +
				", experience = " + experience +
				", level = " + level;	
	}
	
	/**
	 * 
	 * All the attribute functions: set and get values
	 * 
	 */
	
	//ID attribute
	public int getId() {
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}

	//name attribute
	public String getName() {
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	//region attribute
	public String getRegion(){
		return this.region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	//district attribute
	public String getDistrict(){
		return this.district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	//type attribute
	public String getType(){
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//health attribute
	public int getHealth(){
		return this.health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	//magic attribute
	public int getMagic(){
		return this.magic;
	}
	public void setMagic(int magic) {
		this.magic = magic;
	}
	
	//attack attribute
	public int getAttack(){
		return this.attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	//defense attribute
	public int getDefense(){
		return this.defense;
	}
	public void setDefense(int defense){
		this.defense = defense;
	}
	
	// speed attribute
	public int getSpeed(){
		return this.speed;
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	//moves per turn attribute
	public int getMovesPerTurn(){
		return this.moves_per_turn;
	}
	public void setMovesPerTurn(int moves_per_turn){
		this.moves_per_turn = moves_per_turn;
	}
	
	//experience attribute
	public int getExperience(){
		return this.experience;
	}
	public void setExperience(int exp){
		this.experience = exp;
	}
	
	//level attribute
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level = level;
	}
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++++ Done
	 */

}

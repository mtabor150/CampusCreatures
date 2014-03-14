package database;

public class Creatures {
	
	//13 total attributes to creatures
	private int id;			//primary key of the database
	private String name;
	//4 string attribute values including name 
	private String region;
	private String district;
	private String type;
	//9 int attribute values, including ID
	private int health;
	private int magic;
	private int attack;
	private int defense;
	private int speed;
	private int moves_per_turn;
	private int experience;
	private int level;
	
	public Creatures(){}
	
	public Creatures(String name, String region, String district, String type,
			int health, int magic, int attack, int defense, int speed, int mpt, int exp, int level) {
		super();
		this.name = name;
		this.region = region;
		this.district = district;
		this.type = type;
		this.health = health;
		this.magic = magic;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.moves_per_turn = mpt;
		this.experience = exp;
		this.level = level;
	}
	
	@Override
	public String toString() {
		return "Creature [id= " + id +
				", name= " + name +
				", region= " + region +
				", district= " + district +
				", type= " + type +
				", health= " + health +
				", magic= " + magic +
				", attack= " + attack +
				", defense= " + defense +
				", speed= " + speed +
				", moves per turn= " + moves_per_turn +
				", experience= " + experience +
				", level= " + level;	
	}
	
	/**
	 * 
	 * All the attribute functions: set and get values
	 * 
	 */
	
	//ID attribute
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	//name attribute
	public String getName() {
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	//region attribute
	public String getRegion(){
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	//district attribute
	public String getDistrict(){
		return district;
	}
	public void setDistrict(String district) {
		// TODO Auto-generated method stub
		this.district = district;
	}
	
	//type attribute
	public String getType(){
		return type;
	}
	public void setType(String type) {
		// TODO Auto-generated method stub
		this.type = type;
	}
	
	//health attribute
	public int getHealth(){
		return health;
	}
	public void setHealth(int health) {
		// TODO Auto-generated method stub
		this.health = health;
	}
	
	//magic attribute
	public int getMagic(){
		return magic;
	}
	public void setMagic(int magic) {
		// TODO Auto-generated method stub
		this.magic = magic;
	}
	
	//attack attribute
	public int getAttack(){
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	//defense attribute
	public int getDefense(){
		return defense;
	}
	public void setDefense(int defense){
		this.defense = defense;
	}
	
	// speed attribute
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	//moves per turn attribute
	public int getMovesPerTurn(){
		return moves_per_turn;
	}
	public void setMovesPerTurn(int mpt){
		this.moves_per_turn = mpt;
	}
	
	//experience attribute
	public int getExperience(){
		return experience;
	}
	public void setExperience(int exp){
		this.experience = exp;
	}
	
	//level attribute
	public int getLevel(){
		return level;
	}
	public void setLevel(int level){
		this.level = level;
	}
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++++ Done
	 */

}

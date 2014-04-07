package campuscreatures.database;

/**
 * 
 * @author pjdesi
 * 
 * Player class 
 * - used for the player table in the database
 * - same attributes as Creatures table, but added "captured" as prefix 
 * 	 in order to distinguish player's own instance of a creature
 * - added a creatureSeen boolean type and a seenAt string type
 * - added a captured boolean type
 * - all int attributes, not including _id should allow for incrementing
 * 
 */

public class Player {
	
	int _id;					//primary key of the database
	String captured_Name;
	String captured_Region;
	String captured_District;
	String captured_Type;
	int captured_Health;
	int captured_Magic;
	int captured_Attack;
	int captured_Defense;
	int captured_Speed;
	int captured_MPT;
	int captured_Experience;
	int captured_Level;
//	boolean creature_Seen;		//used for recon
//	String seen_At;				//used for recon
//	boolean _captured;
	String creature_Seen;
	String seen_At;
	String _captured;
	
	//constructors
	public Player (){
	}

	public Player (String capturedName, String capturedRegion, String capturedDistrict,
			String capturedType, int capturedHealth, int capturedMagic, int capturedAttack, int capturedDefense,
			int capturedSpeed, int capturedMPT, int capturedExperience, int capturedLevel, String creatureSeen, 
			String seenAt, String captured) {
		
		this.captured_Name = capturedName;
		this.captured_Region = capturedRegion;
		this.captured_District = capturedDistrict;
		this.captured_Type = capturedType;
		this.captured_Health = capturedHealth;
		this.captured_Magic = capturedMagic;
		this.captured_Attack = capturedAttack;
		this.captured_Defense = capturedDefense;
		this.captured_Speed = capturedSpeed;
		this.captured_MPT = capturedMPT;
		this.captured_Experience = capturedExperience;
		this.captured_Level = capturedLevel;
		this.creature_Seen = creatureSeen;
		this.seen_At = seenAt;
		this._captured = captured;		
	}
	
	public Player (int id, String capturedName, String capturedRegion, String capturedDistrict,
			String capturedType, int capturedHealth, int capturedMagic, int capturedAttack, int capturedDefense,
			int capturedSpeed, int capturedMPT, int capturedExperience, int capturedLevel, String creatureSeen, 
			String seenAt, String captured) {
		
		this._id = id;
		this.captured_Name = capturedName;
		this.captured_Region = capturedRegion;
		this.captured_District = capturedDistrict;
		this.captured_Type = capturedType;
		this.captured_Health = capturedHealth;
		this.captured_Magic = capturedMagic;
		this.captured_Attack = capturedAttack;
		this.captured_Defense = capturedDefense;
		this.captured_Speed = capturedSpeed;
		this.captured_MPT = capturedMPT;
		this.captured_Experience = capturedExperience;
		this.captured_Level = capturedLevel;
		this.creature_Seen = creatureSeen;
		this.seen_At = seenAt;
		this._captured = captured;		
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
		return this.captured_Name;
	}
	public void setName(String name){
		this.captured_Name = name;
	}
	
	//region attribute
	public String getRegion(){
		return this.captured_Region;
	}
	public void setRegion(String region) {
		this.captured_Region = region;
	}
	
	//district attribute
	public String getDistrict(){
		return this.captured_District;
	}
	public void setDistrict(String district) {
		this.captured_District = district;
	}
	
	//type attribute
	public String getType(){
		return this.captured_Type;
	}
	public void setType(String type) {
		this.captured_Type = type;
	}
	
	//health attribute
	public int getHealth(){
		return this.captured_Health;
	}
	public void setHealth(int health) {
		this.captured_Health = health;
	}
	
	//magic attribute
	public int getMagic(){
		return this.captured_Magic;
	}
	public void setMagic(int magic) {
		this.captured_Magic = magic;
	}
	
	//attack attribute
	public int getAttack(){
		return this.captured_Attack;
	}
	public void setAttack(int attack) {
		this.captured_Attack = attack;
	}
	
	//defense attribute
	public int getDefense(){
		return this.captured_Defense;
	}
	public void setDefense(int defense){
		this.captured_Defense = defense;
	}
	
	// speed attribute
	public int getSpeed(){
		return this.captured_Speed;
	}
	public void setSpeed(int speed){
		this.captured_Speed = speed;
	}
	
	//moves per turn attribute
	public int getMovesPerTurn(){
		return this.captured_MPT;
	}
	public void setMovesPerTurn(int moves_per_turn){
		this.captured_MPT = moves_per_turn;
	}
	
	//experience attribute
	public int getExperience(){
		return this.captured_Experience;
	}
	public void setExperience(int experience){
		this.captured_Experience = experience;
	}
	
	//level attribute
	public int getLevel(){
		return this.captured_Level;
	}
	public void setLevel(int level){
		this.captured_Level = level;
	}
	
	//has creature been seen or not
	public String getSeen(){
		return this.creature_Seen;
	}
	public void setSeen(String answer){
		this.creature_Seen = answer; 
	}
	
	//if seen, then where?
	public String getSeenAt(){
		return this.seen_At;
	}
	public void setSeenAt(String seenAt){
		this.seen_At = seenAt;
	}
	
	//has creature been seen or not
	public String getCaptured(){
		return this.creature_Seen;
	}
	public void setCaptured(String answer){
		this._captured = answer; 
	}
}
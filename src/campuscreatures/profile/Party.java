package campuscreatures.profile;
import java.util.*;

import campuscreatures.database.Creatures;
import campuscreatures.battleMechanics.BattleCreature;

public class Party {
	
	ArrayList<BattleCreature> party;
	
	public Party(){
		
		party = new ArrayList<BattleCreature>();
	}
	
	public BattleCreature getPartyMember(int index){
		return party.get(index);
	}
	
	public void addPartyMember(int index, BattleCreature member){
		
		if (party.get(index) == null){
			
		party.set(index, member);
		
		} else {
			BattleCreature member2 = party.get(index);
			party.set(index, member);
			firstAvailableSlot(member2);
		}
	}
	
	private void firstAvailableSlot(BattleCreature member2){
		int count = 0;
		while (party.get(count) == null){
			count++;
		}
		party.set(count, member2);
	}
	
	public void removePartyMember(BattleCreature creature){
		for(int i=0; i<6; i++){
			if (creature == party.get(i)){
				party.remove(i);
			} 
		}
	}
	public int size(){
		return party.size();
	}
		
}


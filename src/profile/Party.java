package profile;
import java.util.*;

import campuscreatures.database.Creatures;
import campuscreatures.battleMechanics.BattleCreature;

public class Party {
	
	ArrayList<Creatures> party;
	
	public Party(){
		
		ArrayList<BattleCreature> party = new ArrayList<BattleCreature>();
		
	}
	
	public Creatures getPartyMember(int index){
		return party.get(index);
	}
	
	public void addPartyMember(int index, Creatures member){
		
		if (party.get(index) == null){
			
		party.set(index, member);
		
		} else {
			Creatures member2 = party.get(index);
			party.set(index, member);
			firstAvailableSlot(member2);
		}
	}
	
	private void firstAvailableSlot(Creatures member){
		int count = 0;
		while (party.get(count) == null){
			count++;
		}
		party.set(count, member);
	}
		
}


package campuscreatures.battleMechanics;

import java.util.ArrayList;

public class BattlePrompt {
	
	private ArrayList<String> messages;
	private int maxSize;
	private int totalIndexedMessages;
	private boolean roundHeaderAddedLast = false;
	
	public BattlePrompt(int max) {
		messages = new ArrayList();
		maxSize = max;
		totalIndexedMessages = 0;
		addRoundHeader(1);
	}
	
	/*
	 * We have special cases where we want many lines in the prompt indexed. This can help with
	 * visualizing the order of messages; this is useful since message display is instantaneous
	 * and message order is confusing/ambiguous without the indexes, even to the programmer.
	 * A potential upgrade is slow, sequential, and time-separated display of messages.
	 */
	public void addIndexedMessage(String newMessage) {
		totalIndexedMessages++;
		String tempString = totalIndexedMessages + ") " + newMessage; //this is for formatting for easier reading
		addMessage(tempString);
	}
	
	public void addMessage(String newMessage) {
		messages.add(newMessage);
		if(messages.size() > maxSize) {
			messages.remove(0);
		}
		roundHeaderAddedLast = false;
	}
	
	public String battlePromptAsString() {
		String totalBattleMessage = new String();
		int numMessages = messages.size();
		for(int i = 0; i<maxSize & i<numMessages; i++) {
			if(i == numMessages-1 & roundHeaderAddedLast) {
				return totalBattleMessage; //don't want to include the round header if it is last
			}
			totalBattleMessage += messages.get(i);
			totalBattleMessage += "\n";
		}
		return totalBattleMessage;
	}
	
	public void addRoundHeader(int round) {
		addMessage("");
		addMessage("Round " + round);
		roundHeaderAddedLast = true;
	}
	
	public void creatureWon(BattleCreature creature) {
		addMessage(creature.getTitle() + " WON!");
	}
	
}

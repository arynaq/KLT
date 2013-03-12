package quests;

import items.QuestItem;

public class Quest {

	private QuestItem item;
	private String name;
	private String[] text;
	private int levelRequired;
	
	public Quest(String name, QuestItem item, String[] text) {
		this.item = item;
		this.name = name;
		this.text = text;

	}
	
}

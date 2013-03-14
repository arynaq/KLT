package items;

public class QuestItem extends GameItem {
	
	public QuestItem(){
		type = "QST";
		id = type + (++GameItem.nextID);
	}

}

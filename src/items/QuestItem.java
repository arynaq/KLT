package items;

public class QuestItem extends GameItem {
	
	public QuestItem(){
		type = "QST";
		id = type + (++GameItem.nextID);
	}

    @Override
    public int compareTo(GameItem o) {
        // TODO Auto-generated method stub
        return 0;
    }

}

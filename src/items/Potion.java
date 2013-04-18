package items;

public class Potion {
	private char type;
	private int value;

	public Potion(char type, int value) {
		this.type = type;
		this.value = value;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}

package items;

public class Potion implements Comparable<Potion> {
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

    @Override
    public int compareTo(Potion o) {
        if (this.getValue() == o.getValue())
            return 0;
        else if (this.getValue() < o.getValue())
            return -1;
        else
            return 1;

    }

}

package sfx;

public interface GameSound {
	void play();

	void stop();

	void reset();

	public enum SOUNDS {
		ATTACK
	}
}

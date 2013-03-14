package characters;

public interface Combatable {
	
	void doDamage(int damage, GameCharacter gc);

	void takeDamage(int damage, GameCharacter gc);

}

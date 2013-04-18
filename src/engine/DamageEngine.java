package engine;

public class DamageEngine {
	private int minDmg;
	private int maxDmg;
	private int dealDmg;
	private double critChance = 0.15;
	private boolean isCrit = false;

	/**
	 * Simple damage calculations, crit deals 2x dmg while normal attack is
	 * between min and max.
	 * 
	 * @param dmg
	 */
	public void calculateDamage(int dmg) {
		minDmg = dmg;
		maxDmg = dmg * 3;
		if (Math.random() < critChance) {
			isCrit = true;
			dealDmg = maxDmg * 2;
		} else {
			dealDmg = minDmg + (int) (Math.random() * ((maxDmg - minDmg) + 1));
			isCrit = false;
		}
		if (isCrit == true) {
			System.out.println("Crit: " + dealDmg + ".");
		} else {
			System.out.println("Dmg: " + dmg + "." + " DealDmg: " + dealDmg
					+ ".");
		}
	}
}

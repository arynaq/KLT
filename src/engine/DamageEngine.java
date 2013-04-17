package engine;

public class DamageEngine {
	private int minDmg;
	private int maxDmg;
	private int dealDmg;
	private double critChance = 0.1;
	private boolean isCrit = false;

	public void calculateDamage(int dmg) {
		minDmg = dmg;
		maxDmg = dmg * 3;
		if (Math.random() < critChance) {
			isCrit = true;
			dealDmg = maxDmg * 2;
		} else {
			dealDmg = (int) Math.round((Math.random() * maxDmg));
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


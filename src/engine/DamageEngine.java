package engine;

public class DamageEngine {
    private int minDmg;
    private int maxDmg;
    private int dealDmg;
    private double critChance = 0.15;
    private boolean isCrit = false;

    public DamageEngine() {
        System.out.println("DamageEngine loaded.");
    }

    /**
     * Simple damage calculations, crit deals 2x dmg while normal attack is
     * between min and max.
     * 
     * @param dmg
     * @return
     */
    public int calculateDamage(int dmg) {
        isCrit = false;
        minDmg = dmg;
        maxDmg = dmg * 2;
        dealDmg = minDmg + (int) (Math.random() * ((maxDmg - minDmg) + 1));
        if (Math.random() < critChance) {
            isCrit = true;
            dealDmg *=2;
        }
        
        return dealDmg;
    }

    public boolean isCrit() {
        return isCrit;
    }
}


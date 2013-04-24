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
     * @return
     */
    public int calculateDamage(int dmg) {
        minDmg = dmg;
        maxDmg = dmg * 2;
        if (Math.random() < critChance) {
            isCrit = true;
        } else {
            dealDmg = minDmg + (int) (Math.random() * ((maxDmg - minDmg) + 1));
            if (isCrit) {
                // System.out.println("============");
                // System.out.println("Was going to do dmg: " + dealDmg);
                // dealDmg *= 2;
                // System.out.println("But got a crit: " + dealDmg);
                // System.out.println("============");
                isCrit = false;
            }

            // isCrit = false;
        }
        // if (isCrit == true) {
        // System.out.println("Crit: " + dealDmg + ".");
        // } else {
        // // System.out.println("Dmg: " + dmg + "." + " DealDmg: " + dealDmg
        // // + ".");
        // }
        return dealDmg;
    }

    public boolean isCrit() {
        return isCrit;
    }
}


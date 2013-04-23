package characters;
/**
	 * Level class contains hp, required XP for level up and the damage level of
	 * the player.
	 * 
	 * @author frela
	 * 
	 */
	public class Level {
		private int hp;
		private int reqXP;
		private int dmg;
		private int level;

		public Level(int hp, int reqXP, int dmg, int level) {
			this.hp = hp;
			this.reqXP = reqXP;
			this.dmg = dmg;
			this.level = level;
		}

        public Level(int i) {
        this(10, 0, 1, 0);
        }

        public int getHP() {
			return hp;
		}

		public void setHP(int hp) {
			this.hp = hp;
		}

		public int getDmg() {
			return dmg;
		}

		public void setDmg(int dmg) {
			this.dmg = dmg;
		}

		public int getReqXP() {
			return reqXP;
		}

		public void setReqXP(int reqXP) {
			this.reqXP = reqXP;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}
	}
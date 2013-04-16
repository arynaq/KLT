package engine;

public enum GameInput {
	QUIT, LOAD, SAVE, MENU;
	/**
	 * An enum to hold the four cardinal directions. Movement is only allowed
	 * along these directions, no diagonal movement. Also used to represent the
	 * direction characters face.
	 * 
	 * @author aryann
	 * 
	 */
	public enum Movement{
		UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);
		private int dx;
		private int dy;

		Movement(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}

		public int getDX() {
			return dx;
		}

		public int getDY() {
			return dy;
		}
	}
}

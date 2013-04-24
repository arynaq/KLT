package characters;

import engine.GameInput.Movement;
import engine.GameState;
import gfx.Animated;
import gfx.AttackMoveAnimated;
import gfx.Renderable;

public class HumanoidEnemy extends BaseEnemy {
    private Animated moveWest;
    private Animated moveEast;
    private Animated moveSouth;
    private Animated moveNorth;
    private Animated currentRenderable;
    private int oldX, oldY;
    private boolean doMove;


    public HumanoidEnemy(AttackMoveAnimated animation, int x, int y,
            int health, int damage,
 int attackCooldown, int xp) {
        super(x, y, animation.getSheeet().getFrame(0).getWidth(), animation
                .getSheeet().getFrame(0).getHeight(), damage, health, xp, 0,
                attackCooldown);

        this.moveWest = animation.getWestMovementSheet();
        this.moveEast = animation.getEastMovementSheet();
        this.moveSouth = animation.getSouthMovementSheet();
        this.moveNorth = animation.getNorthMovementSheet();
        this.currentRenderable = moveEast;

    }

    @Override
    public void seek(Combatable other) {
        // TODO Auto-generated method stub

    }

    @Override
    public Renderable getRenderable() {
        if (!(GameState.getInstance().isInCurrentMap(this))) {
            currentRenderable.setDoNotRender(true);
            return currentRenderable;
        }
        else {
            currentRenderable.setDoNotRender(false);
        }

        if (getState() == State.DEAD) {
            currentRenderable.setDoNotRender(true);
            return currentRenderable;
        }





        if (getX() != oldX || getY() != oldY)
            doMove = true;
        else
            doMove = false;
        oldX = getX();
        oldY = getY();

        if (getFacing() == Movement.LEFT)
            currentRenderable = moveWest;
        else if (getFacing() == Movement.RIGHT)
            currentRenderable = moveEast;
        else if (getFacing() == Movement.UP)
            currentRenderable = moveNorth;
        else if (getFacing() == Movement.DOWN)
            currentRenderable = moveSouth;

        if (doMove)
            currentRenderable.move(GameState.PLAYERSTEPS);

        updateCurrentRenderable();
        return currentRenderable;
    }

    private void updateCurrentRenderable() {
        currentRenderable.setX(getX() % GameState.DIMENSION.width);
        currentRenderable.setY(getY() % GameState.DIMENSION.height);
    }

}

package sprites;
import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;
import game.Game;
import geometry.Velocity;

import java.awt.Color;

/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */

public class Ball implements Sprite {
    private Point center;
    private final int radius;

    private Velocity velocity;
    private java.awt.Color color;
    private int topBoundary = 600;

    private int bottomBoundary = 50;
    private GameEnvironment gameEnv;
    private final int ONE_STEP = 6;

    private int flag = 0;


    /**
     * constructor.
     *
     * @param center the center of the ball.
     * @param r      the radius if the ball.
     * @param color  the color of the ball.
     */

    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnv = new GameEnvironment();
        this.velocity = new Velocity(2.5, 2.5);
        this.topBoundary = topBoundary - radius;
        this.bottomBoundary = bottomBoundary + radius;

    }

    /**
     * constructor.
     *
     * @param center         the center point of the ball
     * @param radius         the ball radius
     * @param velocity       the ball velocity
     * @param color          the ball color
     * @param topBoundary    top boundary of the screen.
     * @param bottomBoundary bottom boundary of the screen
     * @param gameEnv        game environment.
     */
    public Ball(Point center, int radius, Velocity velocity, Color color,
                int topBoundary, int bottomBoundary, GameEnvironment gameEnv) {
        this.center = center;
        this.radius = radius;
        this.velocity = velocity;
        this.color = color;
        this.topBoundary = topBoundary - radius;
        this.bottomBoundary = bottomBoundary + radius;
        this.gameEnv = gameEnv;

    }

    /**
     * timepassed is the heart of the ball class,it calls moveOnestep method which is the,
     * method that moves the ball.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * constructor.
     *
     * @param x     the x determinant center of the ball.
     * @param y     the y determinant center of the ball.
     * @param r     the radius if the ball.
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.gameEnv = new GameEnvironment();
        this.velocity = new Velocity(0, -4);
        this.topBoundary = topBoundary - radius;
        this.bottomBoundary = bottomBoundary + radius;

    }

    /**
     * get the x center of the ball.
     *
     * @return x value of the center point.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * get the y center of the ball.
     *
     * @return y val.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * get the ball size.
     *
     * @return the radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * get the velocity of the ball.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * set the velocity of the ball.
     * @param dx which is the x vector value.
     * @param dy which is the y vector value.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * setGameEnvironment.
     * this method will add the game environment to the ball members.
     * @param gameEnv the game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnv) {
        this.gameEnv = gameEnv;
    }

    /**
     * set the color of the ball.
     * @return
     */
   public void setColor(Color color) {
        this.color = color;
    }


    /**
     * draws the surface.
     * @param surface which is the surface
     */
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * addToGame.
     * this method will add the ball to the game sprite list.
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
    /**
     * removeFromGame.
     * this function is called when the ball need to be removed from thhe game i.e when he hits,
     * the death region.
     * @param g the game.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
    /**
     * moveOneStep.
     * this function will move the ball one step,will change is velocity and fix his location,
     * if needed.
     * moves the ball one step.
     */
    public void moveOneStep() {
        Velocity originalVelocity = this.velocity;
        double x = center.getX();
        double y = getY();
        Point tmp = new Point(x, y);
        Line lineTrajectory = new Line(tmp, this.velocity.applyToPoint(tmp));
        CollisionInfo cInfo = this.gameEnv.getClosestCollision(lineTrajectory);
        if (cInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            this.flag = this.flag - 1;
        } else {
            if (this.gameEnv != null && !this.gameEnv.isEmpty()) {
                if (cInfo.collisionPoint().getX()
                        == cInfo.collisionObject().getCollisionRectangle().getUpperLeft().getX()) {
                    this.center = (new Point(cInfo.collisionPoint().getX() - ONE_STEP, this.getY() + velocity.getDy()));
                    //collision with the right side of the block.
                } else if (cInfo.collisionPoint().getX()
                        == cInfo.collisionObject().getCollisionRectangle().getUpperLeft().getX()
                        + cInfo.collisionObject().getCollisionRectangle().getWidth()) {
                    this.center = (new Point(cInfo.collisionPoint().getX() + ONE_STEP,
                            this.getY() + velocity.getDy())); //collision with the top side of the block.
                } else if (cInfo.collisionPoint().getY()
                        == cInfo.collisionObject().getCollisionRectangle().getUpperLeft().getY()) {
                    this.center = (new Point(this.center.getX() + velocity.getDx(),
                            cInfo.collisionPoint().getY() - ONE_STEP));
                } else if (cInfo.collisionPoint().getY()
                        == cInfo.collisionObject().getCollisionRectangle().getUpperLeft().getY()
                        + cInfo.collisionObject().getCollisionRectangle().getHeight()) {
                    this.center = (new Point(this.center.getX() + velocity.getDx(),
                            cInfo.collisionPoint().getY() + ONE_STEP));
                }
                this.velocity = cInfo.collisionObject().hit(this, cInfo.collisionPoint(), originalVelocity);
            }
        }
    }
}



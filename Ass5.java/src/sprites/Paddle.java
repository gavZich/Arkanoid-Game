package sprites;

import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Game;
import geometry.Velocity;

import java.awt.Color;

/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */
public class Paddle implements Collidable, Sprite {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    static final double SCREEN_WIDTH = 800;
    static final double SCREEN_HEIGHT = 600;
    static final double PADDLE_WIDTH = 100;
    static final double BLOCK_HEIGHT = 20;

    /**
     * constructor.
     * @param keyboard the user's key presses
     */
    public Paddle(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.rect = new Rectangle(
                new Point((SCREEN_WIDTH / 2) - (PADDLE_WIDTH / 2), SCREEN_HEIGHT - 40),
                PADDLE_WIDTH,
                BLOCK_HEIGHT);
    }

    /**
     * moveLeft.
     * Move the sprites.Paddle left.
     */
    public void moveLeft() {
        this.rect = new Rectangle(new Point(this.rect.getUpperLeft().getX() - 4,
                this.rect.getUpperLeft().getY()), this.rect.getWidth(), this.rect.getHeight());
    }

    /**
     * getPaddleWidth.
     * gets the paddle width.
     * @return the paddle with.
     */
    public double getPaddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * getScreenWidth.
     * gets the screen width.
     * @return the screen width.
     */
    public double getScreenWidth() {
        return SCREEN_WIDTH;
    }


    // Set the rectangle of the paddle.
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }


    /**
     * moveRight.
     * Move the sprites.Paddle to the right.
     */
    public void moveRight() {
        this.rect = new Rectangle(new Point(this.rect.getUpperLeft().getX() + 4,
                this.rect.getUpperLeft().getY()), this.rect.getWidth(), this.rect.getHeight());
    }

    // Get the rectangle of the paddle.
    public Rectangle getRect() {
        return this.rect;
    }
    /**
     * checks if paddle is out of boundary.
     * @return boolean value,depends on the paddle location.
     */
    public void Sliding() {
        double paddleStartX = this.rect.getUpperLeft().getX();
        double paddleEndX = this.rect.getUpperLeft().getX() + this.rect.getWidth();
        if (paddleEndX >= 780){
            setRect(new Rectangle(new Point(20, 560), PADDLE_WIDTH, BLOCK_HEIGHT));
        }
        if (paddleStartX <= 20){
            setRect(new Rectangle(new Point(680, 560), PADDLE_WIDTH, BLOCK_HEIGHT));
        }
    }
   

    /**
     * timePassed.
     * moves the paddle to the diraction the user wants.
     */
     public void timePassed(){
        // if the left key is pressed, move the paddle to the left
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            Sliding();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
            Sliding();
        }
    }
    
    /**
     * Draw the paddle on a given GUI surface.
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.getCollisionRectangle().getUpperLeft().getX();
        int y = (int) this.getCollisionRectangle().getUpperLeft().getY();
        int width = (int) this.getCollisionRectangle().getWidth();
        int height = (int) this.getCollisionRectangle().getHeight();
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }
    /**
     * getCollisionRectangle.
     * Gets the sprites.Paddle Geometry.Rectangle object.
     * @return the Geometry.Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    /**
     * hit.
     * @param collisionPoint  the collision point with the sprites.Ball
     * @param currentVelocity the sprites.Ball's current geometry.Velocity
     * @param hitter the ball that hit the paddle.
     * @return the new geometry.Velocity that the sprites.Ball will have
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double colX = collisionPoint.getX();
        double colY = collisionPoint.getY();
        double upLeftX = rect.getUpperLeft().getX();
        double upLeftY = rect.getUpperLeft().getY();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (colY == rect.getUpperLeft().getY()) {                                         // sprites.Ball hits sprites.Paddle's top
            return regionVelocity(colX, currentVelocity);
        }
        if (colY >= upLeftY
                && colY == upLeftY + rect.getWidth()) {                               // sprites.Ball hits sprites.Paddle's bottom
            dy = -dy;
        } else if ((colX >= upLeftX
                && colX <= upLeftX + (rect.getWidth() / 2)
                && dx > 0)                                                          // sprites.Ball hits sprites.Paddle's left side
                || (colX >= upLeftX + (rect.getWidth() / 2)
                && colX <= upLeftX + rect.getWidth()
                && dx < 0)) {                                                       // sprites.Ball hits sprites.Paddle's right side
            dx = -dx;
        }
        return new Velocity(dx, dy);
    }
    /**
     * Add this sprites.Paddle to a given game.Game.
     * @param g the game.Game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Changes the sprites.Ball's geometry.Velocity based on which part of the sprites.Paddle it hit.
     * @param colX the X coordinate of the collision Geometry.Point.
     * @param currentVelocity the sprites.Ball's current velocity
     * @return the sprites.Ball's new geometry.Velocity
     */
    private Velocity regionVelocity(double colX, Velocity currentVelocity) {
        double upLeftX = rect.getUpperLeft().getX();
        int regionNum = 1;
        while (regionNum <= 5) {
            // if the collision is within the current region
            if (colX > upLeftX + ((PADDLE_WIDTH / 5) * (regionNum - 1))
                    && colX < upLeftX + ((PADDLE_WIDTH / 5) * regionNum)) {
                break;
            }
            ++regionNum;
        }
        double speed = currentVelocity.getSpeed();
        /* The angles are to offset the calculation done by the radians.
         * The actual angles are 300, 330, 60 and 30 from first to last */
        switch (regionNum) {
            case 1:
                return Velocity.fromAngleAndSpeed(210, speed);
            case 2:
                return Velocity.fromAngleAndSpeed(240, speed);
            case 4:
                return Velocity.fromAngleAndSpeed(300, speed);
            case 5:
                return Velocity.fromAngleAndSpeed(330, speed);
            default:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }
        private Velocity changeAngleOfBall(Point collisionPoint, Velocity currVel) {
        double vectorChange;
        double hitXInBetween = collisionPoint.getX() - rect.getUpperLeft().getX();
        if (0 <= hitXInBetween && hitXInBetween <= 10) {
            vectorChange = Math.toDegrees(Math.sin(300));
        } else if (10 < hitXInBetween && hitXInBetween <= 20) {
            vectorChange = Math.toDegrees(Math.sin(330));
        } else if (20 < hitXInBetween && hitXInBetween <= 30) {
            vectorChange  = -1;
        } else if (30 < hitXInBetween && hitXInBetween <= 40) {
            vectorChange = Math.toDegrees(Math.sin(30));
        } else {
            vectorChange = Math.toDegrees(Math.sin(60));
        }
        return new Velocity(currVel.getDx() * vectorChange, currVel.getDy() * -1);
    }

}

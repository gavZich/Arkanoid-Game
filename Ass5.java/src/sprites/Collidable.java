package sprites;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return Geometry.Rectangle the collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit.
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param hitter the ball that hited the object.
     * @param collisionPoint the exact point of collision.
     * @param currentVelocity the ball current velocity.
     * @return geometry.Velocity, the new ball velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
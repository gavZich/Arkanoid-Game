package sprites;

import geometry.Point;


/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */

public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * constructor.
     * @param collisionPoint the collision point of the event.
     * @param collisionObject the object that got collided.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * collisionPoint.
     * getter, returns the class object point member.
     * @return Geometry.Point the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * collisionObject.
     * @return sprites.Collidable,he sprites.Collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
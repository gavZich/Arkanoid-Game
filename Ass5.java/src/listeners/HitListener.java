package listeners;
import sprites.Ball;
import sprites.Block;

/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the sprites.Ball that's doing the hitting.
     * @param beingHit the collidable that being hhit
     * @param hitter the ball that hit the object.
     */

    void hitEvent(Block beingHit, Ball hitter);
}
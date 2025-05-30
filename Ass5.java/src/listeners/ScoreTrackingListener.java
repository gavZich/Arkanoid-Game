package listeners;

import sprites.Ball;
import sprites.Block;
import sprites.Counter;

/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter the counter that keeps the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * gets the currentScore.
     * @return currentScore.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * hitEvent.
     * this method will increase the score when block is being hit.
     * @param beingHit the collidable that being hhit
     * @param hitter the ball that hit the object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
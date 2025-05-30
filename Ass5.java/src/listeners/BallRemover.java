package listeners;

import game.Game;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;

/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */

public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * constructor.
     * @param game the game.
     * @param counter counter class in charge of simple math operators.
     */
    public BallRemover(Game game, Counter counter) {
        this.game = game;
        this.remainingBalls = counter;
    }

    /**
     * getRemainingBalls.
     * getter methods returning the class counter memeber.
     * @return the class counter member.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}

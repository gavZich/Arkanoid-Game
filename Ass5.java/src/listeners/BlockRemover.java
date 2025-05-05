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
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * constructor.
     * @param game the game object.
     * @param remainingBlocks the blocks counter.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * getRemainingBlocks.
     * return the object counter.
     * @return counter the object counter.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * getGame.
     * returns the class game member.
     * @return game.Game. the game member.
     */
    public Game getGame() {
        return game;
    }
    /**
     * hitEvent.
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit the block that was hit.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        game.getScoreTrack().hitEvent(beingHit, hitter);
        this.remainingBlocks.decrease(1);
        if (game.getGameSprites().containsKey(beingHit.getColor())) {
            Integer numOfColor = game.getGameSprites().get(beingHit.getColor());
            game.getGameSprites().put(beingHit.getColor(), numOfColor - 1);
            if (game.getGameSprites().get(beingHit.getColor()) == 0) {
                game.getScoreTrack().getCurrentScore().increase(100);
            }
        }
    }
}

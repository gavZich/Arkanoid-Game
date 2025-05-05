package sprites;

import biuoop.DrawSurface;
import game.Game;
import listeners.ScoreTrackingListener;
import sprites.Block;

import java.awt.Color;
/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */
public class ShowingScoreBlock implements Sprite {
    private final int xLocation = 350;
    private final int yLocation = 20;
    private final Block block;
    private final ScoreTrackingListener scoreTrack;

    /**
     * Constructor.
     * @param block base block.
     * @param s listeners.ScoreTrackingListener that contains the scoreTracker
     */
    public  ShowingScoreBlock(Block block, ScoreTrackingListener s) {
        this.block = block;
        this.scoreTrack = s;
    }
    /**
     * addToGame.
     * adds the block to the game.
     * @param g the game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * gets the class block.
     * @return sprites.Block the class block.
     */
    public Block getBlock() {
        return block;
    }
    /**
     * drawOn.
     * draw the block on the surface.
     * @param d the surface given.
     */
    @Override
    public void drawOn(DrawSurface d) {
        String score = String.valueOf(scoreTrack.getCurrentScore().getValue());
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 1, 800, yLocation);
        d.setColor(Color.BLACK);
        d.drawText(xLocation, yLocation - 1, "Score :" + score, 15);
    }

    /**
     * timePassed.
     * does nothing there is other method that deal with it.
     */
    @Override
    public void timePassed() {

    }
}

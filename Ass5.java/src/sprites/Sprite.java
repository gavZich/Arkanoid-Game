package sprites;

import biuoop.DrawSurface;
/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */
public interface Sprite {
    /**
     * drawOn.
     * draw the sprite to the screen
     * @param d the surface.
     */
    void drawOn(DrawSurface d);
    /**
     * timePassed.
     * notify the sprite that time has passed
     */
    void timePassed();
}
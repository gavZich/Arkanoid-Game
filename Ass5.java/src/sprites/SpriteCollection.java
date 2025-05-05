package sprites;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * constructor.
     * @param sprite list of Sprites
     */
    public SpriteCollection(Sprite sprite) {
        this.sprites = new ArrayList<>();
        sprites.add(sprite);
    }

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * addSprite.
     * add sprites.Sprite to the list.
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * notifyAllTimePassed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> tmpSprites = new ArrayList<>(this.sprites);
        for (Sprite s : tmpSprites) {
            s.timePassed();
        }
    }
    /**
     * remove sprite from spriteCollection.
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
            sprites.remove(s);
    }

    /**
     * drawAllOn.
     * draws the sprites on the surface.
     * @param d the surface.
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
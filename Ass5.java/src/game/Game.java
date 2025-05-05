package game;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import listeners.BallRemover;
import listeners.BlockRemover;
import geometry.Rectangle;
import geometry.Point;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Collidable;
import sprites.Counter;
import sprites.GameEnvironment;
import sprites.Sprite;
import sprites.Paddle;
import sprites.ShowingScoreBlock;
import sprites.SpriteCollection;
import java.awt.Color;
import java.util.HashMap;
/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    static final int BLOCK_WIDTH = 50;
    static final int BLOCK_HEIGHT = 20;
    static final int RIGHT_BOARDER = 780;
    private BallRemover ballRemover;
    private BlockRemover blockRemovers;
    private ScoreTrackingListener scoreTrack;
    private HashMap<Color, Integer> gameSprites;
    /**
     * constructor.
     */
    public Game() {

    }
    /**
     * addCollidable.
     * adds colliable to the game environment.
     *
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * addSprite.
     * add sprite to the sprite collection.
     *
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * getGameSprites, gets the game hashmap sprites sorted by their color and values.
     *
     * @return map with color keys and the number of sprites having this color.
     */
    public HashMap<Color, Integer> getGameSprites() {
        return gameSprites;
    }
    /**
     * getScoreTrack.
     * gets thh scoreTracker of the game.
     *
     * @return listeners.ScoreTrackingListener, the scoreTracker of the game.
     */
    public ScoreTrackingListener getScoreTrack() {
        return scoreTrack;
    }
    /**
     * initialize.
     * Initialize a new game: create the Blocks and sprites.Ball (and sprites.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        sleeper = new Sleeper();
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockRemovers = new BlockRemover(this, new Counter());
        ballRemover = new BallRemover(this, new Counter());
        scoreTrack = new ScoreTrackingListener(new Counter());
        gameSprites = new HashMap<>();
        Paddle paddle = new Paddle(gui.getKeyboardSensor());
        paddle.addToGame(this);
        createBlocks();
        ballsFactory(3);
        createScreenBoundary();
    }
    /**
     * ballsFactory.
     * this method creates balls.
     *
     * @param numOfBalls int, the number of ball wanted.
     */
    public void ballsFactory(int numOfBalls) {
        for (int i = 0; i < numOfBalls; i++) {
            Ball ball = new Ball(350 + 10 * i, 500, 4, Color.BLACK);
            ball.addToGame(this);
            ballRemover.getRemainingBalls().increase(1);
            ball.setGameEnvironment(environment);
        }
    }
    /**
     * createScreenBoundary.
     * this method creates the screen block boundary, the upper block will show the current score,
     * the lower will be called death region meaning that if will remove any ball that hits it,by notify
     * the ball remover.
     * also it will add the block to the game sprites.
     */
    public void createScreenBoundary() {
        Rectangle rectangle = new Rectangle(new Point(0, 20), 800, 20);
        Block scoreShowingRect = new Block(rectangle, java.awt.Color.gray);
        Rectangle rectangle2 = new Rectangle(new Point(0, 20), 20, 600);
        Block block2 = new Block(rectangle2, Color.gray);
        Rectangle rectangle3 = new Rectangle(new Point(780, 20), 20, 600);
        Block block3 = new Block(rectangle3, Color.gray);
        Rectangle rectangle4 = new Rectangle(new Point(0, 598), 800, 20);
        Block deathRegion = new Block(rectangle4, Color.gray);
        deathRegion.addHitListener(this.ballRemover);
        Block[] blocks = new Block[]{scoreShowingRect, deathRegion, block2, block3};
        for (Block b : blocks) {
            b.addToGame(this);
        }
        ShowingScoreBlock sh = new ShowingScoreBlock(scoreShowingRect, this.scoreTrack);
        sh.addToGame(this);
    }
    /**
     * createBlocks.
     * this method will create the game blocks, will add them to the game sprites,
     * will increase the counter of the blocks.
     */
    public void createBlocks() {
        java.awt.Color[] colors = {Color.gray, Color.red, Color.yellow, Color.blue, Color.pink, Color.WHITE};
        int startHeight = 100;
        for (int i = 0; i < 6; i++) {
            gameSprites.put(colors[i], 12 - i);
            for (int j = 12 - i; j > 0; j--) {
                Point p = new Point(RIGHT_BOARDER - BLOCK_WIDTH * j, startHeight + BLOCK_HEIGHT * i);
                Rectangle rect = new Rectangle(p, BLOCK_WIDTH, BLOCK_HEIGHT);
                Block b = new Block(rect, colors[i]);
                b.addToGame(this);
                b.addHitListener(blockRemovers);
                blockRemovers.getRemainingBlocks().increase(1);
            }
        }
    }
    /**
     * run.
     * Run the game -- start the animation loop.
     */
    public void run() {
        boolean flag = false;
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.DARK_GRAY);
            d.fillRectangle(0, 0, 800, 700);
            this.sprites.drawAllOn(d);
            gui.show(d);
            if (flag) {
                return;
            }
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.ballRemover.getRemainingBalls().getValue() == 0) {
                flag = true;
            }
        }
    }
    /**
     * removeCollidable.
     * this method will remove the coliidable from game environment.
     *
     * @param c the collidable need to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * removes the sprite from sprite Collection.
     *
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}


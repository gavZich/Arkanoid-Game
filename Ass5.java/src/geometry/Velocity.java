package geometry;

/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx x speed value
     * @param dy y speed value
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * converts speed and angle to their axe's vectors.
     * @param angle the angle
     * @param speed the speed
     * @return velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleInRad = Math.toRadians(angle);
        double dx;
        double dy;
        dx = Math.cos(angleInRad) * speed;
        dy = Math.sin(angleInRad) * speed;

        return new Velocity(dx, dy);
    }

    /**
     * get velocity dx.
     * @return dx value
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * gets velocity dy value.
     * @return dy value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p the point
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * getSpeed.
     * gets the total speed vector.
     * @return double speed.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Gets the angle of the vector in degrees.
     *
     * @return the angle
     */
    public double getAngle() {
        /* Math.acos returns Radians */
        return Math.toDegrees(Math.acos(dx / this.getSpeed()));
    }
}

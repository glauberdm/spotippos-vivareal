package br.com.vivareal.spotippos.domain;

/**
 * Boudaries with upperLeft and bottomRight points coordinates.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public class Boundaries {

    private Coordinate upperLeft;
    private Coordinate bottomRight;

    public Coordinate getUpperLeft() {
        return upperLeft;
    }

    public void setUpperLeft(Coordinate upperLeft) {
        this.upperLeft = upperLeft;
    }

    public Coordinate getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Coordinate bottomRight) {
        this.bottomRight = bottomRight;
    }
}

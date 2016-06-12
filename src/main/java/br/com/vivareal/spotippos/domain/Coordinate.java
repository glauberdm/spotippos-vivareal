package br.com.vivareal.spotippos.domain;

/**
 * x and y points coordinates.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public class Coordinate {

    private Integer x;
    private Integer y;

    public Coordinate() {
    }

    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}

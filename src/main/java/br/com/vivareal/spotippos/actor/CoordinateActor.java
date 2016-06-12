package br.com.vivareal.spotippos.actor;

import br.com.vivareal.spotippos.domain.Coordinate;
import org.springframework.stereotype.Component;

/**
 * Actor for {@link Coordinate}
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@Component
public class CoordinateActor {

    /**
     * Verify if {@link Coordinate} point is present in a and b {@link Coordinate}s of rectangle.
     *
     * @param point {@link Coordinate} for point location for verify.
     * @param a     {@link Coordinate} for upperLeft point of rectangle.
     * @param b     {@link Coordinate} for bottomRight point of rectangle.
     * @return
     */
    public boolean pointInPointsRectagle(Coordinate point, Coordinate a, Coordinate b) {
        return point.getX() > a.getX() && point.getY() < a.getY() && point.getX() < b.getX() && point.getY() > b.getY();
    }
}

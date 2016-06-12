package br.com.vivareal.spotippos.actor;

import br.com.vivareal.spotippos.Application;
import br.com.vivareal.spotippos.domain.Coordinate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link CoordinateActor}.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class CoordinateActorTest {

    @Autowired
    private CoordinateActor coordinateActor;

    @Test
    public void pointInPointsRectagle() {
        Coordinate point = mock(Coordinate.class);
        when(point.getX()).thenReturn(550);
        when(point.getY()).thenReturn(550);

        Coordinate a = mock(Coordinate.class);
        when(a.getX()).thenReturn(0);
        when(a.getY()).thenReturn(600);

        Coordinate b = mock(Coordinate.class);
        when(b.getX()).thenReturn(600);
        when(b.getY()).thenReturn(500);


        boolean pointInCoordinates = coordinateActor.pointInPointsRectagle(point, a, b);


        assertTrue(pointInCoordinates);
    }

    @Test
    public void pontNotInPointsRectagle() {
        Coordinate point = mock(Coordinate.class);
        when(point.getX()).thenReturn(200);
        when(point.getY()).thenReturn(300);

        Coordinate a = mock(Coordinate.class);
        when(a.getX()).thenReturn(0);
        when(a.getY()).thenReturn(600);

        Coordinate b = mock(Coordinate.class);
        when(b.getX()).thenReturn(600);
        when(b.getY()).thenReturn(500);


        boolean pointInCoordinates = coordinateActor.pointInPointsRectagle(point, a, b);


        assertFalse(pointInCoordinates);
    }
}
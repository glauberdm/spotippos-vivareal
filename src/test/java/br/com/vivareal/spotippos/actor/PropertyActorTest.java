package br.com.vivareal.spotippos.actor;

import br.com.vivareal.spotippos.Application;
import br.com.vivareal.spotippos.domain.Boundaries;
import br.com.vivareal.spotippos.domain.Coordinate;
import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.domain.Province;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link PropertyActor}.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PropertyActorTest {

    @Autowired
    private PropertyActor propertyActor;

    private Province provinceOne;
    private Province provinceTwo;

    @Before
    public void setup() {
        configureProvinceOne();
        configureProvinceTwo();
    }

    @Test
    public void testPropertyInProvinceOne() {
        Property property = mock(Property.class);
        when(property.getX()).thenReturn(550);
        when(property.getY()).thenReturn(550);

        boolean propertyInProvince = propertyActor.propertyInProvince(property, provinceOne);

        assertTrue(propertyInProvince);
    }

    @Test
    public void testPropertyInProvinceTwo() {
        Property property = mock(Property.class);
        when(property.getX()).thenReturn(550);
        when(property.getY()).thenReturn(550);

        boolean propertyInProvince = propertyActor.propertyInProvince(property, provinceTwo);

        assertTrue(propertyInProvince);
    }

    @Test
    public void testPropertyNotInProvinceOne() {
        Property property = mock(Property.class);
        when(property.getX()).thenReturn(200);
        when(property.getY()).thenReturn(300);

        boolean propertyInProvince = propertyActor.propertyInProvince(property, provinceOne);

        assertFalse(propertyInProvince);
    }

    @Test
    public void testProvinceGodeForPropertyActorTest() {
        Property property = mock(Property.class);
        when(property.getX()).thenReturn(200);
        when(property.getY()).thenReturn(550);

        List<String> provincesForProperty = propertyActor.provincesForProperty(property);

        assertThat(provincesForProperty, containsInAnyOrder("Gode"));
    }

    @Test
    public void testProvinceRujaForPropertyActorTest() {
        Property property = mock(Property.class);
        when(property.getX()).thenReturn(700);
        when(property.getY()).thenReturn(550);

        List<String> provincesForProperty = propertyActor.provincesForProperty(property);

        assertThat(provincesForProperty, containsInAnyOrder("Ruja"));
    }

    @Test
    public void testProvinceGodeAndRujaForPropertyActorTest() {
        Property property = mock(Property.class);
        when(property.getX()).thenReturn(550);
        when(property.getY()).thenReturn(550);

        List<String> provincesForProperty = propertyActor.provincesForProperty(property);

        assertThat(provincesForProperty, containsInAnyOrder("Gode", "Ruja"));
    }

    @Test
    public void propertyInCoordinates() {
        Property property = mock(Property.class);
        when(property.getX()).thenReturn(550);
        when(property.getY()).thenReturn(550);

        Coordinate coordinatePointA = mock(Coordinate.class);
        when(coordinatePointA.getX()).thenReturn(0);
        when(coordinatePointA.getY()).thenReturn(600);

        Coordinate coordinatePointB = mock(Coordinate.class);
        when(coordinatePointB.getX()).thenReturn(600);
        when(coordinatePointB.getY()).thenReturn(500);


        boolean propertyInCoordinates = propertyActor.propertyInAreaCoordinates(property, coordinatePointA, coordinatePointB);


        assertTrue(propertyInCoordinates);
    }

    @Test
    public void propertyNotInCoordinates() {
        Property property = mock(Property.class);
        when(property.getX()).thenReturn(200);
        when(property.getY()).thenReturn(300);

        Coordinate coordinatePointA = mock(Coordinate.class);
        when(coordinatePointA.getX()).thenReturn(0);
        when(coordinatePointA.getY()).thenReturn(600);

        Coordinate coordinatePointB = mock(Coordinate.class);
        when(coordinatePointB.getX()).thenReturn(600);
        when(coordinatePointB.getY()).thenReturn(500);


        boolean propertyInCoordinates = propertyActor.propertyInAreaCoordinates(property, coordinatePointA, coordinatePointB);


        assertFalse(propertyInCoordinates);
    }

    private void configureProvinceOne() {
        Boundaries boundaries = mock(Boundaries.class);
        Coordinate coordinateUpperLeft = mock(Coordinate.class);
        when(coordinateUpperLeft.getX()).thenReturn(0);
        when(coordinateUpperLeft.getY()).thenReturn(1000);
        Coordinate coordinateBottomRight = mock(Coordinate.class);
        when(coordinateBottomRight.getX()).thenReturn(600);
        when(coordinateBottomRight.getY()).thenReturn(500);
        when(boundaries.getUpperLeft()).thenReturn(coordinateUpperLeft);
        when(boundaries.getBottomRight()).thenReturn(coordinateBottomRight);

        provinceOne = mock(Province.class);
        when(provinceOne.getBoundaries()).thenReturn(boundaries);
    }

    private void configureProvinceTwo() {
        Boundaries boundaries = mock(Boundaries.class);
        Coordinate coordinateUpperLeft = mock(Coordinate.class);
        when(coordinateUpperLeft.getX()).thenReturn(400);
        when(coordinateUpperLeft.getY()).thenReturn(1000);
        Coordinate coordinateBottomRight = mock(Coordinate.class);
        when(coordinateBottomRight.getX()).thenReturn(1100);
        when(coordinateBottomRight.getY()).thenReturn(500);
        when(boundaries.getUpperLeft()).thenReturn(coordinateUpperLeft);
        when(boundaries.getBottomRight()).thenReturn(coordinateBottomRight);

        provinceTwo = mock(Province.class);
        when(provinceTwo.getBoundaries()).thenReturn(boundaries);
    }

}
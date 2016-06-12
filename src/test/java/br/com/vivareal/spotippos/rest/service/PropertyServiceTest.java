package br.com.vivareal.spotippos.rest.service;

import br.com.vivareal.spotippos.Application;
import br.com.vivareal.spotippos.domain.Coordinate;
import br.com.vivareal.spotippos.domain.Properties;
import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.rest.response.DataResponseEntity;
import br.com.vivareal.spotippos.rest.response.FoundPropertiesResponseEntity;
import br.com.vivareal.spotippos.rest.response.PropertyResponseEntity;
import br.com.vivareal.spotippos.rest.response.ResponseStatus;
import br.com.vivareal.spotippos.rest.response.exception.PropertyNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link PropertyService}.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PropertyServiceTest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private Properties properties;

    @Test
    public void createSuccess() {
        properties.setProperties(new ArrayList<>());
        properties.setTotalProperties(0);

        Property property = new Property(); //mock(Property.class);
        property.setTitle("Imóvel código 1, com 5 quartos e 4 banheiros");
        property.setX(222);
        property.setY(444);
        property.setPrice(new BigDecimal(1250000));
        property.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        property.setBeds(4);
        property.setBaths(3);
        property.setSquareMeters(210);


        ResponseEntity<PropertyResponseEntity> responseEntity = propertyService.create(property);


        PropertyResponseEntity propertyResponseEntity = responseEntity.getBody();
        assertThat(propertyResponseEntity.getStatus(), is(ResponseStatus.SUCCESS));
        assertThat(propertyResponseEntity.getData(), is(notNullValue()));
        assertThat(propertyResponseEntity.getData().getId(), is(1L));
        assertThat(propertyResponseEntity.getMessage(), is(PropertyService.SUCCESS_MESSAGE));
        assertThat(properties.getProperties(), contains(property));
        assertThat(properties.getTotalProperties(), is(1));
    }

    @Test
    public void createUnsuccessWithBedsGreaterThanMaximus() {
        properties.setProperties(new ArrayList<>());
        properties.setTotalProperties(0);

        Property property = mock(Property.class);
        when(property.getTitle()).thenReturn("Imóvel código 1, com 5 quartos e 4 banheiros");
        when(property.getX()).thenReturn(222);
        when(property.getY()).thenReturn(444);
        when(property.getPrice()).thenReturn(new BigDecimal(1250000));
        when(property.getDescription()).thenReturn("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        when(property.getBeds()).thenReturn(6);
        when(property.getBaths()).thenReturn(3);
        when(property.getSquareMeters()).thenReturn(210);


        ResponseEntity<PropertyResponseEntity> responseEntity = propertyService.create(property);


        PropertyResponseEntity propertyResponseEntity = responseEntity.getBody();
        assertThat(propertyResponseEntity.getStatus(), is(ResponseStatus.UNSUCCESS));
        assertThat(propertyResponseEntity.getData(), is(nullValue()));
        assertThat(propertyResponseEntity.getMessage(), is(PropertyService.UNSUCCESS_MESSAGE));
        assertThat(properties.getProperties(), empty());
        assertThat(properties.getTotalProperties(), is(0));
    }

    @Test
    public void createError() {
        properties.setProperties(new ArrayList<>());
        properties.setTotalProperties(0);

        Property property = null;


        ResponseEntity<PropertyResponseEntity> responseEntity = propertyService.create(property);


        PropertyResponseEntity propertyResponseEntity = responseEntity.getBody();
        assertThat(propertyResponseEntity.getStatus(), is(ResponseStatus.ERROR));
        assertThat(propertyResponseEntity.getData(), is(nullValue()));
        assertThat(propertyResponseEntity.getMessage(), is(PropertyService.ERROR_MESSAGE));
        assertThat(properties.getProperties(), empty());
        assertThat(properties.getTotalProperties(), is(0));
    }

    @Test
    public void findById() throws PropertyNotFoundException {
        Property propertyId1 = mock(Property.class);
        when(propertyId1.getId()).thenReturn(1L);
        Property propertyId2 = mock(Property.class);
        when(propertyId2.getId()).thenReturn(2L);
        Property propertyId3 = mock(Property.class);
        when(propertyId3.getId()).thenReturn(3L);

        properties.setProperties(asList(propertyId1, propertyId2, propertyId3));


        Property propertyId1Found = propertyService.findById(1L);


        assertThat(propertyId1Found.getId(), is(1L));
    }

    @Test(expected = PropertyNotFoundException.class)
    public void findByIdNotFound() throws PropertyNotFoundException {
        Property propertyId1 = mock(Property.class);
        when(propertyId1.getId()).thenReturn(1L);
        Property propertyId2 = mock(Property.class);
        when(propertyId2.getId()).thenReturn(2L);
        Property propertyId3 = mock(Property.class);
        when(propertyId3.getId()).thenReturn(3L);

        properties.setProperties(asList(propertyId1, propertyId2, propertyId3));


        Property propertyId4NotFound = propertyService.findById(4L);


        assertThat(propertyId4NotFound, is(nullValue()));
    }

    @Test
    public void propertiesByAreaCoordinates_ax0ay600bx600by400() {
        Coordinate coordinatePointA = mock(Coordinate.class);
        when(coordinatePointA.getX()).thenReturn(0);
        when(coordinatePointA.getY()).thenReturn(600);

        Coordinate coordinatePointB = mock(Coordinate.class);
        when(coordinatePointB.getX()).thenReturn(600);
        when(coordinatePointB.getY()).thenReturn(400);

        Property property1 = mock(Property.class);
        when(property1.getX()).thenReturn(550);
        when(property1.getY()).thenReturn(550);

        Property property2 = mock(Property.class);
        when(property2.getX()).thenReturn(200);
        when(property2.getY()).thenReturn(300);

        Property property3 = mock(Property.class);
        when(property3.getX()).thenReturn(450);
        when(property3.getY()).thenReturn(450);

        properties.setProperties(asList(property1, property2, property3));


        FoundPropertiesResponseEntity foundPropertiesResponseEntity = propertyService.propertiesByAreaCoordinates(coordinatePointA, coordinatePointB);


        assertThat(foundPropertiesResponseEntity.getFoundProperties(), is(2));
        assertThat(foundPropertiesResponseEntity.getProperties(), contains(property1, property3));
    }

    @Test
    public void propertiesByAreaCoordinates_ax0ay400bx300by100() {
        Coordinate coordinatePointA = mock(Coordinate.class);
        when(coordinatePointA.getX()).thenReturn(0);
        when(coordinatePointA.getY()).thenReturn(400);

        Coordinate coordinatePointB = mock(Coordinate.class);
        when(coordinatePointB.getX()).thenReturn(300);
        when(coordinatePointB.getY()).thenReturn(100);

        Property property1 = mock(Property.class);
        when(property1.getX()).thenReturn(550);
        when(property1.getY()).thenReturn(550);

        Property property2 = mock(Property.class);
        when(property2.getX()).thenReturn(200);
        when(property2.getY()).thenReturn(300);

        Property property3 = mock(Property.class);
        when(property3.getX()).thenReturn(450);
        when(property3.getY()).thenReturn(450);

        properties.setProperties(asList(property1, property2, property3));


        FoundPropertiesResponseEntity foundPropertiesResponseEntity = propertyService.propertiesByAreaCoordinates(coordinatePointA, coordinatePointB);


        assertThat(foundPropertiesResponseEntity.getFoundProperties(), is(1));
        assertThat(foundPropertiesResponseEntity.getProperties(), contains(property2));
    }

    @Test
    public void noPropertiesByAreaCoordinates_ax700ay700bx700by700() {
        Coordinate coordinatePointA = mock(Coordinate.class);
        when(coordinatePointA.getX()).thenReturn(700);
        when(coordinatePointA.getY()).thenReturn(700);

        Coordinate coordinatePointB = mock(Coordinate.class);
        when(coordinatePointB.getX()).thenReturn(700);
        when(coordinatePointB.getY()).thenReturn(700);

        Property property1 = mock(Property.class);
        when(property1.getX()).thenReturn(550);
        when(property1.getY()).thenReturn(550);

        Property property2 = mock(Property.class);
        when(property2.getX()).thenReturn(200);
        when(property2.getY()).thenReturn(300);

        Property property3 = mock(Property.class);
        when(property3.getX()).thenReturn(450);
        when(property3.getY()).thenReturn(450);

        properties.setProperties(asList(property1, property2, property3));


        FoundPropertiesResponseEntity foundPropertiesResponseEntity = propertyService.propertiesByAreaCoordinates(coordinatePointA, coordinatePointB);


        assertThat(foundPropertiesResponseEntity.getFoundProperties(), is(0));
        assertThat(foundPropertiesResponseEntity.getProperties(), empty());
    }
}

package br.com.vivareal.spotippos.domain.filter;

import br.com.vivareal.spotippos.Application;
import br.com.vivareal.spotippos.domain.Properties;
import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.domain.validator.PropertyValidator;
import br.com.vivareal.spotippos.domain.validator.property.PropertyBathsValidation;
import br.com.vivareal.spotippos.domain.validator.property.PropertyBedsValidation;
import br.com.vivareal.spotippos.domain.validator.property.PropertySquareMetersValidation;
import br.com.vivareal.spotippos.rest.service.PropertyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link PropertiesFilter}.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PropertiesFilterTest {

    @Autowired
    private Properties properties;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyBedsValidation propertyBedsValidator;

    @Autowired
    private PropertyBathsValidation propertyBathsValidator;

    @Autowired
    private PropertySquareMetersValidation propertySquareMetersValidator;

    @Autowired
    private PropertiesFilter propertiesFilter;

    @Test
    public void filerWithBedsValidator() {
        Property propertyWithBetweenMinMaxBeds = mock(Property.class);
        when(propertyWithBetweenMinMaxBeds.getBeds()).thenReturn(4);

        Property propertyWithGreaterThanMaxBeds = mock(Property.class);
        when(propertyWithGreaterThanMaxBeds.getBeds()).thenReturn(6);

        Property propertyWithLessThanMinBeds = mock(Property.class);
        when(propertyWithLessThanMinBeds.getBeds()).thenReturn(0);

        properties.setProperties(asList(propertyWithBetweenMinMaxBeds, propertyWithGreaterThanMaxBeds, propertyWithLessThanMinBeds));

        PropertyValidator propertyValidator = new PropertyValidator();
        propertyValidator.addPropertyValidator(propertyBedsValidator);
        ReflectionTestUtils.setField(propertiesFilter, "propertyValidator", propertyValidator);


        propertiesFilter.filter(properties);


        assertThat(properties.getProperties(), contains(propertyWithBetweenMinMaxBeds));
    }

    @Test
    public void filterWithBathsValidator() {
        Property propertyWithBetweenMinMaxBaths = mock(Property.class);
        when(propertyWithBetweenMinMaxBaths.getBaths()).thenReturn(4);

        Property propertyWithGreaterThanMaxBaths = mock(Property.class);
        when(propertyWithGreaterThanMaxBaths.getBaths()).thenReturn(5);

        Property propertyWithLessThanMinBaths = mock(Property.class);
        when(propertyWithLessThanMinBaths.getBaths()).thenReturn(0);

        properties.setProperties(asList(propertyWithBetweenMinMaxBaths, propertyWithGreaterThanMaxBaths, propertyWithLessThanMinBaths));

        PropertyValidator propertyValidator = new PropertyValidator();
        propertyValidator.addPropertyValidator(propertyBathsValidator);
        ReflectionTestUtils.setField(propertiesFilter, "propertyValidator", propertyValidator);


        propertiesFilter.filter(properties);


        assertThat(properties.getProperties(), contains(propertyWithBetweenMinMaxBaths));
    }

    @Test
    public void filterWithSquareMetersValidator() {
        Property propertyWithBetweenMinMaxSquareMeters = mock(Property.class);
        when(propertyWithBetweenMinMaxSquareMeters.getSquareMeters()).thenReturn(50);

        Property propertyWithGreaterThanMaxSquareMeters = mock(Property.class);
        when(propertyWithGreaterThanMaxSquareMeters.getBaths()).thenReturn(250);

        Property propertyWithLessThanMinSquareMeters = mock(Property.class);
        when(propertyWithLessThanMinSquareMeters.getBaths()).thenReturn(10);

        properties.setProperties(asList(propertyWithBetweenMinMaxSquareMeters, propertyWithGreaterThanMaxSquareMeters, propertyWithLessThanMinSquareMeters));

        PropertyValidator propertyValidator = new PropertyValidator();
        propertyValidator.addPropertyValidator(propertySquareMetersValidator);
        ReflectionTestUtils.setField(propertiesFilter, "propertyValidator", propertyValidator);


        propertiesFilter.filter(properties);


        assertThat(properties.getProperties(), contains(propertyWithBetweenMinMaxSquareMeters));
    }

}
package br.com.vivareal.spotippos.domain.validator;

import br.com.vivareal.spotippos.Application;
import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.domain.validator.property.PropertyBedsValidation;
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
 * Test for {@link PropertyBedsValidation}.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PropertyBedsValidationTest {

    @Autowired
    PropertyBedsValidation propertyBedsValidator;

    @Test
    public void validateBedsNumberBetweenMinAndMax() {
        Property property = mock(Property.class);
        when(property.getBeds()).thenReturn(Integer.valueOf(2));

        boolean validate = propertyBedsValidator.check(property);

        assertTrue(validate);
    }

    @Test
    public void validateBedsNumberEqualMax(){
        Property property = mock(Property.class);
        when(property.getBeds()).thenReturn(Integer.valueOf(5));

        boolean validate = propertyBedsValidator.check(property);

        assertTrue(validate);
    }

    @Test
    public void validateBedsNumberEqualMin(){
        Property property = mock(Property.class);
        when(property.getBeds()).thenReturn(Integer.valueOf(1));

        boolean validate = propertyBedsValidator.check(property);

        assertTrue(validate);
    }

    @Test
    public void validateBedsNumberGreaterThanMax(){
        Property property = mock(Property.class);
        when(property.getBeds()).thenReturn(Integer.valueOf(6));

        boolean validate = propertyBedsValidator.check(property);

        assertFalse(validate);
    }

    @Test
    public void validateBedsNumberLessThanMin(){
        Property property = mock(Property.class);
        when(property.getBeds()).thenReturn(Integer.valueOf(0));

        boolean validate = propertyBedsValidator.check(property);

        assertFalse(validate);
    }
}
package br.com.vivareal.spotippos.domain.validator;

import br.com.vivareal.spotippos.Application;
import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.domain.validator.property.PropertyBathsValidation;
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
 * Test for {@link PropertyBathsValidation}.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PropertyBathsValidationTest {

    @Autowired
    private PropertyBathsValidation propertyBathsValidator;

    @Test
    public void validateBathsNumberBetweenMinAndMax() {
        Property property = mock(Property.class);
        when(property.getBaths()).thenReturn(Integer.valueOf(2));

        boolean validate = propertyBathsValidator.check(property);

        assertTrue(validate);
    }

    @Test
    public void validateBathsNumberEqualMax(){
        Property property = mock(Property.class);
        when(property.getBaths()).thenReturn(Integer.valueOf(4));

        boolean validate = propertyBathsValidator.check(property);

        assertTrue(validate);
    }

    @Test
    public void validateBathsNumberEqualMin(){
        Property property = mock(Property.class);
        when(property.getBaths()).thenReturn(Integer.valueOf(1));

        boolean validate = propertyBathsValidator.check(property);

        assertTrue(validate);
    }

    @Test
    public void validateBathsNumberGreaterThanMax(){
        Property property = mock(Property.class);
        when(property.getBaths()).thenReturn(Integer.valueOf(5));

        boolean validate = propertyBathsValidator.check(property);

        assertFalse(validate);
    }

    @Test
    public void validateBathsNumberLessThanMin(){
        Property property = mock(Property.class);
        when(property.getBaths()).thenReturn(Integer.valueOf(0));

        boolean validate = propertyBathsValidator.check(property);

        assertFalse(validate);
    }
}
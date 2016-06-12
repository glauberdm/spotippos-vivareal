package br.com.vivareal.spotippos.domain.validator.property;

import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.domain.validator.Validation;
import org.springframework.stereotype.Component;

/**
 * {@link Validation} for {@link Property} beds
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@Component
public class PropertyBedsValidation implements Validation<Property> {

    @Override
    public boolean check(Property property) {
        return property.getBeds() <= 5 && property.getBeds() >= 1;
    }
}

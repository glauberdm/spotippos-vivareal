package br.com.vivareal.spotippos.domain.validator;

import br.com.vivareal.spotippos.domain.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@link Validator} for {@link Property}
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public class PropertyValidator implements Validator<Property> {

    private List<Validation<Property>> propertyValidations;

    public PropertyValidator() {
        this.propertyValidations = new ArrayList<>();
    }

    @Override
    public boolean validate(Property property) {
        boolean valid = true;

        for (Validation<Property> validator : propertyValidations) {
            if (!validator.check(property)) {
                return false;
            }
        }

        return valid;
    }

    public void addPropertyValidator(Validation<Property>... propertyValidations) {
        Collections.addAll(this.propertyValidations, propertyValidations);
    }
}

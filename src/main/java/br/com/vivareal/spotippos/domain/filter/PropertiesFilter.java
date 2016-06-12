package br.com.vivareal.spotippos.domain.filter;

import br.com.vivareal.spotippos.domain.Properties;
import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.domain.validator.PropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Filter for {@link Properties} based in {@link br.com.vivareal.spotippos.domain.validator.PropertyValidator}
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@Component
public class PropertiesFilter implements Filter<Properties> {

    @Autowired
    private PropertyValidator propertyValidator;

    @Override
    public void filter(Properties properties) {
        Predicate<Property> propertyPredicate = property -> {
            return propertyValidator.validate(property);
        };

        List<Property> propertiesValided = properties.getProperties().stream().filter(propertyPredicate).collect(Collectors.toList());

        properties.setProperties(propertiesValided);
        properties.setTotalProperties(propertiesValided.size());
    }
}

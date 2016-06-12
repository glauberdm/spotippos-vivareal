package br.com.vivareal.spotippos.domain;

import br.com.vivareal.spotippos.domain.filter.PropertiesFilter;
import br.com.vivareal.spotippos.domain.json.LoadJsonProperties;
import br.com.vivareal.spotippos.domain.json.LoadJsonProvinces;
import br.com.vivareal.spotippos.domain.validator.PropertyValidator;
import br.com.vivareal.spotippos.domain.validator.property.PropertyBathsValidation;
import br.com.vivareal.spotippos.domain.validator.property.PropertyBedsValidation;
import br.com.vivareal.spotippos.domain.validator.property.PropertySquareMetersValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring beans of domain spotippos-vivareal.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@Configuration
public class SpotipposDomainConfiguration {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PropertyBedsValidation propertyBedsValidation;

    @Autowired
    private PropertyBathsValidation propertyBathsValidation;

    @Autowired
    private PropertySquareMetersValidation propertySquareMetersValidation;

    @Autowired
    private PropertiesFilter propertiesFilter;

    @Bean
    public PropertyValidator propertyValidator() {
        PropertyValidator propertyValidator = new PropertyValidator();

        propertyValidator.addPropertyValidator(propertyBedsValidation, propertyBathsValidation, propertySquareMetersValidation);

        return propertyValidator;
    }

    @Bean(name = "loadJsonProperties")
    public LoadJsonProperties loadJsonProperties() {
        LoadJsonProperties loadJsonProperties = new LoadJsonProperties(objectMapper);
        loadJsonProperties.addFilter(propertiesFilter);
        return loadJsonProperties;
    }

    @Bean(name = "loadJsonProvinces")
    public LoadJsonProvinces loadJsonProvinces() {
        return new LoadJsonProvinces(objectMapper);
    }
}

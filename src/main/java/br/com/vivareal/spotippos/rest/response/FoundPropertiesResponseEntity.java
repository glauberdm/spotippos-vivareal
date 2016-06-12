package br.com.vivareal.spotippos.rest.response;

import br.com.vivareal.spotippos.domain.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Responde entry for {@link Property}s founds.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public class FoundPropertiesResponseEntity {

    private Integer foundProperties;
    private List<Property> properties = new ArrayList<>();

    public Integer getFoundProperties() {
        return foundProperties;
    }

    public void setFoundProperties(Integer foundProperties) {
        this.foundProperties = foundProperties;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}

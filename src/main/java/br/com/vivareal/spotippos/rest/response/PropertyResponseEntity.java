package br.com.vivareal.spotippos.rest.response;

import br.com.vivareal.spotippos.domain.Property;

/**
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public class PropertyResponseEntity extends DataResponseEntity<Property> {

    public PropertyResponseEntity(ResponseStatus status, Property data, String message) {
        super(status, data, message);
    }
}

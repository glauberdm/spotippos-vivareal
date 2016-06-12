package br.com.vivareal.spotippos.rest.response.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for {@link br.com.vivareal.spotippos.domain.Property} not found.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PropertyNotFoundException extends Exception {

    private static final String NOT_FOUND_MESSAGE_TEMPLATE = "Property with ID = %d not found!";

    public PropertyNotFoundException(Long id) {
        super(String.format(NOT_FOUND_MESSAGE_TEMPLATE, id));
    }
}

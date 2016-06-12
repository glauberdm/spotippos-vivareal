package br.com.vivareal.spotippos.rest.service;

import br.com.vivareal.spotippos.actor.PropertyActor;
import br.com.vivareal.spotippos.domain.Coordinate;
import br.com.vivareal.spotippos.domain.Properties;
import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.domain.validator.PropertyValidator;
import br.com.vivareal.spotippos.rest.response.FoundPropertiesResponseEntity;
import br.com.vivareal.spotippos.rest.response.PropertyResponseEntity;
import br.com.vivareal.spotippos.rest.response.ResponseStatus;
import br.com.vivareal.spotippos.rest.response.exception.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Service for {@link br.com.vivareal.spotippos.rest.controller.PropertiesController}
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@Service
public class PropertyService {

    public static final String SUCCESS_MESSAGE = "Congratulations! Your property was created in spotippos-vivareal! :D";
    public static final String UNSUCCESS_MESSAGE = "Sorry! Your property cannot be created because not valid in spotippos-vivareal!" +
            " Validation rules:" +
            " Maximum 5 beds, and minumum 1." +
            " Maximum 4 baths, and minumum 1." +
            " Maximum 240 square meters, and minimum 20.";
    public static final String ERROR_MESSAGE = "Sorry! Your property cannot be created. Please, contact the support.";

    @Autowired
    private Properties properties;

    @Autowired
    private PropertyActor propertyActor;

    @Autowired
    private PropertyValidator propertyValidator;

    /**
     * Find {@link Property} by id.
     *
     * @param id
     * @return
     * @throws PropertyNotFoundException
     */
    public Property findById(Long id) throws PropertyNotFoundException {
        Predicate<Property> propertyIdPredicate = p -> p.getId().equals(id);

        try {
            return properties.getProperties().parallelStream().filter(propertyIdPredicate).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new PropertyNotFoundException(id);
        }
    }

    /**
     * Return {@link Property}s in rectangle {@link Coordinate}s.
     *
     * @param a
     * @param b
     * @return
     */
    public FoundPropertiesResponseEntity propertiesByAreaCoordinates(Coordinate a, Coordinate b) {
        Predicate<Property> propertyInAreaPredicate = p -> {
            return propertyActor.propertyInAreaCoordinates(p, a, b);
        };

        List<Property> propertiesInArea = properties.getProperties().parallelStream().filter(propertyInAreaPredicate).collect(Collectors.toList());

        FoundPropertiesResponseEntity foundPropertiesResponseEntity = new FoundPropertiesResponseEntity();
        foundPropertiesResponseEntity.setProperties(propertiesInArea);
        foundPropertiesResponseEntity.setFoundProperties(propertiesInArea.size());

        return foundPropertiesResponseEntity;
    }

    /**
     * Add a {@link Property} in spotippos-vivareal.
     *
     * @param property
     * @return
     */
    public ResponseEntity<PropertyResponseEntity> create(Property property) {

        try {
            if (!propertyValidator.validate(property)) {
                PropertyResponseEntity dataResponseEntity = new PropertyResponseEntity(ResponseStatus.UNSUCCESS, null,
                        UNSUCCESS_MESSAGE);

                return new ResponseEntity<>(dataResponseEntity, HttpStatus.NOT_ACCEPTABLE);
            }

            Comparator<Property> propertyComparatorForMaxId = Comparator.comparing(Property::getId);

            try {
                Long maxId = properties.getProperties().parallelStream().max(propertyComparatorForMaxId).get().getId();
                Long nextId = maxId + 1;
                property.setId(nextId);
            } catch (NoSuchElementException e) {
                property.setId(1L);
            }

            propertyActor.defineProvinces(property);
            properties.getProperties().add(property);
            properties.setTotalProperties(properties.getTotalProperties() + 1);

            PropertyResponseEntity dataResponseEntity = new PropertyResponseEntity(ResponseStatus.SUCCESS, property, SUCCESS_MESSAGE);

            return new ResponseEntity<>(dataResponseEntity, HttpStatus.CREATED);
        } catch (Exception e) {

            PropertyResponseEntity dataResponseEntity = new PropertyResponseEntity(ResponseStatus.ERROR, null, ERROR_MESSAGE);

            return new ResponseEntity<>(dataResponseEntity, HttpStatus.EXPECTATION_FAILED);
        }
    }
}

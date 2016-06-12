package br.com.vivareal.spotippos.rest.controller;


import br.com.vivareal.spotippos.domain.Coordinate;
import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.rest.response.FoundPropertiesResponseEntity;
import br.com.vivareal.spotippos.rest.response.PropertyResponseEntity;
import br.com.vivareal.spotippos.rest.response.exception.PropertyNotFoundException;
import br.com.vivareal.spotippos.rest.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * PropertiesController for spotippos-vivareal.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@RestController
@RequestMapping(value = "/properties")
public class PropertiesController {

    @Autowired
    private PropertyService propertyService;

    /**
     * Create a {@link Property} in spotippos-vivareal.
     *
     * @param property
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PropertyResponseEntity> create(@RequestBody Property property) {
        return propertyService.create(property);
    }


    /**
     * Get {@link Property} by id.
     *
     * @param id
     * @return
     * @throws PropertyNotFoundException
     */
    @RequestMapping(value = "/{id}/**", method = RequestMethod.GET)
    public Property getPropery(@PathVariable Long id) throws PropertyNotFoundException {
        return propertyService.findById(id);
    }

    /**
     * Get {@link Property}s in point A (upper left) and point B (bottom right) rectangle coordinates.
     *
     * @param ax
     * @param ay
     * @param bx
     * @param by
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public FoundPropertiesResponseEntity getPropertiesByCoordinate(
            @RequestParam(value = "ax") Integer ax
            , @RequestParam(value = "ay") Integer ay
            , @RequestParam(value = "bx") Integer bx
            , @RequestParam(value = "by") Integer by) {

        Coordinate pointA = new Coordinate(ax, ay);
        Coordinate pointB = new Coordinate(bx, by);

        return propertyService.propertiesByAreaCoordinates(pointA, pointB);
    }
}

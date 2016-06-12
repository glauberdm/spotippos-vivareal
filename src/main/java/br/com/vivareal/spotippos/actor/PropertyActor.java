package br.com.vivareal.spotippos.actor;

import br.com.vivareal.spotippos.domain.Coordinate;
import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.domain.Province;
import br.com.vivareal.spotippos.domain.Provinces;
import de.svenjacobs.loremipsum.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Actor for @{@link Property}
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@Component
public class PropertyActor {

    private static final String PROPERTY_TITLE_TEMPLATE = "Imóvel códido %d, com %d quartos e %d banheiros";

    @Autowired
    private LoremIpsum loremIpsum;

    @Autowired
    private Provinces provinces;

    @Autowired
    private CoordinateActor coordinateActor;

    /**
     * Complement title of the {@link Property} whith text template @PROPERTY_TITLE_TEMPLATE for better apresentation.
     *
     * @param property {@link Property} for complementation.
     */
    public void complementTitle(Property property) {
        property.setTitle(String.format(PROPERTY_TITLE_TEMPLATE, property.getId(), property.getBeds(), property.getBaths()));
    }

    /**
     * Complement description of the {@link Property} for better apresentation.
     *
     * @param property {@link Property} for complementation.
     */
    public void complementDescription(Property property) {
        property.setDescription(loremIpsum.getWords());
    }

    /**
     * Define set of {@link Province} that the {@link Property} it's in.
     *
     * @param property {@link Property} for definition.
     */
    public void defineProvinces(Property property) {
        List<String> provinces = provincesForProperty(property);
        property.setProvinces(provinces);
    }

    /**
     * Return the list of {@link Province} names that a {@link Property} it's in.
     *
     * @param property {@link Property}
     * @return The list of provinces names.
     */
    public List<String> provincesForProperty(Property property) {
        Predicate<Map.Entry<String, Province>> predicateProvinces = entryProvince -> {

            Province province = entryProvince.getValue();

            return propertyInProvince(property, province);
        };

        Object provincesOfProperty = this.provinces.entrySet().parallelStream().filter(predicateProvinces).map(Map.Entry::getKey).collect(Collectors.toList());

        return (java.util.List<String>) provincesOfProperty;
    }

    /**
     * Check if a {@link Property} it's in {@link Province}.
     *
     * @param property {@link Property} for check.
     * @param province {@link Province} possibile for property.
     * @return
     */
    public boolean propertyInProvince(Property property, Province province) {

        Integer propertyX = property.getX();
        Integer propertyY = property.getY();
        Coordinate coordinateProperty = new Coordinate(propertyX, propertyY);

        Coordinate upperLeftProvince = province.getBoundaries().getUpperLeft();
        Coordinate bottomRightProvince = province.getBoundaries().getBottomRight();

        return coordinateActor.pointInPointsRectagle(coordinateProperty, upperLeftProvince, bottomRightProvince);
    }

    /**
     * Check if a {@link Property} it's in rectangle with upperLeft and bottomRight points {@link Coordinate}.
     *
     * @param property {@link Property} for check.
     * @param a        {@link Coordinate} of upprLeft point.
     * @param b        {@link Coordinate} of bottomRight point.
     * @return
     */
    public boolean propertyInAreaCoordinates(Property property, Coordinate a, Coordinate b) {
        Integer propertyX = property.getX();
        Integer propertyY = property.getY();
        Coordinate coordinateProperty = new Coordinate(propertyX, propertyY);

        return coordinateActor.pointInPointsRectagle(coordinateProperty, a, b);
    }
}

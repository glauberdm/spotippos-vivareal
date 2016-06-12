package br.com.vivareal.spotippos.domain.json;

import br.com.vivareal.spotippos.actor.PropertyActor;
import br.com.vivareal.spotippos.domain.Property;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * {@link JsonDeserializer} for {@link Property}
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@Component
public class PropertyDeserializer extends JsonDeserializer<Property> {

    @Autowired
    private PropertyActor propertyActor;

    /**
     * {@inheritDoc}
     */
    @Override
    public Property deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectNode propertieNode = p.readValueAsTree();

        JsonNode actualNode;

        Property property = new Property();
        property.setPrice((actualNode = propertieNode.get("price")) != null ? new BigDecimal(actualNode.asDouble()) : null);
        property.setId((actualNode = propertieNode.get("id")) != null ? actualNode.asLong() : null);
        property.setX((actualNode = propertieNode.get("x")) != null ? actualNode.asInt() : 0);
        property.setY((actualNode = propertieNode.get("y")) != null ? actualNode.asInt() : 0);
        property.setBeds((actualNode = propertieNode.get("beds")) != null ? actualNode.asInt() : 0);
        property.setBaths((actualNode = propertieNode.get("baths")) != null ? actualNode.asInt() : 0);
        property.setSquareMeters((actualNode = propertieNode.get("squareMeters")) != null ? actualNode.asInt() : 0);

        propertyActor.complementTitle(property);
        propertyActor.complementDescription(property);
        propertyActor.defineProvinces(property);

        return property;
    }
}

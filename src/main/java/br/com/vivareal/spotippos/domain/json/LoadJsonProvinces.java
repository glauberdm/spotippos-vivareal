package br.com.vivareal.spotippos.domain.json;

import br.com.vivareal.spotippos.domain.Province;
import br.com.vivareal.spotippos.domain.Provinces;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Map;

/**
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public class LoadJsonProvinces extends LoadJson<Provinces> {

    @Autowired
    public LoadJsonProvinces(ObjectMapper objectMapper) {
        super(Provinces.class, objectMapper);
    }

    /**
     * Specific parser for root vo {@link Provinces}
     *
     * @param jsonNode root {@link JsonNode}
     * @return
     */
    @Override
    protected Provinces toObject(JsonNode jsonNode) {
        Provinces provinces = new Provinces();

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();

        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            Province province = getObjectMapper().convertValue(field.getValue(), Province.class);
            province.setName(field.getKey());

            provinces.put(province.getName(), province);
        }

        return provinces;
    }
}

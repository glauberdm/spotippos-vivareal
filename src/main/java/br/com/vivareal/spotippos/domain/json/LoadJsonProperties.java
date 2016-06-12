package br.com.vivareal.spotippos.domain.json;

import br.com.vivareal.spotippos.domain.Properties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public class LoadJsonProperties extends LoadJson<Properties> {

    @Autowired
    public LoadJsonProperties(ObjectMapper objectMapper) {
        super(Properties.class, objectMapper);
    }
}

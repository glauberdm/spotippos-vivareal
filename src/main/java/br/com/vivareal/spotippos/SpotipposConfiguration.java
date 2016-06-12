package br.com.vivareal.spotippos;

import br.com.vivareal.spotippos.domain.Properties;
import br.com.vivareal.spotippos.domain.Provinces;
import br.com.vivareal.spotippos.domain.json.LoadJsonProperties;
import br.com.vivareal.spotippos.domain.json.LoadJsonProvinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;

/**
 * Spring beans spotippos-vivareal configuration.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@Configuration
public class SpotipposConfiguration {

    public static final String PROPERTIES_FILE_NAME = "properties.json";
    public static final String PROPERTIES_URL = "https://raw.githubusercontent.com/VivaReal/code-challenge/master/properties.json";

    public static final String PROVINCES_FILE_NAME = "provinces.json";
    public static final String PROVINCES_URL = "https://raw.githubusercontent.com/VivaReal/code-challenge/master/provinces.json";

    @Autowired
    private LoadJsonProperties loadJsonProperties;

    @Autowired
    private LoadJsonProvinces loadJsonProvinces;

    @Bean(name = "provinces")
    public Provinces provinces() throws IOException {
        try {
            return loadJsonProvinces.loadFromURL(PROVINCES_URL);
        } catch (Exception e) {
            return loadJsonProvinces.loadFromFile(PROVINCES_FILE_NAME);
        }
    }

    @Bean(name = "properties")
    @DependsOn({"provinces"})
    public Properties properties() throws IOException {
        try {
            return loadJsonProperties.loadFromURL(PROPERTIES_URL);
        } catch (Exception e) {
            return loadJsonProperties.loadFromFile(PROPERTIES_FILE_NAME);
        }
    }
}

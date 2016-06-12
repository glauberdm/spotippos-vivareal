package br.com.vivareal.spotippos.domain.json;

import br.com.vivareal.spotippos.Application;
import br.com.vivareal.spotippos.SpotipposConfiguration;
import br.com.vivareal.spotippos.domain.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Test for {@link LoadJsonProperties}.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class LoadJsonPropertiesTest {

    @Autowired
    private LoadJsonProperties loadJsonProperties;

    @Test
    public void loadJsonPropertiesFromFileSucess() throws IOException {
        Properties properties = loadJsonProperties.loadFromFile(SpotipposConfiguration.PROPERTIES_FILE_NAME);

        assertThat(properties, is(notNullValue()));
    }

    @Test(expected = NullPointerException.class)
    public void loadJsonPropertiesFromFileFail() throws IOException {
        Properties properties = loadJsonProperties.loadFromFile(null);

        assertThat(properties, is(nullValue()));
    }

    @Test
    public void loadJsonPropertiesFromURLSucess() throws IOException {
        Properties properties = loadJsonProperties.loadFromURL(SpotipposConfiguration.PROPERTIES_URL);

        assertThat(properties, is(notNullValue()));
    }

    @Test(expected = MalformedURLException.class)
    public void loadJsonPropertiesFromURLFail() throws IOException {
        Properties properties = loadJsonProperties.loadFromURL(null);

        assertThat(properties, is(nullValue()));
    }
}
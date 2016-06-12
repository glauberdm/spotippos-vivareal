package br.com.vivareal.spotippos.domain.json;

import br.com.vivareal.spotippos.Application;
import br.com.vivareal.spotippos.SpotipposConfiguration;
import br.com.vivareal.spotippos.domain.Provinces;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link LoadJsonProvinces}.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class LoadJsonProvincesTest {

    @Autowired
    private LoadJsonProvinces loadJsonProvinces;

    @Test
    public void loadJsonProvincesFromFileSucess() throws IOException {
        Provinces provinces = loadJsonProvinces.loadFromFile(SpotipposConfiguration.PROVINCES_FILE_NAME);

        assertThat(provinces, is(notNullValue()));
    }

    @Test(expected = NullPointerException.class)
    public void loadJsonProvincesFromFileFail() throws IOException {
        Provinces provinces = loadJsonProvinces.loadFromFile(null);

        assertThat(provinces, is(nullValue()));
    }

    @Test(expected = MalformedURLException.class)
    public void loadJsonProvincesFromUrlFail() throws IOException {
        Provinces provinces = loadJsonProvinces.loadFromURL(null);

        assertThat(provinces, is(nullValue()));
    }

}
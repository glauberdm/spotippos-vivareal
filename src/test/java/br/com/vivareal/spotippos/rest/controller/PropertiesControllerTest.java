package br.com.vivareal.spotippos.rest.controller;

import br.com.vivareal.spotippos.Application;
import br.com.vivareal.spotippos.actor.PropertyActor;
import br.com.vivareal.spotippos.domain.Properties;
import br.com.vivareal.spotippos.domain.Property;
import br.com.vivareal.spotippos.domain.json.LoadJsonProperties;
import br.com.vivareal.spotippos.rest.response.FoundPropertiesResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for {@link PropertiesController}.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, PropertyActor.class})
@WebAppConfiguration
public class PropertiesControllerTest {

    @Autowired
    private PropertiesController propertiesController;

    @Autowired
    private Properties properties;

    @Autowired
    private LoadJsonProperties loadJsonProperties;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void createSuccess() throws Exception {
        properties.setProperties(new ArrayList<>());
        properties.setTotalProperties(0);

        String jsonPropertyRequest = "{ \"x\": 222, \"y\": 444, \"title\": \"Imóvel código 1, com 5 quartos e 4 banheiros\", \"price\": 1250000, \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\", \"beds\": 4, \"baths\": 3, \"squareMeters\": 210}";

        mvc.perform(post("/properties")
                .contentType(APPLICATION_JSON_UTF8)
                .content(jsonPropertyRequest))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.message").value("Congratulations! Your property was created in spotippos-vivareal! :D"))
        ;
    }

    @Test
    public void createUnccessWithBedsGreaterThanMax() throws Exception {
        properties.setProperties(new ArrayList<>());
        properties.setTotalProperties(0);

        String propertyJson = "{ \"x\": 222, \"y\": 444, \"title\": \"Imóvel código 1, com 5 quartos e 4 banheiros\", \"price\": 1250000, \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\", \"beds\": 7, \"baths\": 3, \"squareMeters\": 210}";

        mvc.perform(post("/properties")
                .contentType(APPLICATION_JSON_UTF8)
                .content(propertyJson))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("UNSUCCESS"))
                .andExpect(jsonPath("$.data").value(nullValue()))
                .andExpect(jsonPath("$.message").value("Sorry! Your property cannot be created because not valid in spotippos-vivareal! Validation rules: Maximum 5 beds, and minumum 1. Maximum 4 baths, and minumum 1. Maximum 240 square meters, and minimum 20."))
        ;
    }

    @Test
    public void createError() throws Exception {
        properties.setProperties(null);

        String propertyJson = "{ \"x\": 222, \"y\": 444, \"title\": \"Imóvel código 1, com 5 quartos e 4 banheiros\", \"price\": 1250000, \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\", \"beds\": 4, \"baths\": 3, \"squareMeters\": 210}";

        mvc.perform(post("/properties")
                .contentType(APPLICATION_JSON_UTF8)
                .content(propertyJson))
                .andExpect(status().isExpectationFailed())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.data").value(nullValue()))
                .andExpect(jsonPath("$.message").value("Sorry! Your property cannot be created. Please, contact the support."))
        ;
    }

    @Test
    public void getPropery() throws Exception {
        Property property = new Property();
        property.setId(1L);

        properties.setProperties(asList(property));


        String jsonResponse = mvc.perform(get("/properties/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(1))
                .andReturn().getResponse().getContentAsString();


        ObjectMapper objectMapper = loadJsonProperties.getObjectMapper();
        Property propertyResponse = objectMapper.readValue(jsonResponse, Property.class);

        assertThat(propertyResponse.getId(), is(1L));
    }

    @Test
    public void getProperyNotFound() throws Exception {
        properties.setProperties(asList());

        mvc.perform(get("/properties/{id}", 1L))
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    public void getPropertiesByCoordinate() throws Exception {

        Property property1 = new Property();
        property1.setId(1L);
        property1.setX(550);
        property1.setY(550);

        Property property2 = new Property();
        property2.setId(2L);
        property2.setX(200);
        property2.setY(300);

        Property property3 = new Property();
        property3.setId(3L);
        property3.setX(450);
        property3.setY(450);

        properties.setProperties(asList(property1, property2, property3));


        String jsonResponse = mvc.perform(get("/properties?ax={integer}&ay={integer}&bx={integer}&by={integer}", 0, 600, 600, 400))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.foundProperties").value(2))
                .andExpect(jsonPath("$.properties").value(hasSize(2)))
                .andReturn().getResponse().getContentAsString();


        ObjectMapper objectMapper = loadJsonProperties.getObjectMapper();
        FoundPropertiesResponseEntity foundPropertiesResponseEntity = objectMapper.readValue(jsonResponse, FoundPropertiesResponseEntity.class);

        assertThat(foundPropertiesResponseEntity.getFoundProperties(), is(2));
        assertThat(foundPropertiesResponseEntity.getProperties().get(0).getId(), is(1L));
        assertThat(foundPropertiesResponseEntity.getProperties().get(1).getId(), is(3L));
    }

}
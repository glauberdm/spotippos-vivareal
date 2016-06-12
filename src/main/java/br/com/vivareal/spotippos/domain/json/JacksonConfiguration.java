package br.com.vivareal.spotippos.domain.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.svenjacobs.loremipsum.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

/**
 * JSON beans configurations.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@Configuration
public class JacksonConfiguration {

    @Autowired
    private SpringBeanHandlerInstantiator springBeanHandlerInstantiator;

    @Bean
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
        jackson2ObjectMapperFactoryBean.afterPropertiesSet();

        ObjectMapper objectMapper = jackson2ObjectMapperFactoryBean.getObject();

        objectMapper.setHandlerInstantiator(springBeanHandlerInstantiator);

        return objectMapper;
    }

    @Bean
    public LoremIpsum loremIpsum() {
        return new LoremIpsum();
    }
}

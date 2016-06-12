package br.com.vivareal.spotippos.domain.json;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * JSON Configuration for {@link PropertyDeserializer} apply @{@link com.fasterxml.jackson.databind.annotation.JsonDeserialize} in {@link br.com.vivareal.spotippos.domain.Property}
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@Component
public class SpringBeanHandlerInstantiator extends HandlerInstantiator {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public JsonDeserializer<?> deserializerInstance(DeserializationConfig config, Annotated annotated, Class<?> deserClass) {
        try {
            return (JsonDeserializer<?>) applicationContext.getBean(deserClass);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public KeyDeserializer keyDeserializerInstance(DeserializationConfig config, Annotated annotated, Class<?> keyDeserClass) {
        try {
            return (KeyDeserializer) applicationContext.getBean(keyDeserClass);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public JsonSerializer<?> serializerInstance(SerializationConfig config, Annotated annotated, Class<?> serClass) {
        try {
            return (JsonSerializer<?>) applicationContext.getBean(serClass);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TypeResolverBuilder<?> typeResolverBuilderInstance(MapperConfig<?> config, Annotated annotated, Class<?> builderClass) {
        try {
            return (TypeResolverBuilder<?>) applicationContext.getBean(builderClass);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TypeIdResolver typeIdResolverInstance(MapperConfig<?> config, Annotated annotated, Class<?> resolverClass) {
        try {
            return (TypeIdResolver) applicationContext.getBean(resolverClass);
        } catch (Exception e) {
            return null;
        }
    }
}

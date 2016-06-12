package br.com.vivareal.spotippos.domain.json;

import br.com.vivareal.spotippos.domain.filter.Filter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Load JSON from {@link File} of {@link URL}
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public abstract class LoadJson<T> {

    private ObjectMapper objectMapper;
    private Class<T> objectClass;
    private List<Filter<T>> filters;

    /**
     * Constructor.
     *
     * @param objectClass  root vo {@link Class} of JSON.
     * @param objectMapper {@link ObjectMapper}.
     */
    public LoadJson(Class<T> objectClass, ObjectMapper objectMapper) {
        this.objectClass = objectClass;
        this.objectMapper = objectMapper;
        this.filters = new ArrayList<>();
    }

    /**
     * Return T root vo load of JSON from {@link File} name.
     *
     * @param jsonFileName {@link String} for {@link File}.
     * @return object T type.
     */
    public T loadFromFile(String jsonFileName) throws IOException {
        InputStream inputStream = getResourceFile(jsonFileName);
        JsonNode rootNode = objectMapper.readTree(inputStream);
        return load(rootNode);
    }

    /**
     * Return T root vo load of JSON from {@link URL}.
     *
     * @param jsonUrl {@link String} for {@link URL}.
     * @return object T type.
     */
    public T loadFromURL(String jsonUrl) throws IOException {
        URL url = new URL(jsonUrl);
        JsonNode rootNode = objectMapper.readTree(url);
        return load(rootNode);
    }

    /**
     * Load {@link JsonNode} root and apply filters validations.
     *
     * @param rootNode
     * @return object T type.
     */
    private T load(JsonNode rootNode) {
        T t = toObject(rootNode);
        applyFilters(t);
        return t;
    }

    /**
     * Parser JSON to root vo {@link Class}
     *
     * @param jsonNode root {@link JsonNode}
     * @return object T type
     */
    protected T toObject(JsonNode jsonNode) {
        return this.getObjectMapper().convertValue(jsonNode, this.objectClass);
    }

    /**
     * Apply filters.
     *
     * @param t
     */
    private void applyFilters(T t) {
        for (Filter filter : filters) {
            filter.filter(t);
        }
    }

    /**
     * Return {@link ObjectMapper} of {@link LoadJson}
     *
     * @return
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * Return {@link File} of resources
     *
     * @param fileName
     * @return
     */
    private InputStream getResourceFile(String fileName) {
        return this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

    /**
     * Add {@link Filter}s.
     *
     * @param filters
     */
    public void addFilter(Filter<T>... filters) {
        Collections.addAll(this.filters, filters);
    }
}

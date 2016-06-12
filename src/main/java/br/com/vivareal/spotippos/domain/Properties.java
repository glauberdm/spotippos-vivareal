package br.com.vivareal.spotippos.domain;

import java.util.List;

/**
 * Register of list all validated {@link Property}s in spotippos-vivareal.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public class Properties {

    private Integer totalProperties;
    private List<Property> properties;

    public Integer getTotalProperties() {
        return totalProperties;
    }

    public void setTotalProperties(Integer totalProperties) {
        this.totalProperties = totalProperties;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}

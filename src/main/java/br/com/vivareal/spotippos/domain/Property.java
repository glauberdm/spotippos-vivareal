package br.com.vivareal.spotippos.domain;

import br.com.vivareal.spotippos.domain.json.PropertyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.util.List;

/**
 * Property in spotippos-vivareal.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
@JsonDeserialize(using = PropertyDeserializer.class)
public class Property {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private Integer x;
    private Integer y;
    private Integer beds;
    private Integer baths;
    private List<String> provinces;
    private Integer squareMeters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public List<String> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<String> provinces) {
        this.provinces = provinces;
    }

    public Integer getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(Integer squareMeters) {
        this.squareMeters = squareMeters;
    }
}

package br.com.vivareal.spotippos.domain;

/**
 * Province in spotippos-vivareal.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public class Province {

    private String name;
    private Boundaries boundaries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boundaries getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(Boundaries boundaries) {
        this.boundaries = boundaries;
    }
}

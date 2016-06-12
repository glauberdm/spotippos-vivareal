package br.com.vivareal.spotippos.domain.filter;

/**
 * Interface for filter's implementations.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public interface Filter<T> {
    void filter(T t);
}

package br.com.vivareal.spotippos.domain.validator;

/**
 * Interface for validations
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public interface Validation<T> {
    boolean check(T t);
}

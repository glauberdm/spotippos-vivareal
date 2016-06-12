package br.com.vivareal.spotippos.domain.validator;

/**
 * Interface for validator. Validate {@link Validation}s.
 *
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public interface Validator<T> {
    boolean validate(T t);
}

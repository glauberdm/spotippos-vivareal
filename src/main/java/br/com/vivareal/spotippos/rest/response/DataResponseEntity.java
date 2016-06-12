package br.com.vivareal.spotippos.rest.response;

/**
 * @author <a href="mailto:glauberbcc@gmail.com">Glauber Monteiro</a>
 */
public abstract class DataResponseEntity<T> {

    private ResponseStatus status;
    private T data;
    private String message;

    public DataResponseEntity(ResponseStatus status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

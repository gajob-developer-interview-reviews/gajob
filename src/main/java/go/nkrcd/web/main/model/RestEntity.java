package go.nkrcd.web.main.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestEntity<T> {
    private HttpStatus code;
    private String msg;
    private T data;

    @Builder
    public RestEntity(HttpStatus code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static<T> RestEntity<T> res(HttpStatus code, String msg, T data) {
        return RestEntity.<T>builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }
}

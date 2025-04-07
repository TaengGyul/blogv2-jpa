package shop.mtcoding.blog._core;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Resp<T> {
    private Integer status;
    private String msg;
    private T body;

    public static <B> Resp<?> ok(B body) { // 여기서 <B>에 있는 B는 일단 오브젝트가 들어가야하니 아무거나 적어둔 것
        return new Resp<>(200, "성공", body); // <> 안에 B 생략 가능, 여기서 new가 되면 타입이 결정되서 위에 T에 들어가는 거임
    }

    public static Resp<?> fail(Integer status, String msg) {
        return new Resp<>(status, msg, null);
    }
}
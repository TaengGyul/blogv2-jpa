package shop.mtcoding.blog.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class UpdateDTO {
        private String password;
        private String email;
    }

    // insert 용도의 dto에는 toEntity 메서드를 만든다.
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }

    @Data
    public static class LoginDTO { // insert 하는 용도가 아니여서 toEntity 필요없음 / 로그인은 조회 용도
        private String username;
        private String password;
        private String rememberMe; // check 되면 on, 안되면 null
    }
}

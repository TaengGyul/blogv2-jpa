package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String isPublic;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .isPublic(isPublic == null ? true : false)
                    .user(user) // user 객체 필요
                    .build();
        }
    }
}

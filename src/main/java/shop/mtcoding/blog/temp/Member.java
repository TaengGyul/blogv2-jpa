package shop.mtcoding.blog.temp;

// Builder가 왜 편한지 보여주는 예시

import shop.mtcoding.blog.user.User;

public class Member {
    private int id;
    private String name;
    private String password;

    public static Member builder() {
        return new Member();
    }

    public Member id(int id) {
        this.id = id;
        return this;
    }

    public Member name(String name) {
        this.name = name;
        return this;
    }

    public Member password(String password) {
        this.password = password;
        return this;
    }

    public static void main(String[] args) {
        User u = User.builder().username("admin").password("123456").build(); // 끝에 무조건 build() 가 있어야지 작동 됨 (문법임)

        Member m = Member.builder()
                .id(1)
                .name("길동");

    }
}
package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private Boolean isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    // LAZY 를 넣으면 릴레이션 맵핑을 안해서 user_tb를 조회 하지 않음!!, 게시글 목록 볼때는 게시글 상세보기와 달리 user 정보가 보일 필요가 없기 때문!
    // Object Relation Mapping (오브젝트 릴레이션 맵핑) : 유저 객체를 넣는다 / Many = board, One = user를 나타냄 (ManyToOne = One(user)이 Many(board)를 많이 만들수 있다.)
    private User user; // ORM

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, Boolean isPublic, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
        this.user = user;
        this.createdAt = createdAt;
    }
}
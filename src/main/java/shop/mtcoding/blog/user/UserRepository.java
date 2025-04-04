package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User findById(int id) {
        return em.find(User.class, id);
    }

    // 기본 쿼리 대신에 JPA 사용한 것! (JPA는 기술임, 이걸 쓰기 전에 기본 인서트 쿼리 짤 수 있어야 함)
    /*
        1. createNativeQuery -> 기본 쿼리
        2. createQuery -> JPA가 제공해주는 객체 지향 쿼리
        3. NamedQuery -> Query Method는 함수 이름으로 쿼리 생성 - 하지마요!!
        4. EntityGraph -> 지금 이해 못함
     */
    public void save(User user) { // 이때 save 는 비영속객체
        em.persist(user); // 2. user 영속객체
        // 3. user는 데이터베이스와 동기화 됨
    }


    public User findByUsername(String username) {
        return em.createQuery("select u from User u where u.username = :username", User.class) // 클래스 대문자
                .setParameter("username", username)
                .getSingleResult();
    }

}

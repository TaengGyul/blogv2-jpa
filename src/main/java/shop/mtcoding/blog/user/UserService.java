package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // 비지니스로직, 트랜잭션처리, DTO 완료
    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        try {
            userRepository.save(joinDTO.toEntity());
        } catch (Exception e) {

            throw new RuntimeException("동일한 아이디로 하지마라!");
        }

    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("유저네임 혹은 비밀번호가 틀렸습니다");
        }
        return user;
    }

    // 책임 : 비지니스 로직, 트랜잭션 처리, DTO 완료
    public Map<String, Object> 유저네임중복체크(String username) {
        User user = userRepository.findByUsername(username);
        Map<String, Object> dto = new HashMap<>();

        if (user == null) {
            dto.put("available", true);
        } else {
            dto.put("available", false);
        }
        return dto;
    }

    @Transactional
    public User 회원정보수정(UserRequest.UpdateDTO updateDTO, Integer userId) {
        User user = userRepository.findById(userId);
        if (user == null) throw new RuntimeException("회원을 찾을 수 없습니다.");
        user.update(updateDTO.getPassword(), updateDTO.getEmail()); // 영속화된 객체의 상태변경
        return user;
    } // 더티 체킹 -> 상태가 변경되면 update를 날려요!
}
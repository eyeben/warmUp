package softeerWarmup1.demo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softeerWarmup1.demo.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginIdAndPassword(String loginId,String password);
    boolean existsByNickname(String nickname);
}

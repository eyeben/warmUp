package softeerWarmup1.demo.member.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softeerWarmup1.demo.member.domain.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupDto {

    private String loginId;
    private String password;
    private String nickname;

    public Member toEntity(){
        Member member = Member.builder()
                        .loginId(loginId)
                        .password(password)
                        .nickname(nickname)
                        .build();
        return member;
    }
}

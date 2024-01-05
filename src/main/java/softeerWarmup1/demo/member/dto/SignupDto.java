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
    private String carNum;
    private Long modelId;
    private int modelYear;
    private int startYear;

    public Member toEntity(){
        Member member = Member.builder()
                        .loginId(loginId)
                        .password(password)
                        .nickname(nickname)
                        .carNum(carNum)
                        .modelId(modelId)
                        .modelYear(modelYear)
                        .startYear(startYear)
                        .build();
        return member;
    }
}

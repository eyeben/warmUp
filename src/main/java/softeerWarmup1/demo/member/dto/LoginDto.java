package softeerWarmup1.demo.member.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder//여기서는 필요없음
// 빌더를 쓰는 이유는 변수 값 일일히 세팅할 때 편해짐
//LoginDto loginDto = new LoginDto("idddd","passworddd");
//를 아래처럼 쓸 수 있게해주는 기능
// LoginDto loginDto = new LoginDto.Builder().loginId("iddd").password("passs").build();

public class LoginDto {
    private String loginId;
    private String password;
}

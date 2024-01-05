package softeerWarmup1.demo.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 클래스에서 게터 세터 등 필요한 메서드 자동 생성시켜줌
@Entity // 데이터 베이스의 엔티티로 인식 시키기 위한 어노테이션
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id//기본키 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 고유키값 자동생성시켜줌
    @Column// 컬럼으로 인식
    private Long id;
    @Column
    private String loginId;
    @Column
    private String password;
    @Column
    private String nickname;
    @Column
    private String carNum;
    @Column
    private Long modelId;
    @Column
    private int modelYear;
    @Column
    private int startYear;


}

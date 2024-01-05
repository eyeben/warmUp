package softeerWarmup1.demo.member.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
    private String carNum;
    private Long modelId;
    private int modelYear;
    private int startYear;

}

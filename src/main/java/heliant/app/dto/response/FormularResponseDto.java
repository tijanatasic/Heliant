package heliant.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FormularResponseDto extends BaseResponseDto {

    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "naziv")
    private String naziv;
}

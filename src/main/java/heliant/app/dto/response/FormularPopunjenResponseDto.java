package heliant.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FormularPopunjenResponseDto extends BaseResponseDto {

    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "formularId")
    private Integer formularId;
    @JsonProperty(value = "popunjenaPolja")
    private List<PoljePopunjenoResponseDto> popunjenaPolja;

}

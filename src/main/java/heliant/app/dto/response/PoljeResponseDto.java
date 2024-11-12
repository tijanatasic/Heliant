package heliant.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PoljeResponseDto extends BaseResponseDto {

    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "formular")
    private FormularResponseDto formular;
    @JsonProperty(value = "naziv")
    private String naziv;
    @JsonProperty(value = "prikazniRedosled")
    private Integer prikazniRedosled;
    @JsonProperty(value = "tip")
    private String tip;

}

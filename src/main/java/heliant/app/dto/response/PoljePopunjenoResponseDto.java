package heliant.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PoljePopunjenoResponseDto extends BaseResponseDto {

    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "idFormularPopunjen")
    private Integer idFormularPopunjen;
    @JsonProperty(value = "polje")
    private PoljeResponseDto polje;
    @JsonProperty(value = "vrednostTekst")
    private String vrednostTekst;
    @JsonProperty(value = "vrednostBroj")
    private Double vrednostBroj;

}

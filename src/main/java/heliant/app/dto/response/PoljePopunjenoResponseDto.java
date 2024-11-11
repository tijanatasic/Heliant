package heliant.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoljePopunjenoResponseDto {

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
    @JsonProperty(value = "vremeKreiranja")
    private LocalDateTime vremeKreiranja;
    @JsonProperty(value = "vremePoslednjeIzmene")
    private LocalDateTime vremePoslednjeIzmene;

}

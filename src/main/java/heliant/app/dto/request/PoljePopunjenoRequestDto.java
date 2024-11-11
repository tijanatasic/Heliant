package heliant.app.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoljePopunjenoRequestDto {

    @JsonProperty(value = "poljeId")
    @NotNull(message = "{polje.id.null.message}")
    private Integer poljeId;

    @JsonProperty(value = "vrednostTekst")
    private String vrednostTekst;

    @JsonProperty(value = "vrednostBroj")
    private Double vrednostBroj;

}

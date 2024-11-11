package heliant.app.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoljeRequestDto {

    @JsonProperty(value = "formularId")
    @NotNull(message = "{formular.id.null.message}")
    private Integer formularId;

    @JsonProperty(value = "naziv")
    @NotNull(message = "{naziv.null.message}")
    @NotBlank(message = "{naziv.empty.message}")
    private String naziv;

    @JsonProperty(value = "prikazniRedosled")
    @NotNull(message = "{prikazni.redosled.null.message}")
    private Integer prikazniRedosled;

    @JsonProperty(value = "tip")
    @NotNull(message = "{tip.null.message}")
    @NotBlank(message = "{tip.empty.message}")
    private String tip;
}

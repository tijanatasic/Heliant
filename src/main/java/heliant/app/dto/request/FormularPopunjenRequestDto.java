package heliant.app.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormularPopunjenRequestDto {

    @JsonProperty(value = "formularId")
    @NotNull(message = "{formular.id.null.message}")
    private Integer formularId;

    @JsonProperty(value = "popunjenaPolja")
    @NotNull(message = "{popunjena.polja.null.message}")
    @NotEmpty(message = "{popunjena.polja.empty.message}")
    private List<PoljePopunjenoRequestDto> popunjenaPolja;
}

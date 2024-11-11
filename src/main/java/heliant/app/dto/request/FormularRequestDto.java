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
public class FormularRequestDto {

    @JsonProperty(value = "naziv")
    @NotNull(message = "{naziv.null.message}")
    @NotBlank(message = "{naziv.empty.message}")
    private String naziv;

}

package heliant.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormularPopunjenResponseDto {

    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "formularId")
    private Integer formularId;
    @JsonProperty(value = "popunjenaPolja")
    private List<PoljePopunjenoResponseDto> popunjenaPolja;
    @JsonProperty(value = "vremeKreiranja")
    private LocalDateTime vremeKreiranja;
    @JsonProperty(value = "vremePoslednjeIzmene")
    private LocalDateTime vremePoslednjeIzmene;

}

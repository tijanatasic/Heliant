package heliant.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormularResponseDto {

    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "naziv")
    private String naziv;
    @JsonProperty(value = "vremeKreiranja")
    private LocalDateTime vremeKreiranja;
    @JsonProperty(value = "vremePoslednjeIzmene")
    private LocalDateTime vremePoslednjeIzmene;

}

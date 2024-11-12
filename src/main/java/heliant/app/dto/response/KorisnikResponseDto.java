package heliant.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KorisnikResponseDto {

    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "korisnickoIme")
    private String korisnickoIme;

}

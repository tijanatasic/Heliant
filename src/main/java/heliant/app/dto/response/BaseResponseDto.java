package heliant.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseDto {

    @JsonProperty(value = "vremeKreiranja")
    private LocalDateTime vremeKreiranja;
    @JsonProperty(value = "vremePoslednjeIzmene")
    private LocalDateTime vremePoslednjeIzmene;
    @JsonProperty(value = "korisnikKreirao")
    private KorisnikResponseDto korisnikKreirao;
    @JsonProperty(value = "korisnikPoslednjiAzurirao")
    private KorisnikResponseDto korisnikPoslednjiAzurirao;

}

package heliant.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @JsonProperty(value = "username")
    @NotNull(message = "{username.null.message}")
    @NotBlank(message = "{username.empty.message}")
    private String username;

    @JsonProperty(value = "password")
    @NotNull(message = "{password.null.message}")
    @NotBlank(message = "{password.empty.message}")
    private String password;

}

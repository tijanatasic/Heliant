package heliant.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormularPageableResponseDto {

    @JsonProperty(value = "formularList")
    private List<FormularResponseDto> formularList;
    @JsonProperty(value = "totalPages")
    private Integer totalPages;
    @JsonProperty(value = "totalElements")
    private Long totalElements;
    @JsonProperty(value = "currentPage")
    private Integer currentPage;
    @JsonProperty(value = "pageSize")
    private Integer pageSize;
}

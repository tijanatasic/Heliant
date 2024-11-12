package heliant.app.service;

import heliant.app.dto.request.FormularRequestDto;
import heliant.app.dto.response.FormularPageableResponseDto;
import heliant.app.dto.response.FormularResponseDto;

public interface FormularService {

    FormularResponseDto findFormularById(Integer id);

    FormularPageableResponseDto findAllFormular(int page, int pageSize);

    FormularResponseDto saveFormular(FormularRequestDto formularRequestDto);

    FormularResponseDto updateFormular(FormularRequestDto formularRequestDto, Integer id);

    void deleteFormular(Integer id);

}

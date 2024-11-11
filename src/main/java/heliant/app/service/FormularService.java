package heliant.app.service;

import heliant.app.dto.request.FormularRequestDto;
import heliant.app.dto.response.FormularResponseDto;

import java.util.List;

public interface FormularService {

    FormularResponseDto findFormularById(Integer id);

    List<FormularResponseDto> findAllFormular();

    FormularResponseDto saveFormular(FormularRequestDto formularRequestDto);

    FormularResponseDto updateFormular(FormularRequestDto formularRequestDto, Integer id);

    void deleteFormular(Integer id);

}

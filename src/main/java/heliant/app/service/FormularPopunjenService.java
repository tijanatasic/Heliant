package heliant.app.service;

import heliant.app.dto.request.FormularPopunjenRequestDto;
import heliant.app.dto.response.FormularPopunjenResponseDto;

import java.util.List;

public interface FormularPopunjenService {

    FormularPopunjenResponseDto findPopunjenFormularById(Integer id);

    List<FormularPopunjenResponseDto> findAllPopunjeniFormulari();

    FormularPopunjenResponseDto savePopunjenFormular(FormularPopunjenRequestDto formularPopunjenRequestDto);

    FormularPopunjenResponseDto updatePopunjenFormular(FormularPopunjenRequestDto formularPopunjenRequestDto, Integer id);

    void deletePopunjenFormular(Integer id);
}

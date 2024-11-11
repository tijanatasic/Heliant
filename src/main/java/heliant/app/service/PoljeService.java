package heliant.app.service;

import heliant.app.dto.request.PoljeRequestDto;
import heliant.app.dto.response.PoljeResponseDto;

import java.util.List;

public interface PoljeService {

    PoljeResponseDto findPoljeById(Integer id);

    List<PoljeResponseDto> findAllPolja();

    PoljeResponseDto savePolje(PoljeRequestDto poljeRequestDto);

    PoljeResponseDto updatePolje(PoljeRequestDto poljeRequestDto, Integer id);

    void deletePolje(Integer id);

}

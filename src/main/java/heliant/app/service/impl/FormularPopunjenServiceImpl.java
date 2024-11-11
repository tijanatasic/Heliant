package heliant.app.service.impl;

import heliant.app.dto.request.FormularPopunjenRequestDto;
import heliant.app.dto.request.PoljePopunjenoRequestDto;
import heliant.app.dto.response.FormularPopunjenResponseDto;
import heliant.app.entity.FormularPopunjenEntity;
import heliant.app.exception.ValidationException;
import heliant.app.mapper.FormularPopunjenMapper;
import heliant.app.repository.FormularPopunjenEntityRepository;
import heliant.app.service.FormularPopunjenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormularPopunjenServiceImpl implements FormularPopunjenService {

    private final FormularPopunjenEntityRepository formularPopunjenEntityRepository;
    private final FormularPopunjenMapper formularPopunjenMapper;

    @Override
    @Transactional
    public FormularPopunjenResponseDto findPopunjenFormularById(Integer id) {
        FormularPopunjenEntity formularPopunjenEntity = getFormularPopunjenById(id);
        FormularPopunjenResponseDto formularPopunjenResponseDto = formularPopunjenMapper.mapFormularPopunjenEntityToResponseDto(formularPopunjenEntity);
        log.debug("Mapped FormularPopunjenResponseDto: {}", formularPopunjenResponseDto);
        return formularPopunjenResponseDto;
    }

    @Override
    @Transactional
    public List<FormularPopunjenResponseDto> findAllPopunjeniFormulari() {
        List<FormularPopunjenEntity> formularPopunjenEntities = formularPopunjenEntityRepository.findAll();
        log.debug("Number of fetched formulari popunjeni is: {}", formularPopunjenEntities.size());
        if (formularPopunjenEntities.isEmpty()) {
            log.debug("There are no formulars in DB.");
            return List.of();
        }
        List<FormularPopunjenResponseDto> formularPopunjenResponseDtos = formularPopunjenMapper.mapFormularPopunjenEntityListToResponseDtoList(formularPopunjenEntities);
        log.debug("Mapped formularPopunjenResponseDtos: {}", formularPopunjenResponseDtos);
        return formularPopunjenResponseDtos;
    }

    @Override
    @Transactional
    public FormularPopunjenResponseDto savePopunjenFormular(FormularPopunjenRequestDto formularPopunjenRequestDto) {
        validatePopunjenaPolja(formularPopunjenRequestDto.getPopunjenaPolja());
        FormularPopunjenEntity formularPopunjenEntity = formularPopunjenMapper.mapFormularPopunjenDtoRequestToEntity(formularPopunjenRequestDto);
        log.debug("Mapped formularPopunjenEntity: {}", formularPopunjenEntity);
        FormularPopunjenEntity savedFormularPopunjenEntity = formularPopunjenEntityRepository.save(formularPopunjenEntity);
        log.debug("Popunjen formular successfully saved: {}", savedFormularPopunjenEntity);
        return formularPopunjenMapper.mapFormularPopunjenEntityToResponseDto(savedFormularPopunjenEntity);
    }

    @Override
    @Transactional
    public FormularPopunjenResponseDto updatePopunjenFormular(FormularPopunjenRequestDto formularPopunjenRequestDto, Integer id) {
        validatePopunjenaPolja(formularPopunjenRequestDto.getPopunjenaPolja());
        FormularPopunjenEntity formularPopunjenEntity = getFormularPopunjenById(id);
        FormularPopunjenEntity updatedFormularPopunjenEntity = formularPopunjenMapper.mapForUpdateFormularPopunjenDtoRequestToEntity(formularPopunjenEntity, formularPopunjenRequestDto);
        log.debug("Mapped updatedFormularPopunjenEntity: {}", updatedFormularPopunjenEntity);
        updatedFormularPopunjenEntity = formularPopunjenEntityRepository.saveAndFlush(updatedFormularPopunjenEntity);
        log.debug("Updated formular popunjen entity: {}", updatedFormularPopunjenEntity);
        return formularPopunjenMapper.mapFormularPopunjenEntityToResponseDto(updatedFormularPopunjenEntity);
    }

    @Override
    @Transactional
    public void deletePopunjenFormular(Integer id) {
        FormularPopunjenEntity formularPopunjenEntity = getFormularPopunjenById(id);
        formularPopunjenEntityRepository.delete(formularPopunjenEntity);
        log.debug("Successfully deleted formular popunjen with id: {}", id);
    }

    private FormularPopunjenEntity getFormularPopunjenById(Integer id) {
        FormularPopunjenEntity formularPopunjenEntity = formularPopunjenEntityRepository.findById(id).orElse(null);
        log.debug("Fetched formular popunjen: {}", formularPopunjenEntity);
        if (formularPopunjenEntity == null) {
            log.warn("Unable to find formular popunjen with id: {}", id);
            throw new ValidationException("Unable to find formular popunjen with given ID!");
        }
        return formularPopunjenEntity;
    }

    private void validatePopunjenaPolja(List<PoljePopunjenoRequestDto> popunjenaPolja) {
        popunjenaPolja.stream()
                .filter(p ->
                        (StringUtils.isBlank(p.getVrednostTekst()) && p.getVrednostBroj() == null) ||
                                (StringUtils.isNoneBlank(p.getVrednostTekst()) && p.getVrednostBroj() != null))
                .findFirst()
                .ifPresent(e -> {
                    throw new ValidationException("Fields broj and text can't be both null or both populated at the same time!");
                });

        Map<Integer, Long> countByPoljeId = popunjenaPolja.stream()
                .collect(Collectors.groupingBy(PoljePopunjenoRequestDto::getPoljeId, Collectors.counting()));

        for (Map.Entry<Integer, Long> entry : countByPoljeId.entrySet()) {
            if (entry.getValue() > 1) {
                throw new ValidationException("You are not able to populate the same field with more than once!");
            }
        }


    }
}

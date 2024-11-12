package heliant.app.service.impl;

import heliant.app.dto.request.FormularRequestDto;
import heliant.app.dto.response.FormularPageableResponseDto;
import heliant.app.dto.response.FormularResponseDto;
import heliant.app.entity.FormularEntity;
import heliant.app.exception.ValidationException;
import heliant.app.mapper.FormularMapper;
import heliant.app.repository.FormularEntityRepository;
import heliant.app.service.FormularService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormularServiceImpl implements FormularService {

    private final FormularEntityRepository formularEntityRepository;
    private final FormularMapper formularMapper;

    @Override
    @Transactional
    public FormularResponseDto findFormularById(Integer id) {
        FormularEntity formularEntity = getFormularById(id);
        FormularResponseDto formularResponseDto = formularMapper.mapFormularEntityToResponseDto(formularEntity);
        log.debug("Mapped FormularDto: {}", formularResponseDto);
        return formularResponseDto;
    }

    @Override
    @Transactional
    public FormularPageableResponseDto findAllFormular(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<FormularEntity> formularEntitiesPage = formularEntityRepository.findAllByOrderByIdAsc(pageable);
        if (formularEntitiesPage.isEmpty()) {
            log.debug("There are no formulars in DB.");
            return new FormularPageableResponseDto(List.of(), 0, 0L, 0, pageSize);
        }
        FormularPageableResponseDto formularPageableResponseDto = formularMapper.mapFormularDtoListToPageableResponse(formularEntitiesPage);
        log.debug("Mapped FormularPageableResponseDto: {}", formularPageableResponseDto);
        return formularPageableResponseDto;
    }

    @Override
    @Transactional
    public FormularResponseDto saveFormular(FormularRequestDto formularRequestDto) {
        FormularEntity formularEntity = formularMapper.mapFormularDtoRequestToEntity(formularRequestDto);
        log.debug("Mapped formularEntity: {}", formularEntity);
        FormularEntity savedFormularEntity = formularEntityRepository.save(formularEntity);
        log.debug("Successfully saved formular entity: {}", savedFormularEntity);
        return formularMapper.mapFormularEntityToResponseDto(savedFormularEntity);
    }

    @Override
    @Transactional
    public FormularResponseDto updateFormular(FormularRequestDto formularRequestDto, Integer id) {
        FormularEntity formularEntity = getFormularById(id);
        FormularEntity updatedFormularEntity = formularMapper.mapFormularDtoRequestToEntity(formularEntity, formularRequestDto);
        updatedFormularEntity = formularEntityRepository.saveAndFlush(updatedFormularEntity);
        log.debug("Updated formular: {}", updatedFormularEntity);
        return formularMapper.mapFormularEntityToResponseDto(updatedFormularEntity);
    }

    @Override
    @Transactional
    public void deleteFormular(Integer id) {
        FormularEntity formularEntity = getFormularById(id);
        formularEntityRepository.delete(formularEntity);
        log.debug("Successfully deleted formular entity: {}", formularEntity);
    }

    private FormularEntity getFormularById(Integer id) {
        FormularEntity formularEntity = formularEntityRepository.findById(id).orElse(null);
        log.debug("Fetched formular: {}", formularEntity);
        if (formularEntity == null) {
            log.warn("Unable to find formular with id: {}", id);
            throw new ValidationException("Unable to find formular with given ID!");
        }
        return formularEntity;
    }
}

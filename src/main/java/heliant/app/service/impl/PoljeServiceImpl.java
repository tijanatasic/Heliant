package heliant.app.service.impl;

import heliant.app.dto.request.PoljeRequestDto;
import heliant.app.dto.response.PoljeResponseDto;
import heliant.app.entity.PoljeEntity;
import heliant.app.exception.ValidationException;
import heliant.app.mapper.PoljeMapper;
import heliant.app.repository.PoljeEntityRepository;
import heliant.app.service.PoljeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PoljeServiceImpl implements PoljeService {

    private final PoljeEntityRepository poljeEntityRepository;
    private final PoljeMapper poljeMapper;

    @Override
    @Transactional
    public PoljeResponseDto findPoljeById(Integer id) {
        PoljeEntity poljeEntity = getPoljeById(id);
        PoljeResponseDto poljeResponseDto = poljeMapper.mapPoljeEntityToResponseDto(poljeEntity);
        log.debug("Mapped polje: {}", poljeResponseDto);
        return poljeResponseDto;
    }

    @Override
    @Transactional
    public List<PoljeResponseDto> findAllPolja() {
        List<PoljeEntity> poljeEntities = poljeEntityRepository.findAll();
        log.debug("Number of fetched polja is: {}", poljeEntities.size());
        if (poljeEntities.isEmpty()) {
            log.debug("There are no polja in DB.");
            return List.of();
        }
        List<PoljeResponseDto> poljeResponseDtos = poljeMapper.mapPoljeEntityListToResponseDtoList(poljeEntities);
        log.debug("Mapped formularDtos: {}", poljeResponseDtos);
        return poljeResponseDtos;
    }

    @Override
    @Transactional
    public PoljeResponseDto savePolje(PoljeRequestDto poljeRequestDto) {
        PoljeEntity poljeEntity = poljeMapper.mapPoljeDtoRequestToEntity(poljeRequestDto);
        log.debug("Mapped polje: {}", poljeEntity);
        PoljeEntity savedPoljeEntity = poljeEntityRepository.save(poljeEntity);
        log.debug("Saved polje entity: {}", savedPoljeEntity);
        return poljeMapper.mapPoljeEntityToResponseDto(savedPoljeEntity);
    }

    @Override
    @Transactional
    public PoljeResponseDto updatePolje(PoljeRequestDto poljeRequestDto, Integer id) {
        PoljeEntity poljeEntity = getPoljeById(id);
        PoljeEntity updatedPoljeEntity = poljeMapper.mapPoljeDtoRequestToEntity(poljeEntity, poljeRequestDto);
        updatedPoljeEntity = poljeEntityRepository.saveAndFlush(updatedPoljeEntity);
        log.debug("Saved polje entity: {}", updatedPoljeEntity);
        return poljeMapper.mapPoljeEntityToResponseDto(updatedPoljeEntity);
    }

    @Override
    @Transactional
    public void deletePolje(Integer id) {
        PoljeEntity poljeEntity = getPoljeById(id);
        poljeEntityRepository.delete(poljeEntity);
        log.debug("Successfully deleted polje: {}", poljeEntity);
    }

    private PoljeEntity getPoljeById(Integer id) {
        PoljeEntity poljeEntity = poljeEntityRepository.findById(id).orElse(null);
        log.debug("Fetched polje: {}", poljeEntity);
        if (poljeEntity == null) {
            log.warn("Unable to find polje with id: {}", id);
            throw new ValidationException("Unable to find polje with given ID!");
        }
        return poljeEntity;
    }
}

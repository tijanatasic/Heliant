package heliant.app.mapper;

import heliant.app.dto.request.FormularPopunjenRequestDto;
import heliant.app.dto.request.PoljePopunjenoRequestDto;
import heliant.app.dto.response.FormularPopunjenResponseDto;
import heliant.app.entity.FormularEntity;
import heliant.app.entity.FormularPopunjenEntity;
import heliant.app.entity.PoljePopunjenoEntity;
import heliant.app.exception.ValidationException;
import heliant.app.repository.FormularEntityRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FormularPopunjenMapper {

    @Autowired
    private PoljePopunjenoMapper poljePopunjenoMapper;
    @Autowired
    private FormularEntityRepository formularEntityRepository;

    @Mapping(target = "popunjenaPolja", ignore = true)
    @Mapping(target = "formularId", source = "formular.id")
    public abstract FormularPopunjenResponseDto mapFormularPopunjenEntityToResponseDto(FormularPopunjenEntity formularPopunjenEntity);

    public abstract List<FormularPopunjenResponseDto> mapFormularPopunjenEntityListToResponseDtoList(List<FormularPopunjenEntity> formularPopunjenEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vremeKreiranja", ignore = true)
    @Mapping(target = "vremePoslednjeIzmene", ignore = true)
    @Mapping(target = "popunjenaPolja", ignore = true)
    @Mapping(target = "formular", ignore = true)
    public abstract FormularPopunjenEntity mapForUpdateFormularPopunjenDtoRequestToEntity(@MappingTarget FormularPopunjenEntity formularPopunjenEntity, FormularPopunjenRequestDto formularPopunjenRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vremeKreiranja", ignore = true)
    @Mapping(target = "vremePoslednjeIzmene", ignore = true)
    @Mapping(target = "popunjenaPolja", ignore = true)
    @Mapping(target = "formular", ignore = true)
    public abstract FormularPopunjenEntity mapFormularPopunjenDtoRequestToEntity(FormularPopunjenRequestDto formularPopunjenRequestDto);

    @AfterMapping
    void mapFormularPopunjenEntityToResponseDtoAfterMapping(@MappingTarget FormularPopunjenResponseDto formularPopunjenResponseDto, FormularPopunjenEntity formularPopunjenEntity) {
        formularPopunjenResponseDto.setPopunjenaPolja(poljePopunjenoMapper.mapPoljePopunjenoEntityToDto(formularPopunjenEntity.getPopunjenaPolja()));
    }

    @AfterMapping
    void mapFormularPopunjenDtoRequestToEntityAfterMapping(@MappingTarget FormularPopunjenEntity formularPopunjenEntity, FormularPopunjenRequestDto formularPopunjenRequestDto) {
        if (formularPopunjenEntity.getPopunjenaPolja() == null || formularPopunjenEntity.getPopunjenaPolja().isEmpty()) {
            formularPopunjenEntity.getPopunjenaPolja().addAll(poljePopunjenoMapper.mapPoljePopunjenoRequestDtoToEntityList(formularPopunjenRequestDto.getPopunjenaPolja(), formularPopunjenEntity));
        } else {
            updatePopunjenaPoljaFields(formularPopunjenEntity, formularPopunjenRequestDto);
        }

        formularPopunjenEntity.setFormular(getFormularById(formularPopunjenRequestDto.getFormularId()));
    }

    private void updatePopunjenaPoljaFields(FormularPopunjenEntity formularPopunjenEntity, FormularPopunjenRequestDto formularPopunjenRequestDto) {

        Map<Integer, PoljePopunjenoRequestDto> newPoljaPopunjenaMap = formularPopunjenRequestDto.getPopunjenaPolja().stream()
                .collect(Collectors.toMap(PoljePopunjenoRequestDto::getPoljeId, Function.identity()));

        Iterator<PoljePopunjenoEntity> iterator = formularPopunjenEntity.getPopunjenaPolja().iterator();

        while (iterator.hasNext()) {
            PoljePopunjenoEntity oldPoljePopunjeno = iterator.next();
            PoljePopunjenoRequestDto newPoljePopunjeno = newPoljaPopunjenaMap.get(oldPoljePopunjeno.getPolje().getId());

            if (newPoljePopunjeno != null) {
                oldPoljePopunjeno.setVrednostTekst(newPoljePopunjeno.getVrednostTekst());
                oldPoljePopunjeno.setVrednostBroj(newPoljePopunjeno.getVrednostBroj());
                newPoljaPopunjenaMap.remove(oldPoljePopunjeno.getPolje().getId());
            } else {
                ;
                iterator.remove();
            }
        }
        newPoljaPopunjenaMap.values().forEach(newPoljePopunjeno -> {
            PoljePopunjenoEntity poljePopunjenoEntity = poljePopunjenoMapper.mapPoljePopunjenoRequestDtoToEntity(newPoljePopunjeno, formularPopunjenEntity);
            formularPopunjenEntity.getPopunjenaPolja().add(poljePopunjenoEntity);
        });
    }

    private FormularEntity getFormularById(Integer id) {
        FormularEntity formularEntity = formularEntityRepository.findById(id).orElse(null);
        if (formularEntity == null) {
            throw new ValidationException("Unable to find formular with given ID!");
        }
        return formularEntity;
    }
}

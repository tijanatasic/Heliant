package heliant.app.mapper;

import heliant.app.dto.request.PoljeRequestDto;
import heliant.app.dto.response.PoljeResponseDto;
import heliant.app.entity.FormularEntity;
import heliant.app.entity.PoljeEntity;
import heliant.app.enums.TipPoljaEnum;
import heliant.app.exception.ValidationException;
import heliant.app.repository.FormularEntityRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", imports = {TipPoljaEnum.class})
public abstract class PoljeMapper {

    @Autowired
    private FormularEntityRepository formularEntityRepository;

    public abstract PoljeResponseDto mapPoljeEntityToResponseDto(PoljeEntity poljeEntity);

    public abstract List<PoljeResponseDto> mapPoljeEntityListToResponseDtoList(List<PoljeEntity> poljeEntities);

    @Mapping(target = "tip", expression = "java(TipPoljaEnum.getByValue(poljeRequestDto.getTip()))")
    public abstract PoljeEntity mapPoljeDtoRequestToEntity(PoljeRequestDto poljeRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vremeKreiranja", ignore = true)
    @Mapping(target = "vremePoslednjeIzmene", ignore = true)
    @Mapping(target = "formular", ignore = true)
    @Mapping(target = "tip", expression = "java(TipPoljaEnum.getByValue(poljeRequestDto.getTip()))")
    public abstract PoljeEntity mapPoljeDtoRequestToEntity(@MappingTarget PoljeEntity poljeEntity, PoljeRequestDto poljeRequestDto);

    @AfterMapping
    void mapPoljeDtoRequestToEntityAfterMapping(@MappingTarget PoljeEntity poljeEntity, PoljeRequestDto poljeRequestDto) {
        FormularEntity formularEntity = getFormularById(poljeRequestDto.getFormularId());
        poljeEntity.setFormular(formularEntity);
    }

    private FormularEntity getFormularById(Integer id) {
        FormularEntity formularEntity = formularEntityRepository.findById(id).orElse(null);
        if (formularEntity == null) {
            throw new ValidationException("Unable to find formular with given ID!");
        }
        return formularEntity;
    }
}

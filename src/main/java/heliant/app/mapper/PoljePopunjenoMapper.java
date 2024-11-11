package heliant.app.mapper;

import heliant.app.dto.request.PoljePopunjenoRequestDto;
import heliant.app.dto.response.PoljePopunjenoResponseDto;
import heliant.app.entity.FormularPopunjenEntity;
import heliant.app.entity.PoljeEntity;
import heliant.app.entity.PoljePopunjenoEntity;
import heliant.app.exception.ValidationException;
import heliant.app.repository.PoljeEntityRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PoljePopunjenoMapper {

    @Autowired
    private PoljeEntityRepository poljeEntityRepository;

    @Mapping(target = "idFormularPopunjen", source = "formularPopunjen.id")
    public abstract PoljePopunjenoResponseDto mapPoljePopunjenoEntityToDto(PoljePopunjenoEntity poljePopunjenoEntity);

    public abstract List<PoljePopunjenoResponseDto> mapPoljePopunjenoEntityToDto(List<PoljePopunjenoEntity> poljePopunjenoEntities);

    public abstract PoljePopunjenoEntity mapPoljePopunjenoRequestDtoToEntity(PoljePopunjenoRequestDto popunjenoProPoljePopunjenoRequestDto, @Context FormularPopunjenEntity formularPopunjenEntity);

    public abstract List<PoljePopunjenoEntity> mapPoljePopunjenoRequestDtoToEntityList(List<PoljePopunjenoRequestDto> popunjenoRequestDtoList, @Context FormularPopunjenEntity formularPopunjenEntity);

    @AfterMapping
    void mapPoljePopunjenoRequestDtoToEntityAfterMapping(@MappingTarget PoljePopunjenoEntity poljePopunjenoEntity, PoljePopunjenoRequestDto popunjenoPoljePopunjenoRequestDto, @Context FormularPopunjenEntity formularPopunjenEntity) {
        poljePopunjenoEntity.setPolje(getPoljeById(popunjenoPoljePopunjenoRequestDto.getPoljeId()));
        poljePopunjenoEntity.setFormularPopunjen(formularPopunjenEntity);
    }

    private PoljeEntity getPoljeById(Integer id) {
        PoljeEntity poljeEntity = poljeEntityRepository.findById(id).orElse(null);
        if (poljeEntity == null) {
            throw new ValidationException("Unable to find polje with given ID!");
        }
        return poljeEntity;
    }
}

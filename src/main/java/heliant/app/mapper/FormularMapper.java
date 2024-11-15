package heliant.app.mapper;

import heliant.app.dto.request.FormularRequestDto;
import heliant.app.dto.response.FormularPageableResponseDto;
import heliant.app.dto.response.FormularResponseDto;
import heliant.app.entity.FormularEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FormularMapper {

    public abstract FormularResponseDto mapFormularEntityToResponseDto(FormularEntity formularEntity);

    public abstract List<FormularResponseDto> mapFormularEntityListToResponseDtoList(List<FormularEntity> formularEntities);

    public abstract FormularEntity mapFormularDtoRequestToEntity(FormularRequestDto formularRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vremeKreiranja", ignore = true)
    @Mapping(target = "vremePoslednjeIzmene", ignore = true)
    public abstract FormularEntity mapFormularDtoRequestToEntity(@MappingTarget FormularEntity formularEntity, FormularRequestDto formularRequestDto);

    @Mapping(target = "formularList", expression = "java(mapFormularEntityListToResponseDtoList(formularEntitiesPage.get().toList()))")
    @Mapping(target = "totalPages", expression = "java(formularEntitiesPage.getTotalPages())")
    @Mapping(target = "totalElements", expression = "java(formularEntitiesPage.getTotalElements())")
    @Mapping(target = "currentPage", expression = "java(formularEntitiesPage.getNumber())")
    @Mapping(target = "pageSize", expression = "java(formularEntitiesPage.getSize())")
    public abstract FormularPageableResponseDto mapFormularDtoListToPageableResponse(Page<FormularEntity> formularEntitiesPage);
}

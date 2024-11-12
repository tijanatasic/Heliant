package heliant.app.mapper;

import heliant.app.dto.response.KorisnikResponseDto;
import heliant.app.entity.KorisnikEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class KorisnikMapper {

    public abstract KorisnikResponseDto mapKorisnikEntityToResponseDto(KorisnikEntity korisnikEntity);

}

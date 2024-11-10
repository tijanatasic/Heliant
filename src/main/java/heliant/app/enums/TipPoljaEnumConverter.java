package heliant.app.enums;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipPoljaEnumConverter implements AttributeConverter<TipPoljaEnum, String> {

    @Override
    public String convertToDatabaseColumn(TipPoljaEnum tipPoljaEnum) {
        return tipPoljaEnum.name();
    }

    @Override
    public TipPoljaEnum convertToEntityAttribute(String value) {
        return TipPoljaEnum.valueOf(value);
    }
}

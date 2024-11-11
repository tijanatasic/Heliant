package heliant.app.enums;

import heliant.app.exception.ValidationException;

import java.util.Arrays;

public enum TipPoljaEnum {

    TIP_1, TIP_2, TIP_3;

    public static TipPoljaEnum getByValue(String value) {
        return Arrays.stream(TipPoljaEnum.values())
                .filter(t -> t.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Invalid tip!"));
    }
}

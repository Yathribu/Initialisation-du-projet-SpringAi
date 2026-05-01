package com.example.prototypeai.util.trimvalue;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class TrimUtil {

    @Named("TrimUtil")
    public String trimValue(String value) {
        if (value == null) {
            return null;
        }

        return value.trim()
                    .toLowerCase()
                    .replaceAll("\\s+", "");
    }

}

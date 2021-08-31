package com.boxtal.doyenm.doctolib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * A medical structure as a place where you can receive a vaccine
 */
@Getter
@AllArgsConstructor
@Builder
public class StructureDto {
    /**
     * Its identifier in Doctolib
     */
    private String id;
    /**
     * Its name (for example "Centre de vaccination Covid19 de Vitré", "Dr Nicolas Legru"...)
     */
    private String name;
}

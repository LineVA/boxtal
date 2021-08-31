package com.boxtal.doyenm.doctolib.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * All the appointments of a given structure
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDto {
    /**
     * List of availabilities
     */
    private List<AvailabilityDto> availabilities;
    /**
     * The structure
     */
    private StructureDto structure;
}

package com.boxtal.doyenm.doctolib.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * All the availabilities of a given structure
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailabilityDto {
    /**
     * List of slots
     */
    List<SlotDto> slots;

}

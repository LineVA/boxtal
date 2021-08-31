package com.boxtal.doyenm.doctolib.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * A date + schedule of an appointment
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlotDto {
    /**
     * the date and schedule of an appointment
     * For example : "2021-08-31T14:50:00.000+02:00"
     */
    @JsonProperty("start_date")
    String startDate;
}

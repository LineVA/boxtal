package com.boxtal.doyenm.doctolib.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Slot {
    @JsonProperty("start_date")
    String startDate;
}

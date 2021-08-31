package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.doctolib.dto.AppointmentDto;
import com.boxtal.doyenm.doctolib.dto.StructureDto;

import java.util.List;

/**
 * Connector to Doctolin
 */
public interface DoctolibConnector {

    /**
     * Call Doctolib to retrieve the list of structures who handle Covid19 vaccination in or near the given zip code
     * @param zipCode the zipCode where where we are looking for
     * @return list of sturctures who handle Covid19 vaccination
     */
    List<StructureDto> getStructures(String zipCode);

    /**
     * Call Doctolib to retrieve all the appointments of a given structure
     * @param structure the structure for which we want the appointments
     * @return an {{{@link AppointmentDto}}} wtth the structure and its appointments
     */
    AppointmentDto getAppointmentsByStructure(StructureDto structure);
}

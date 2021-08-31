package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.doctolib.dto.AppointmentDto;
import com.boxtal.doyenm.doctolib.dto.StructureDto;

import java.util.List;

public interface DoctolibConnector {

    List<StructureDto> getStructures(String zipConde);

    AppointmentDto getAppointmentsByStructure(StructureDto structure);
}

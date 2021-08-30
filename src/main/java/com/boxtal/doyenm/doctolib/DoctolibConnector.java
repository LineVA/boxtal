package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.doctolib.dto.Structure;

import java.util.List;

public interface DoctolibConnector {

    List<Structure> getStructures(String zipConde);

    List<Object> getAppointmentsByStructure(Structure structure);
}

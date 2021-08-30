package com.boxtal.doyenm.doctolib;

import java.util.List;

public interface DoctolibConnector {

    List<Object> getStructures(String zipConde);

    List<Object> getAppointmentsByStructure(Object structure);
}

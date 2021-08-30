package com.boxtal.doyenm.doctolib;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctolibConnectorImpl implements DoctolibConnector{
    @Override
    public List<Object> getStructures(String zipConde) {
        return null;
    }

    @Override
    public List<Object> getAppointmentsByStructure(Object structure) {
        return null;
    }
}

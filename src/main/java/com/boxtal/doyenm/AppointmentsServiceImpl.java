package com.boxtal.doyenm;

import com.boxtal.doyenm.doctolib.DoctolibConnector;
import com.boxtal.doyenm.doctolib.dto.Structure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentsServiceImpl implements AppointmentsService {

    private final DoctolibConnector doctolibConnector;

    public List<Appointment> getAppointmentsByZipCode(String zipCode) {
        List<Structure> structures = doctolibConnector.getStructures(zipCode);
        structures.forEach(struc ->
                doctolibConnector.getAppointmentsByStructure(struc)
        );
        return null;
    }
}


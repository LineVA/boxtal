package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.Appointment;
import com.boxtal.doyenm.doctolib.dto.Structure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DoctolibServiceImpl implements DoctolibService {

    private final DoctolibConnector doctolibConnector;
    private final DoctolibToCommonAppointmentsFunction doctolibToCommonAppointmentsFunction;

    @Override
    public List<Appointment> getAppointmentsByZipCode(String zipCode) {
        List<Structure> structures = doctolibConnector.getStructures(zipCode);
        List<Appointment> appointments = new ArrayList<>();
        structures
                .stream()
                .map(struc ->
                        doctolibConnector.getAppointmentsByStructure(struc))
                .map(doctolibToCommonAppointmentsFunction)
                .map(apps -> appointments.addAll(apps))
                .findAny();
        return appointments;
    }
}

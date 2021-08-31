package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.Appointment;
import com.boxtal.doyenm.doctolib.dto.StructureDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class DoctolibServiceImpl implements DoctolibService {

    private final DoctolibConnector doctolibConnector;
    private final DoctolibToCommonAppointmentsFunction doctolibToCommonAppointmentsFunction;

    @Override
    public List<Appointment> getAppointmentsByZipCode(String zipCode) {
        List<StructureDto> structures = doctolibConnector.getStructures(zipCode);
        List<Appointment> appointments = new ArrayList<>();
        structures
                .stream()
                .map(struc ->
                        doctolibConnector.getAppointmentsByStructure(struc))
                .map(doctolibToCommonAppointmentsFunction)
                .forEach(apps -> appointments.addAll(apps));
        return appointments;
    }
}

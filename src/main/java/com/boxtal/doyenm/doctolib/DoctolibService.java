package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.Appointment;

import java.util.List;

public interface DoctolibService {
     List<Appointment> getAppointmentsByZipCode(String zipCode);

}

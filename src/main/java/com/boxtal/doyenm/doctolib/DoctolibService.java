package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.Appointment;

import java.util.List;

/**
 * Entry point for Doctolib
 */
public interface DoctolibService {

     /**
      * Retrieve all the Doctolib appointments for given zipCode
      * @param zipCode the zipCode we are looking for
      * @return list of the corresponding appointments
      */
     List<Appointment> getAppointmentsByZipCode(String zipCode);

}

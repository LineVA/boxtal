package com.boxtal.doyenm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/doyenm")
@RequiredArgsConstructor
public class BoxtalWebService {

    private final AppointmentsService appointmentsService;


    @GetMapping("/appointments")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> checkIdentity(@RequestParam String zipCode) {
        return appointmentsService.getAppointmentsByZipCode(zipCode);
    }
}

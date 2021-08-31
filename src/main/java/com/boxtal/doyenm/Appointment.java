package com.boxtal.doyenm;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Appointment {
   private String structure;
   private String address;
   private LocalDateTime schedule;
}

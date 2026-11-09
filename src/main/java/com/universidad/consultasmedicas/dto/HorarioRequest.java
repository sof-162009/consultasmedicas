package com.universidad.consultasmedicas.dto;
import jakarta.validation.constraints.NotNull; import java.time.LocalTime;
public record HorarioRequest(@NotNull LocalTime horaInicio,@NotNull LocalTime horaFin){public boolean valido(){return horaFin!=null&&horaInicio!=null&&horaFin.isAfter(horaInicio);}}

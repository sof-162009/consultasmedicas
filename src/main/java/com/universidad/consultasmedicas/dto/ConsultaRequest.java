package com.universidad.consultasmedicas.dto;
import jakarta.validation.constraints.*; import java.time.LocalTime;
public record ConsultaRequest(@NotBlank @Size(max=100) String motivo,@NotNull @Positive Integer consultorio,@NotNull LocalTime horaInicio,@NotNull LocalTime horaFin,@NotNull Long pacienteId,@NotNull Long medicoId){public boolean horarioValido(){return horaInicio!=null&&horaFin!=null&&horaFin.isAfter(horaInicio);}}

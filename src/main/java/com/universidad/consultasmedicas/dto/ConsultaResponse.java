package com.universidad.consultasmedicas.dto;
import java.time.LocalTime;
public record ConsultaResponse(Long id,String motivo,Integer consultorio,LocalTime horaInicio,LocalTime horaFin,Long pacienteId,String nombrePaciente,Long medicoId,String nombreMedico,String especialidadMedico){}

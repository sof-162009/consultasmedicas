package com.universidad.consultasmedicas.dto;
import jakarta.validation.constraints.*;
public record MedicoUpdateRequest(@NotBlank @Size(max=80) String nombre,@NotBlank @Size(max=80) String especialidad,@Size(max=20) String telefono){}

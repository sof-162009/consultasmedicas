package com.universidad.consultasmedicas.dto;
import jakarta.validation.constraints.*;
public record MedicoRequest(@NotBlank @Size(max=80) String nombre,@NotBlank @Size(max=80) String especialidad,@Size(max=20) String telefono,@NotBlank @Size(min=4,max=50) String username,@Size(min=8,max=72) String password){}

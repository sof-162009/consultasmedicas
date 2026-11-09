package com.universidad.consultasmedicas.controller;
import com.universidad.consultasmedicas.dto.*; import com.universidad.consultasmedicas.entity.*; import com.universidad.consultasmedicas.service.GestionService;
import jakarta.validation.Valid; import io.swagger.v3.oas.annotations.*; import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api") @Tag(name="Consultas médicas",description="Administración y consulta de citas por rol")
public class ApiController {
 private final GestionService service; public ApiController(GestionService s){service=s;}
 @GetMapping("/admin/medicos") @Operation(summary="Lista médicos (administrador)") public List<MedicoResponse> medicos(){return service.medicos().stream().map(ApiController::medicoDto).toList();}
 @GetMapping("/admin/medicos/{id}") @Operation(summary="Detalle de médico") public MedicoResponse medico(@PathVariable Long id){return medicoDto(service.medico(id));}
 @PostMapping("/admin/medicos") @ResponseStatus(HttpStatus.CREATED) @Operation(summary="Crea médico y cuenta") public MedicoResponse crearMedico(@Valid @RequestBody MedicoRequest d){return medicoDto(service.crearMedico(d));}
 @PutMapping("/admin/medicos/{id}") @Operation(summary="Edita datos de médico") public MedicoResponse editarMedico(@PathVariable Long id,@Valid @RequestBody MedicoUpdateRequest d){return medicoDto(service.editarMedico(id,d));}
 @DeleteMapping("/admin/medicos/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) @Operation(summary="Elimina médico sin citas") public void borrarMedico(@PathVariable Long id){service.borrarMedico(id);}
 @GetMapping("/admin/consultas") @Operation(summary="Lista todas las consultas") public List<ConsultaResponse> consultas(){return service.consultas().stream().map(ApiController::consultaDto).toList();}
 @GetMapping("/admin/consultas/{id}") @Operation(summary="Detalle de consulta") public ConsultaResponse consulta(@PathVariable Long id){return consultaDto(service.consulta(id));}
 @PostMapping("/admin/consultas") @ResponseStatus(HttpStatus.CREATED) @Operation(summary="Registra consulta vinculada a paciente y médico") public ConsultaResponse crearConsulta(@Valid @RequestBody ConsultaRequest d){return consultaDto(service.crearConsulta(d));}
 @PutMapping("/admin/consultas/{id}") @Operation(summary="Edita consulta") public ConsultaResponse editarConsulta(@PathVariable Long id,@Valid @RequestBody ConsultaRequest d){return consultaDto(service.editarConsulta(id,d));}
 @DeleteMapping("/admin/consultas/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) @Operation(summary="Elimina consulta") public void borrarConsulta(@PathVariable Long id){service.borrarConsulta(id);}
 @GetMapping("/medico/consultas") @Operation(summary="Consultas del médico autenticado") public List<ConsultaResponse> consultasMedico(Authentication a){return service.consultasMedico(a.getName()).stream().map(ApiController::consultaDto).toList();}
 @GetMapping("/paciente/consultas") @Operation(summary="Citas del paciente autenticado") public List<ConsultaResponse> consultasPaciente(Authentication a){return service.consultasPaciente(a.getName()).stream().map(ApiController::consultaDto).toList();}
 @GetMapping("/paciente/consultas/{id}") @Operation(summary="Detalle de cita propia") public ConsultaResponse cita(@PathVariable Long id,Authentication a){return consultaDto(service.citaPaciente(id,a.getName()));}
 @PatchMapping("/paciente/consultas/{id}/horario") @Operation(summary="Actualiza solo el horario de una cita propia") public ConsultaResponse horario(@PathVariable Long id,@Valid @RequestBody HorarioRequest d,Authentication a){return consultaDto(service.actualizarHorario(id,a.getName(),d));}
 private static MedicoResponse medicoDto(Medico m){return new MedicoResponse(m.getId(),m.getNombre(),m.getEspecialidad(),m.getTelefono(),m.getUsuario()==null?null:m.getUsuario().getUsername());}
 private static ConsultaResponse consultaDto(Consulta c){return new ConsultaResponse(c.getId(),c.getMotivo(),c.getConsultorio(),c.getHoraInicio(),c.getHoraFin(),c.getPaciente().getId(),c.getPaciente().getNombre(),c.getMedico().getId(),c.getMedico().getNombre(),c.getMedico().getEspecialidad());}
}

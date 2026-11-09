package com.universidad.consultasmedicas.service;
import com.universidad.consultasmedicas.dto.*; import com.universidad.consultasmedicas.entity.*; import com.universidad.consultasmedicas.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import org.springframework.http.HttpStatus; import org.springframework.web.server.ResponseStatusException; import java.util.*;
@Service @Transactional public class GestionService {
 private final MedicoRepository medicos; private final PacienteRepository pacientes; private final ConsultaRepository consultas; private final UsuarioRepository usuarios; private final RolRepository roles; private final PasswordEncoder encoder;
 public GestionService(MedicoRepository m,PacienteRepository p,ConsultaRepository c,UsuarioRepository u,RolRepository r,PasswordEncoder e){medicos=m;pacientes=p;consultas=c;usuarios=u;roles=r;encoder=e;}
 private <T>T req(Optional<T> o,String n){return o.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,n+" no encontrado"));}
 public List<Medico> medicos(){return medicos.findAll();} public List<Paciente> pacientes(){return pacientes.findAll();} public List<Consulta> consultas(){return consultas.findAll();}
 public Medico medico(Long id){return req(medicos.findById(id),"Médico");} public Consulta consulta(Long id){return req(consultas.findById(id),"Consulta");}
 public List<Consulta> consultasMedico(String user){return consultas.findByMedicoUsuarioUsername(user);} public List<Consulta> consultasPaciente(String user){return consultas.findByPacienteUsuarioUsername(user);}
 public Paciente pacienteActual(String user){return req(pacientes.findByUsuarioUsername(user),"Paciente");} public Medico medicoActual(String user){return req(medicos.findByUsuarioUsername(user),"Médico");}
 public Medico crearMedico(MedicoRequest d){if(d.password()==null||d.password().length()<8)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La contraseña inicial debe tener al menos 8 caracteres");if(usuarios.findByUsername(d.username()).isPresent())throw new ResponseStatusException(HttpStatus.CONFLICT,"Usuario ya existe");var role=req(roles.findByNombre("MEDICO"),"Rol MEDICO");var u=usuarios.save(new Usuario(d.username(),encoder.encode(d.password()),role));return medicos.save(new Medico(d.nombre(),d.especialidad(),d.telefono(),u));}
 public Medico editarMedico(Long id,MedicoUpdateRequest d){var m=medico(id);m.update(d.nombre(),d.especialidad(),d.telefono());return m;}
 public void borrarMedico(Long id){if(consultas.existsByMedicoId(id))throw new ResponseStatusException(HttpStatus.CONFLICT,"No se puede eliminar un médico con consultas asociadas");var m=medico(id);Long uid=m.getUsuario()==null?null:m.getUsuario().getId();medicos.delete(m);if(uid!=null)usuarios.deleteById(uid);}
 public Paciente crearPaciente(String nombre,String username,String password){if(usuarios.findByUsername(username).isPresent())throw new ResponseStatusException(HttpStatus.CONFLICT,"Usuario ya existe");var role=req(roles.findByNombre("PACIENTE"),"Rol PACIENTE");return pacientes.save(new Paciente(nombre,usuarios.save(new Usuario(username,encoder.encode(password),role))));}
 public Consulta crearConsulta(ConsultaRequest d){check(d.horarioValido());return consultas.save(new Consulta(d.motivo(),d.consultorio(),d.horaInicio(),d.horaFin(),req(pacientes.findById(d.pacienteId()),"Paciente"),medico(d.medicoId())));}
 public Consulta editarConsulta(Long id,ConsultaRequest d){check(d.horarioValido());var c=consulta(id);c.update(d.motivo(),d.consultorio(),d.horaInicio(),d.horaFin(),req(pacientes.findById(d.pacienteId()),"Paciente"),medico(d.medicoId()));return c;}
 public void borrarConsulta(Long id){consultas.delete(consulta(id));}
 public Consulta citaPaciente(Long id,String user){var c=consulta(id);if(!Objects.equals(c.getPaciente().getUsuario().getUsername(),user))throw new ResponseStatusException(HttpStatus.FORBIDDEN,"La cita no pertenece al paciente");return c;}
 public Consulta actualizarHorario(Long id,String user,HorarioRequest d){check(d.valido());var c=citaPaciente(id,user);c.updateHorario(d.horaInicio(),d.horaFin());return c;}
 public long totalMedicos(){return medicos.count();} public long totalConsultas(){return consultas.count();}
 private void check(boolean ok){if(!ok)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La hora final debe ser posterior a la inicial");}
}

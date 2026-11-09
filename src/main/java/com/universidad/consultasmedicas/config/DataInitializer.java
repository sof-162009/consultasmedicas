package com.universidad.consultasmedicas.config;
import com.universidad.consultasmedicas.entity.*; import com.universidad.consultasmedicas.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class DataInitializer {
 @Bean CommandLineRunner init(RolRepository roles,UsuarioRepository usuarios,MedicoRepository medicos,PacienteRepository pacientes,PasswordEncoder enc){return args->{
  for(String n:new String[]{"ADMINISTRADOR","MEDICO","PACIENTE"})if(roles.findByNombre(n).isEmpty())roles.save(new Rol(n));
  createAccount("admin","Admin123*","ADMINISTRADOR",null,roles,usuarios,medicos,pacientes,enc);
  createAccount("medico1","Medico123*","MEDICO","Dra. Ana Gómez|Medicina general",roles,usuarios,medicos,pacientes,enc);
  createAccount("paciente1","Paciente123*","PACIENTE","Carlos Pérez",roles,usuarios,medicos,pacientes,enc);
 };}
 private void createAccount(String username,String password,String role,String profile,RolRepository roles,UsuarioRepository users,MedicoRepository doctors,PacienteRepository patients,PasswordEncoder encoder){
  var user=users.findByUsername(username).orElseGet(()->users.save(new Usuario(username,encoder.encode(password),roles.findByNombre(role).orElseThrow())));
  if(profile==null)return;
  if(role.equals("MEDICO")&&!doctors.existsByUsuarioUsername(username)){String[] f=profile.split("\\|",2);doctors.save(new Medico(f[0],f[1],null,user));}
  if(role.equals("PACIENTE")&&!patients.existsByUsuarioUsername(username))patients.save(new Paciente(profile,user));
 }
}

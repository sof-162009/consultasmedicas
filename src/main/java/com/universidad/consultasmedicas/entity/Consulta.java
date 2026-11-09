package com.universidad.consultasmedicas.entity;
import jakarta.persistence.*; import java.time.LocalTime;
@Entity @Table(name="consultas") public class Consulta {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String motivo;
 @Column(nullable=false) private Integer consultorio;
 @Column(name="hora_inicio",nullable=false) private LocalTime horaInicio;
 @Column(name="hora_fin",nullable=false) private LocalTime horaFin;
 @ManyToOne @JoinColumn(name="paciente_id",nullable=false) private Paciente paciente;
 @ManyToOne @JoinColumn(name="medico_id",nullable=false) private Medico medico;
 protected Consulta() {}
 public Consulta(String m,Integer c,LocalTime i,LocalTime f,Paciente p,Medico d){motivo=m;consultorio=c;horaInicio=i;horaFin=f;paciente=p;medico=d;}
 public void update(String m,Integer c,LocalTime i,LocalTime f,Paciente p,Medico d){motivo=m;consultorio=c;horaInicio=i;horaFin=f;paciente=p;medico=d;}
 public void updateHorario(LocalTime i,LocalTime f){horaInicio=i;horaFin=f;}
 public Long getId(){return id;} public String getMotivo(){return motivo;} public Integer getConsultorio(){return consultorio;} public LocalTime getHoraInicio(){return horaInicio;} public LocalTime getHoraFin(){return horaFin;} public Paciente getPaciente(){return paciente;} public Medico getMedico(){return medico;}
}

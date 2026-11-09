package com.universidad.consultasmedicas.entity;
import jakarta.persistence.*;
@Entity @Table(name="pacientes") public class Paciente {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=40) private String nombre;
 @OneToOne @JoinColumn(name="usuario_id",unique=true) private Usuario usuario;
 protected Paciente() {} public Paciente(String n,Usuario u){nombre=n;usuario=u;}
 public Long getId(){return id;} public String getNombre(){return nombre;} public Usuario getUsuario(){return usuario;}
}

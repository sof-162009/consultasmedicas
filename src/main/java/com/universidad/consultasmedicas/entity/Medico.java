package com.universidad.consultasmedicas.entity;
import jakarta.persistence.*;
@Entity @Table(name="medicos") public class Medico {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=80) private String nombre;
 @Column(nullable=false,length=80) private String especialidad;
 @Column(length=20) private String telefono;
 @OneToOne @JoinColumn(name="usuario_id",unique=true) private Usuario usuario;
 protected Medico() {} public Medico(String n,String e,String t,Usuario u){nombre=n;especialidad=e;telefono=t;usuario=u;}
 public void update(String n,String e,String t){nombre=n;especialidad=e;telefono=t;}
 public Long getId(){return id;} public String getNombre(){return nombre;} public String getEspecialidad(){return especialidad;} public String getTelefono(){return telefono;} public Usuario getUsuario(){return usuario;}
}

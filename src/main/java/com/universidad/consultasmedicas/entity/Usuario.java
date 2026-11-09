package com.universidad.consultasmedicas.entity;
import jakarta.persistence.*;
@Entity @Table(name="usuarios") public class Usuario {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=50) private String username;
 @Column(nullable=false,length=100) private String password;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="rol_id",nullable=false) private Rol rol;
 protected Usuario() {} public Usuario(String username,String password,Rol rol){this.username=username;this.password=password;this.rol=rol;}
 public Long getId(){return id;} public String getUsername(){return username;} public String getPassword(){return password;} public Rol getRol(){return rol;}
}

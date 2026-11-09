package com.universidad.consultasmedicas.repository;
import com.universidad.consultasmedicas.entity.Rol; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface RolRepository extends JpaRepository<Rol,Long>{Optional<Rol> findByNombre(String nombre);}

package com.universidad.consultasmedicas.repository;
import com.universidad.consultasmedicas.entity.Usuario; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{Optional<Usuario> findByUsername(String username);}

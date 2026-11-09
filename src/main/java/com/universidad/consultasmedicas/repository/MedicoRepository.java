package com.universidad.consultasmedicas.repository;
import com.universidad.consultasmedicas.entity.Medico; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface MedicoRepository extends JpaRepository<Medico,Long>{Optional<Medico> findByUsuarioUsername(String username); boolean existsByUsuarioUsername(String username);}

package com.universidad.consultasmedicas.repository;
import com.universidad.consultasmedicas.entity.Consulta; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ConsultaRepository extends JpaRepository<Consulta,Long>{List<Consulta> findByMedicoUsuarioUsername(String username); List<Consulta> findByPacienteUsuarioUsername(String username); boolean existsByMedicoId(Long id);}

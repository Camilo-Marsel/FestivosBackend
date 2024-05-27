package festivos.festivos.core.interfaces.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import festivos.festivos.core.entidades.Tipos;

@Repository
public interface ITiposRepositorio extends JpaRepository<Tipos, Integer>{
}

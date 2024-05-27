package festivos.festivos.core.interfaces.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import festivos.festivos.core.entidades.Festivos;

@Repository
public interface  IFestivosRepositorio extends JpaRepository<Festivos, Integer>{
    @Query(value="SELECT f FROM festivo f WHERE f.mes = :mes AND f.dia = :dia", nativeQuery = true)
    public List<Festivos> buscar(@Param("mes")int mes, @Param("dia")int dia);

    List<Festivos> findByMesAndDia(int mes, int dia);

    Optional<Festivos> findByNombre(String nombre);
}

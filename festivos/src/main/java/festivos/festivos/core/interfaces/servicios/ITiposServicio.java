package festivos.festivos.core.interfaces.servicios;

import java.util.List;

import festivos.festivos.core.entidades.Tipos;

public interface ITiposServicio {
    public List<Tipos> listar();

    public Tipos obtener(int id);

    public List<Tipos> buscar(String nombre);

    public Tipos agregar(Tipos Tipos);

    public Tipos modificar(Tipos Tipos);

    public boolean eliminar();
}

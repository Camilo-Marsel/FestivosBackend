package festivos.festivos.core.interfaces.servicios;

import java.util.List;

import festivos.festivos.core.entidades.Festivos;

public interface IFestivosServicio {
    public List<Festivos> listar(int año);

    public Festivos obtener(int mes, int dia);

    public List<Festivos> buscar(int mes, int dia);

    public Festivos agregar(int año);

    public Festivos modificar(String nombre, Festivos nuevosDatos);

    public boolean eliminar();

    public String respuesta(int dia, int mes);

    public List<Festivos> obtenerFestivosPorMesYDia(int mes, int dia);

    public List<Festivos> obtenerFestivosNombre(String nombre);

    
}

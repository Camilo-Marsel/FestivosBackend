package festivos.festivos.aplicacion;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import festivos.festivos.core.entidades.Festivos;
import festivos.festivos.core.entidades.Tipos;
import festivos.festivos.core.interfaces.repositorios.IFestivosRepositorio;
import festivos.festivos.core.interfaces.servicios.IFestivosServicio;

@Service
public class FestivosServicio implements IFestivosServicio {

    private IFestivosRepositorio repositorio;

    public FestivosServicio(IFestivosRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public String respuesta(int dia, int mes) {
        if(repositorio.buscar(mes, dia).isEmpty()){
            return "No es festivo";
        }else{
            return "Es festivo";
        }
    }

    @Override
    public List<Festivos> listar(int año) {
        return repositorio.findAll();
    }

    @Override
    public Festivos obtener(int mes, int dia) {
        return null;
    }

    @Override
    public List<Festivos> obtenerFestivosPorMesYDia(int mes, int dia) {
        return repositorio.findByMesAndDia(mes, dia);
    }

    /*@Override
    public List<Festivos> obtenerFestivosNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }*/
    @Override
    public List<Festivos> buscar(int mes, int dia) {
        
        return repositorio.buscar(mes, dia);
    }
    @Override
    public Festivos agregar(int año) {
        Date domingo = getDomingoRamos(año);
        diasPuentes(año);
        diasDePascua(domingo);

        return null;
    }

    @Override
    public Festivos modificar(String nombre, Festivos nuevosDatos) {
        
        return repositorio.findByNombre(nombre)
                .map(festivoExistente -> {
                    festivoExistente.setMes(nuevosDatos.getMes());
                    festivoExistente.setDia(nuevosDatos.getDia());
                    festivoExistente.setTipos(nuevosDatos.getTipos());
                    return repositorio.save(festivoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Festivo con ID " + nombre + " no encontrado."));
    }

    @Override
    public boolean eliminar() {
        try {
            repositorio.deleteAll();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Date getDomingoRamos(int año) {

        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        int dia = 15 + dias;
        int mes = 3;

        if (dia > 31) {
            dia = dia - 31;
            mes = 4;
        }
        return new Date(año - 1900, mes - 1, dia);

    }
 
    public void diasDePascua(Date domingo){
        Date fecha = domingo;
        Tipos tipo = new Tipos(3, "Basado en Pascua");
        Festivos festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Domingo de Pascua", tipo);
        modificar("Domingo de Pascua", festivos1);

        fecha = agregarDias(fecha, -2);
        festivos1 = new Festivos(fecha.getDay(), -2, 0, fecha.getMonth(), "Viernes Santo", tipo);
        modificar("Viernes Santo", festivos1);

        fecha = agregarDias(fecha, -1);
        festivos1 = new Festivos(fecha.getDay(), -3, 0, fecha.getMonth(), "Jueves Santo", tipo);
        modificar("Jueves Santo", festivos1);

        tipo = new Tipos(4, "Basado en Pascua y Ley Puente Festivo");
        fecha = domingo;
        fecha = agregarDias(fecha, 40);
        fecha = siguienteLunes(fecha);
        festivos1 = new Festivos(fecha.getDay(), 40, 0, fecha.getMonth(), "Ascensión del Señor", tipo);
        modificar("Ascensión del Señor", festivos1);

        fecha = domingo;
        fecha = agregarDias(fecha, 61);
        fecha = siguienteLunes(fecha);
        festivos1 = new Festivos(fecha.getDay(), 61, 0, fecha.getMonth(), "Corpus Christi", tipo);
        modificar("Corpus Christi", festivos1);

        fecha = domingo;
        fecha = agregarDias(fecha, 68);
        fecha = siguienteLunes(fecha);
        festivos1 = new Festivos(fecha.getDay(), 68, 0, fecha.getMonth(), "Sagrado Corazón de Jesús", tipo);
        modificar("Sagrado Corazón de Jesús", festivos1);
    }

    @SuppressWarnings("deprecation")
    public void diasPuentes(int año) {
        Tipos tipo = new Tipos(1, "Fijo");
        Date fecha = new Date(año + 1900, 01, 01);
        Festivos festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Año nuevo", tipo);
        modificar(festivos1.getNombre(), festivos1);
        
        fecha = new Date(año + 1900, 5, 01);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Día del Trabajo", tipo);
        modificar(festivos1.getNombre(), festivos1);

        fecha = new Date(año + 1900, 7, 20);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Independencia Colombia", tipo);
        modificar(festivos1.getNombre(), festivos1);

        fecha = new Date(año + 1900, 8, 7);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Batalla de Boyacá", tipo);
        modificar(festivos1.getNombre(), festivos1);

        fecha = new Date(año + 1900, 12, 8);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Inmaculada Concepción", tipo);
        modificar(festivos1.getNombre(), festivos1);

        fecha = new Date(año + 1900, 12, 25);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Navidad", tipo);
        modificar(festivos1.getNombre(), festivos1);


        tipo = new Tipos(2, "Ley Puente Festivo");
        fecha = new Date(año + 1900, 06, 01);
        fecha = siguienteLunes(fecha);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Santos Reyes", tipo);
        modificar(festivos1.getNombre(), festivos1);

        fecha = new Date(año + 1900, 19, 03);
        fecha = siguienteLunes(fecha);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "San José", tipo);
        modificar(festivos1.getNombre(), festivos1);

        fecha = new Date(año + 1900, 29, 06);
        fecha = siguienteLunes(fecha);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "San Pedro y San Pablo", tipo);
        modificar(festivos1.getNombre(), festivos1);

        fecha = new Date( año + 1900, 15, 8);
        fecha = siguienteLunes(fecha);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Asunción de la Virgen", tipo);
        modificar(festivos1.getNombre(), festivos1);

        fecha = new Date( año + 1900, 12, 10);
        fecha = siguienteLunes(fecha);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Día de la Raza", tipo);
        modificar(festivos1.getNombre(), festivos1);

        fecha = new Date( año + 1900, 11, 01);
        fecha = siguienteLunes(fecha);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Todos los santos", tipo);
        modificar(festivos1.getNombre(), festivos1);

        fecha = new Date( año + 1900, 11, 11);
        fecha = siguienteLunes(fecha);
        festivos1 = new Festivos(fecha.getDay(), 0, 0, fecha.getMonth(), "Independencia de Cartagena", tipo);
        modificar(festivos1.getNombre(), festivos1);
        
    }

    public static Date agregarDias(Date fecha, int dias) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha);

        calendar.add(Calendar.DATE, dias);

        return calendar.getTime();

    }

    public static Date siguienteLunes(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        if (calendar.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY) {
            fecha = agregarDias(fecha, 9 - calendar.get(Calendar.DAY_OF_WEEK));
        } else {
            fecha = agregarDias(fecha, 1);
        }
        return fecha;
    }

    @Override
    public List<Festivos> obtenerFestivosNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  
}

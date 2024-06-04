package festivos.festivos.presentacion;


import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import festivos.festivos.core.entidades.Festivos;
import festivos.festivos.core.interfaces.servicios.IFestivosServicio;

@RestController
@RequestMapping("api/festivos")
public class FestivosControlador {
    private IFestivosServicio servicio;

    public FestivosControlador(IFestivosServicio servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(value = "/fecha/{año}/{mes}/{dia}", method = RequestMethod.GET)
    public String obtener(@PathVariable Integer año, @PathVariable Integer mes, @PathVariable Integer dia) {
        servicio.agregar(año);
        if (dia > 31 || mes > 12 || mes < 1 || dia < 1){
            return "Fecha No valida";
        }else{
            if(servicio.obtenerFestivosPorMesYDia(mes, dia).isEmpty()){
                return "No es festivo";
            }else{
                return "Es festivo";
            }
        }        
    }

    @RequestMapping(value = "/fecha/{año}", method = RequestMethod.GET)
    public List<Festivos> lista (@PathVariable Integer año) {
        servicio.agregar(año);
        return servicio.listar(año);
    }

}

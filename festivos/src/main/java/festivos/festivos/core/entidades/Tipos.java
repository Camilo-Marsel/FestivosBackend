package festivos.festivos.core.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "tipos")
public class Tipos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tipos")
    @GenericGenerator(name = "secuencia_tipos", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "tipo", length = 100)
    private String nombre;
    
    public Tipos() {
    }

    public Tipos(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return nombre;
    }

    public void setTipo(String nombre) {
        this.nombre = nombre;
    }



}

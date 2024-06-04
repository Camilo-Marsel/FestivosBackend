package festivos.festivos.core.entidades;


import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Festivo")
public class Festivos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_festivos")
    @GenericGenerator(name = "secuencia_festivos", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "dia")
    private int dia;

    @Column(name = "mes")
    private int mes;

    @Column(name = "diaspascua")
    private int diasPascua;

    @ManyToOne
    @JoinColumn(name="idtipo", referencedColumnName = "id")
    private Tipos tipos;

    public Festivos() {
    }

    public Festivos(int dia, int diasPascua, int id, int mes, String nombre, Tipos tipo) {
        this.dia = dia;
        this.diasPascua = diasPascua;
        this.id = id;
        this.mes = mes;
        this.nombre = nombre;
        this.tipos = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDiasPascua() {
        return diasPascua;
    }

    public void setDiasPascua(int diasPascua) {
        this.diasPascua = diasPascua;
    }

    public Tipos getTipos() {
        return tipos;
    }

    public void setTipos(Tipos tipos) {
        this.tipos = tipos;
    }

}

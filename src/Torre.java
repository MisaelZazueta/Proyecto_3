//Desarrollado por Ing. Jose Misael Burruel Zazueta
//Version 1.0
//MC Computacionales Instituto Tecnologico de Culiacan
import java.util.ArrayList;
public class Torre {
    private String nombre;
    private ArrayList<Torre> conexiones;

    public Torre (String nombre){
        this.nombre = nombre;
        conexiones = new ArrayList<>();
    }

    public Torre() {

    }

    public boolean comparar(Torre torre){
        if(nombre.compareToIgnoreCase(torre.toString()) == 0)
            return true;
        return false;
    }
    public void agregarConexion(Torre torre){
        conexiones.add(torre);
    }
    public void eliminarConexion(Torre torre){ conexiones.remove(torre); }

    @Override
    public String toString() {
        return nombre;
    }
    public ArrayList<Torre> getConexiones() {
        return conexiones;
    }

    public String getNombre() {
        return nombre;
    }
}

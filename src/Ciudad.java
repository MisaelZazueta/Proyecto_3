//Desarrollado por Ing. Jose Misael Burruel Zazueta
//Version 1.0
//MC Computacionales Instituto Tecnologico de Culiacan
import java.util.ArrayList;

public class Ciudad {
    private ArrayList<Torre> torres;

    public Ciudad(){
        torres = new ArrayList<>();
    }

    public Torre crearTorre(String nombre) {
        Torre torre = new Torre(nombre);
        boolean x = false;
        for (Torre registrada : torres)
            if (registrada.comparar(torre)) {
                x = true;
                torre = registrada;
                break;
            }
        if(!x)
            torres.add(torre);
        if(torres.size() == 0)
            torres.add(torre);
        return torre;
    }
    public void crearConexion(Torre torreA, Torre torreB){
        torreA.agregarConexion(torreB);
    }
    public void eliminarConexion(Torre torreA, Torre torreB){
        torreA.eliminarConexion(torreB);
    }
    public void buscarConexion (Torre torreA, Torre torreB) {
        ArrayList<Torre> busqueda = new ArrayList<>();
        ArrayList<Torre> clon = new ArrayList<>();
        Torre torreAux = null;
        boolean  x = true;
        for (int i = 0; i < torres.size(); i++) {
            String nombre = torres.get(i).getNombre();
            Torre torrei = new Torre(nombre);
            clon.add(torrei);
            for (int j = 0; j < torres.get(i).getConexiones().size(); j++) {
                String nombreA = torres.get(i).getConexiones().get(j).getNombre();
                Torre torrej = new Torre(nombreA);
                clon.get(i).getConexiones().add(torrej);
            }
        }
        do{
            if(x)
                for (Torre registrada : clon)
                    if (registrada.comparar(torreA)) {
                        torreA = registrada;
                        busqueda.add(torreA);
                        break;
                    }
            if (!torreA.getConexiones().isEmpty()) {
                for (Torre registrada : torreA.getConexiones()) {
                    if (registrada.comparar(torreB)) {
                        torreB = registrada;
                        busqueda.add(torreB);
                        System.out.print("+ ");
                        for (int k = 0; k < busqueda.size(); k++) {
                            if (!(k == (busqueda.size() - 1)))
                                System.out.print(busqueda.get(k) + " => ");
                        }
                        System.out.println(busqueda.get(busqueda.size() - 1));
                        busqueda.clear();
                        break;
                    }
                }
                torreAux = torreA;
                torreA = torreA.getConexiones().get(0);
                x = true;
                }
            else{
                if (torreAux != null){
                    Torre torreC = torreA;
                    torreA = torreAux;
                    busqueda.remove(torreC);
                    busqueda.remove(torreA.getConexiones().get(0));
                    //torreA.getConexiones().remove(torreC);
                    clon.get(clon.indexOf(torreA)).getConexiones().remove(0);
                    x = false;
                    if(torreA.getConexiones().isEmpty()) {
                        torreAux = torreA;
                        if(torreAux != torreA || busqueda.size() > 1) {
                            busqueda.remove(torreAux);
                            torreA = busqueda.get(busqueda.size() - 1);
                            torreA.getConexiones().remove(torreAux);
                        }
                        else
                        {
                            torreAux = null;
                            busqueda.clear();
                        }
                        //y = clon.indexOf(torreA);
                        //clon.get(y).getConexiones().remove(torreAux);
                        if(busqueda.size() >= 2)
                            torreAux = busqueda.get(busqueda.size() -2);
                    }
                }
                else {
                    torreA = busqueda.get(0);
                    busqueda.clear();
                }
           }
        }while(!busqueda.isEmpty());
        if (torreAux == null)
            System.out.println("- " + torreA + " => " + torreB);
    }
    public boolean existeTorre(Torre torre) {
        for (Torre registrada : torres)
            if (registrada.comparar(torre))
                return true;
        return false;
    }
}
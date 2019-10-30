//Desarrollado por Ing. Jose Misael Burruel Zazueta
//Version 1.0
//MC Computacionales Instituto Tecnologico de Culiacan
import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Principal {

    public static void main(String[] args) {
        File fichero = new File("C:\\Users\\Usuari0\\IdeaProjects\\Proyecto_3\\src\\archivo.txt");
        Scanner s = null;
        Ciudad ciudad = new Ciudad();
        try {
            s = new Scanner(fichero);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                Pattern pat = Pattern.compile("(([aA-zZ]+[aA-zZ0-9]*)[\\s]*(<-|->|=>|<=|-)[\\s]*([aA-zZ]+[aA-zZ0-9]*)[\\s]*([.-?])[\\s]*(.*))");
                Matcher mat = pat.matcher(linea);
                if(mat.find() && mat.matches()){
                    String operador = mat.group(3);
                    String fin = mat.group(5);
                    String torre1 = mat.group(2).toLowerCase();
                    String torre2 = mat.group(4).toLowerCase();
                    if(torre1 != torre2){
                        if(fin.equals(".")){
                            Torre torreA = ciudad.crearTorre(torre1);
                            Torre torreB = ciudad.crearTorre(torre2);
                            switch (operador) {
                                case "-":
                                    ciudad.eliminarConexion(torreA, torreB);
                                    break;
                                case "->":
                                    ciudad.crearConexion(torreA, torreB);
                                    break;
                                case "<-":
                                    ciudad.crearConexion(torreB, torreA);
                                    break;
                            }
                        }
                        if(fin.equals("?")){
                            Torre torreA = new Torre(torre1);
                            Torre torreB = new Torre(torre2);
                            boolean torreC = ciudad.existeTorre(torreA);
                            boolean torreD = ciudad.existeTorre(torreB);
                            if(torreC && torreD){
                            switch (operador) {
                                case "=>":
                                    ciudad.buscarConexion(torreA, torreB);
                                    break;
                                case "<=":
                                    ciudad.buscarConexion(torreB, torreA);
                                    break;
                                }
                            }
                            else{
                                System.out.println("- " + torreA + " => " + torreB);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Linea incorrecta");
        } finally {
            try {
                if (s != null)
                    s.close();
            } catch (Exception ex2) {
                System.out.println("Mensaje 2: " + ex2.getMessage());
            }
        }
    }
}
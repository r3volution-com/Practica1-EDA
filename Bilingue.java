import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bilingue {
    public static void main(String[] args){
        if (args.length == 3){
            DiccA dicc = new DiccA();
            FileReader fr;
            BufferedReader br;
            String traducido = "", linea, delimiter = "((?<=[,:;?!\\.() ]+)|(?=[,:;?!\\.() ]+))";
            String[] palabras;
            float contGuiones = 0, contPalabras = 0;
            char idioma;
            char[] lenguas;
            boolean existeIdioma = false;
            dicc.leeDiccA(args[0]);
            idioma = args[2].charAt(0);
            lenguas = dicc.getIdiomas();
            for (int i = 0; i < lenguas.length; i++){
                if (lenguas[i] == idioma) existeIdioma = true;
            }
            try {
                fr = new FileReader(args[1]);
                br = new BufferedReader(fr);
                linea = br.readLine();
                while (linea != null){
                    palabras = linea.split(delimiter);
                    if (existeIdioma == true){
                        for (int i = 0; i < palabras.length; i++){
                            //System.out.println(palabras[i]);
                            if (palabras[i].matches("[a-zA-Z]*")) {
                                String termino = dicc.traduce1(palabras[i], idioma);
                                contPalabras++;
                                if (termino == null) {
                                    traducido += "-";
                                    contGuiones++;
                                }
                                else traducido += termino;
                            }
                            else traducido += palabras[i];
                        }
                        traducido += "\n";
                    }
                    linea = br.readLine();
                }
                int porcentaje = (int)(100-(contGuiones/contPalabras)*100);
                System.out.println(traducido);
                System.out.println(porcentaje+"%");
                br.close();
                fr.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Bilingue.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Bilingue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
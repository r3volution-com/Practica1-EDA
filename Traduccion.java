
public class Traduccion {
	private char idioma;
	private String traducciones[];
	public Traduccion(char idioma){
		this.idioma = idioma;
		traducciones = new String[5];
	}
	public char getIdioma(){
		return idioma;
	}
	public boolean setTraduccion(String traduccion){
		int pri = -1;
		boolean found = false;
		for (int i = 0; i < traducciones.length && found == false; i++){
            if (traducciones[i] != null && traducciones[i].compareToIgnoreCase(traduccion) == 0) found = true;
		}
		for (int i = 0; i < traducciones.length && pri == -1; i++){
            if (traducciones[i] == null) pri = i;
		}
		if (traduccion != null && traduccion.equals("") == false && pri >= 0 && !found) {
			traducciones[pri] = traduccion;
			return true;
		}
		return false;
	}
	public boolean setTraduccion(String[] traduccion){
        boolean inserta = false;
		int pri = -1;
		boolean found = false;
		for (int i = 0; i < traducciones.length && pri == -1; i++){
			if (traducciones[i] == null) pri = i;
		}
		if (pri >= 0) {
			for (int i = 0; i < traduccion.length; i++){
				found = false;
				for (int j = 0; j < traducciones.length; j++){
					if (traducciones[j] != null && traduccion[i] != null && traducciones[j].compareToIgnoreCase(traduccion[i]) == 0) found = true;
				}
				if (pri < traducciones.length && !found){
                	if (traducciones[pri] == null && traduccion[i] != null) {
                        traducciones[pri] = traduccion[i];
                        inserta = true;
                        pri++;
                    }
				}
			}
		}
		return inserta;
	}
	public String getTraduccion(){
		return traducciones[0];
	}
	public String[] getArrayTraducciones(){
		return traducciones;
	}
	public String getTraducciones(){
		String newstring = "";
		for (int i = 0; i < traducciones.length; i++){
			if (traducciones[i] != null){
				if (i == 0) newstring += traducciones[i];
				else newstring += "/"+traducciones[i];
			}
		}
		return newstring;
	}
}

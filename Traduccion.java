
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
		for (int i = 0; i < traducciones.length; i++){
			if (traducciones[i] == null){ 
				if (pri < 0) pri = i;
			}else if (traducciones[i].compareToIgnoreCase(traduccion) == 0) found = true;
		}
		if (pri >= 0 && !found) {
			traducciones[pri] = traduccion;
            //System.out.println(pri+" -- "+getTraducciones()+" -- "+idioma);
			return true;
		}
		return false;
	}
	public void setTraduccion(String[] traduccion){
		int pri = -1;
		boolean found = false;
		for (int i = 0; i < traducciones.length; i++){
			if (traducciones[i] == null && pri < 0) pri = i;
		}
		if (pri > 0) {
			for (int i = 0; i < traduccion.length; i++){
				found = false;
				for (int j = 0; j < traducciones.length; j++){
					if (traducciones[j] != null && traduccion[i] != null && traducciones[j].compareToIgnoreCase(traduccion[i]) == 0) found = true;
				}
				if (pri < traducciones.length && !found){
                    // System.out.println(traduccion[i]);
					if (traduccion[i] != null) traducciones[pri] = traduccion[i];
					pri++;
				}
			}
		}
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
        //System.out.println("SY:"+traducciones[i]);
				if (i == 0) newstring += traducciones[i];
				else newstring += "/"+traducciones[i];
			}
		}
        System.out.println(idioma+" : "+newstring);
		return newstring;
	}
}

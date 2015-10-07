
public class Palabra {
	private String origen;
	private Traduccion[] trad;
	public Palabra(String p, int nl){
		origen = p;
		if (nl <= 0) nl = 3;
		trad = new Traduccion[nl];
	}
	public int setTrad(Traduccion t, char l){
		int aparece = -1;
        //System.out.println(origen+" "+l+" "+t.getTraducciones());
		if (t != null){
            for (int i = 0; i < trad.length && aparece == -1 ; i++){
                if (trad[i] != null && trad[i].getIdioma() == l){
                    aparece = i;
                }
            }
			if (aparece == -1){
                int primLibre = -1;
                for (int i = 0; i < trad.length && primLibre == -1; i++){
                    if (trad[i] == null){
                        primLibre = i;
                    }
                }
                if (primLibre != -1){
                    trad[primLibre] = new Traduccion(l);
                    trad[primLibre].setTraduccion(t.getArrayTraducciones());
                    aparece = primLibre;
                }
			} else {
                trad[aparece].setTraduccion(t.getArrayTraducciones());
            }
		} 
		return aparece;
	}
	public String getOrigen(){
		return origen;
	}
	public String getTraduccion(char l){
		for (int i = 0; i < trad.length; i++){
			if (trad[i].getIdioma() == l){
				return trad[i].getTraduccion();
			}
		}
		return null;
	}
	public String getTraducciones(char l){
		for (int i = 0; i < trad.length; i++){
			if (trad[i].getIdioma() == l){
				return trad[i].getTraducciones();
			}
		}
		return null;
	}
	public Traduccion[] getArrayTraducciones(){
		return trad;
	}
	public boolean agregaAcepcion(String s, char l){
		int aparece = -1;
		int posLibre = -1;
        if (s != null){
            for (int i = 0; i < trad.length && aparece == -1; i++){
                if (trad[i] != null && trad[i].getIdioma() == l) {
                    aparece = i;
                }
            }
            for (int i = 0; i < trad.length && posLibre == -1; i++){
                if (trad[i] == null) posLibre = i;
            }
            if (aparece == -1 && posLibre != -1){
                trad[posLibre] = new Traduccion(l);
                aparece = posLibre;
            }
            if (aparece != -1) {
            	 boolean ret = trad[aparece].setTraduccion(s);
                 return ret;
            }
        }
        return false;
	}
	public void escribeInfo(){
		String frase = "";
		for (int i = 0; i < trad.length; i++){
			if (trad[i] != null) frase += ":"+trad[i].getTraducciones();
		}
		System.out.println(origen+frase);
	}
	public void escribeInfo(char l){
		String frase = "";
		for (int i = 0; i < trad.length; i++){
			if (trad[i] != null && trad[i].getIdioma() == l) frase += ":"+trad[i].getTraducciones();
		}
		System.out.println(origen+frase);
	}
	
}


public class Palabra {
	private String origen;
	private Traduccion[] trad;
	public Palabra(String p, int nl){
		origen = p;
		if (nl <= 0) nl = 3;
		trad = new Traduccion[nl];
	}
	public int setTrad(Traduccion t, char l){
		int i_pos = -1;
		if (t != null){
			for (int i = 0; i < trad.length; i++){
				if (trad[i].getIdioma() == l && i_pos == -1){
					i_pos = i;
					trad[i].setTraduccion(t.getArrayTraducciones());
				}
			}
			if (i_pos == -1){
				for (int i = 0; i < trad.length; i++){
					if (trad[i] == null && i_pos == -1){
						i_pos = i;
						trad[i] = new Traduccion(l);
						trad[i].setTraduccion(t.getArrayTraducciones());
					}
				}
			}
		} 
		return i_pos;
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
	public boolean agregaAcepcion(String s, char l){
		
		return true;
	}
	public void escribeInfo(){
		
	}
	
}

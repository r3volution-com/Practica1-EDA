
public class DiccA {
	private int nlenguas;
	private char[] lenguas;
	private Palabra[] dicc;
	private Palabra[] diccord;
	public DiccA() {
		nlenguas = -1;
		dicc = new Palabra[10];
		diccord = new Palabra[10];
	}
	public void leeDiccA(String f){
	}
	public int redimensionaArrays(){
		Palabra[] newdic = new Palabra[dicc.length];
		Palabra[] newdicord = new Palabra[diccord.length];
		System.arraycopy(dicc, 0, newdic, 0, dicc.length );
		System.arraycopy(diccord, 0, newdicord, 0, diccord.length );
		return dicc.length+1;
	}
	public boolean insertaPalabra(Palabra p){
		if (p != null) {
			int aparece = -1;
			for (int i = 0; i < dicc.length && aparece == -1; i++){
				if (dicc[i] != null && dicc[i].getOrigen().equalsIgnoreCase(p.getOrigen()) == true) aparece = i;
			}
			if (aparece != -1){
				int posLibre = -1;
				int posLibreOrd = -1;
				for (int i = 0; i < dicc.length && posLibre == -1; i++){
					if (dicc[i] != null) posLibre = i;
				}
				for (int i = 0; i < diccord.length && posLibreOrd == -1; i++){
					if (diccord[i] != null) posLibreOrd = i;
				}
				if (posLibre != -1) {
					dicc[posLibre] = p;
					diccord[posLibreOrd] = p;
					//Reordenar array diccord
				} else {
					posLibre = redimensionaArrays();
					dicc[posLibre] = p;
					diccord[posLibreOrd] = p;
					//Reordenar array diccord
				}
			} else {
				Traduccion[] tr = p.getArrayTraducciones();
				for (int i = 0; i<tr.length;i++){
					dicc[aparece].setTrad(tr[i], tr[i].getIdioma());
				}
			}
		}
		return false;
	}
	public boolean borraPalabra(String s){
		if (s != null) {
			int aparece = -1;
			int apareceord = -1;
			for (int i = 0; i < dicc.length && aparece == -1; i++){
				if (dicc[i] != null && dicc[i].getOrigen().equalsIgnoreCase(s) == true) aparece = i;
			}
			for (int i = 0; i < diccord.length && apareceord == -1; i++){
				if (diccord[i] != null && diccord[i].getOrigen().equalsIgnoreCase(s) == true) apareceord = i;
			}
			if (aparece != -1 && apareceord != -1){
				dicc[aparece] = null;
				diccord[apareceord] = null;
				for (int i = aparece; i< dicc.length-1; i++){
					dicc[i] = dicc[i+1];
				}
			}
		}
		return true;
	}
	public int busqueda(String s){
		for (int i = 0; i< dicc.length; i++){
			if (dicc[i].getOrigen().equalsIgnoreCase(s)) return i;
		}
		return -1;
	}
	public int busquedaOptima(String s){
		
		return 0;
	}
	public String traduce1(String s, char l){
		for (int i = 0; i< dicc.length; i++){
			if (dicc[i].getOrigen().equalsIgnoreCase(s)) {
				return dicc[i].getTraduccion(l);
			}
		}
		return null;
	}
	public String traduce2(String s, char l){
		for (int i = 0; i< dicc.length; i++){
			if (dicc[i].getOrigen().equalsIgnoreCase(s)) {
				return dicc[i].getTraducciones(l);
			}
		}
		return null;
	}
	public void muestraDiccA(int i){
		
	}
	public void muestraDiccA(int i, int j){
		
	}
	public void muestraDiccA(int i, int j, char l){
		
	}
}

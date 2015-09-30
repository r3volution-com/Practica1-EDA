
public class DiccA {
	private int nlenguas;
	private char[] lenguas;
	private Palabra[] dicc;
	private Palabra[] diccord;
	public DiccA() {
		nlenguas = -1;
	}
	public void leeDiccA(String f){
		nlenguas = -1;
		dicc = new Palabra[10];
	}
	public boolean insertaPalabra(Palabra p){
		if (p != null) {
			int aparece = -1;
			for (int i = 0; i < dicc.length && aparece == -1; i++){
				if (dicc[i] != null && dicc[i].getOrigen().equalsIgnoreCase(p.getOrigen()) == true) aparece = i;
			}
			if (aparece != -1){
				int posLibre = -1;
				for (int i = 0; i < dicc.length && posLibre == -1; i++){
					if (dicc[i] != null) posLibre = i;
				}
				
			}
		}
		return false;
	}
	public boolean borraPalabra(String s){
		
		return true;
	}
	public int busqueda(String s){
		
		return 0;
	}
	public int busquedaOptima(String s){
		
		return 0;
	}
	public String traduce1(String s, char l){
		
		return "";
	}
	public String traduce2(String s, char l){
		
		return "";
	}
	public void muestraDiccA(int i){
		
	}
	public void muestraDiccA(int i, int j){
		
	}
	public void muestraDiccA(int i, int j, char l){
		
	}
}


public class Palabra {
	private String origen;
	private Traduccion[] trad;
	public Palabra(String p, int nl){
		origen = p;
		if (nl <= 0) nl = 3;
		trad = new Traduccion[3];
	}
	public int setTrad(Traduccion t, char l){
		
		return 0;
	}
	public String getOrigen(){
		return origen;
	}
	public String getTraduccion(char l){
		
		return "";
	}
	public String getTraducciones(char l){
		
		return "";
	}
	public boolean agregaAcepcion(String s, char l){
		
		return true;
	}
	public void escribeInfo(){
		
	}
	
}

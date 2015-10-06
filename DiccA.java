import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
		if (f!= null){
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String lineaa=br.readLine();
				
				if(lineaa!=null){
					this.nlenguas = Integer.parseInt(lineaa);
					this.lenguas = new char[nlenguas];
					String lenguas = br.readLine();
					String[] arraylenguas = lenguas.split("[ ]");
					if (arraylenguas != null){
						for (int i = 0; i< arraylenguas.length && i < nlenguas; i++){
							if (arraylenguas[i] != null && arraylenguas[i].length() > 0) this.lenguas[i] = arraylenguas[i].charAt(0);
							//System.out.println(i+" ES: "+arraylenguas[i]);
						}
						String linea = br.readLine();
						String[] palabras;
						while(linea != null) {
							palabras = linea.split("[ ]*\\*[ ]*");
							if (palabras[0] != null){
								Palabra p = new Palabra(palabras[0], nlenguas);
								String[] acepciones;
								for (int i = 1; i < palabras.length; i++){
                                    if (palabras[i] != null && palabras[i].equals("") == false){
                                        acepciones = palabras[i].split("/");
                                        for (int j = 0; j < acepciones.length; j++){
                                            if (palabras[0].equals("upstairs") == true) System.out.println((i-1)+" "+this.lenguas[i-1]+" "+acepciones[j]);
                                            if (p.agregaAcepcion(acepciones[j], this.lenguas[i-1]) == false) {}
                                        }
                                    }
								}
								if (insertaPalabra(p)) {}
							}
							linea = br.readLine();
						}
					}
					br.close();
					fr.close();
				}
				
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public int redimensionaArrays(){
		int oldlength = dicc.length;
		Palabra[] newdic = new Palabra[dicc.length+10];
		Palabra[] newdicord = new Palabra[diccord.length+10];
		System.arraycopy(dicc, 0, newdic, 0, dicc.length );
		System.arraycopy(diccord, 0, newdicord, 0, diccord.length );
		dicc = newdic;
		diccord = newdicord;
		return oldlength;
	}
	public void reordenaArray(Palabra[] olddicc) {
		Palabra variableauxiliar;
		for (int i = 0; i<olddicc.length; i++){
			for (int j = 0; j<olddicc.length; j++){
				if(olddicc[i] != null && olddicc[i].getOrigen() != null && olddicc[j] != null && olddicc[i].getOrigen().compareToIgnoreCase(olddicc[j].getOrigen())<0){
                    variableauxiliar=olddicc[i];
                    olddicc[i]=olddicc[j];
                    olddicc[j]=variableauxiliar;
                }
			}
		}
	}
	public boolean insertaPalabra(Palabra p){
		if (p != null) {
			int aparece = -1;
			for (int i = 0; i < dicc.length && aparece == -1; i++){
				if (dicc[i] != null && dicc[i].getOrigen().equalsIgnoreCase(p.getOrigen()) == true) aparece = i;
			}
			if (aparece == -1){
				int posLibre = -1;
				int posLibreOrd = -1;
				for (int i = 0; i < dicc.length && posLibre == -1; i++){
					if (dicc[i] == null) posLibre = i;
				}
				for (int i = 0; i < diccord.length && posLibreOrd == -1; i++){
					if (diccord[i] == null) posLibreOrd = i;
				}
				if (posLibre != -1) {
					dicc[posLibre] = p;
					diccord[posLibreOrd] = p;
					reordenaArray(diccord);
                    return true;
				} else {
					posLibre = redimensionaArrays();
					//System.out.println("ES "+dicc.length+" "+posLibre);
					dicc[posLibre] = p;
					diccord[posLibre] = p;
					reordenaArray(diccord);
                    return true;
				}
			} else {
				Traduccion[] tr = p.getArrayTraducciones();
				for (int i = 0; i<tr.length;i++){
					if (tr[i] != null && dicc[aparece] != null) {
                        //System.out.println(tr[i].getTraducciones());
                        dicc[aparece].setTrad(tr[i], tr[i].getIdioma());
                        return true;
                    }
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
		if(s!=null){
			int mitad, min, max;
			max=diccord.length-1;
			min = 0;
			for (int i = 0; i < diccord.length && min <= max; i++){
				mitad = (min+max)/2;
				if (diccord[mitad].getOrigen().compareToIgnoreCase(s) == 0) return i;
				else if (diccord[mitad].getOrigen().compareToIgnoreCase(s) < 0) max = mitad-1;
				else if (dicc[mitad].getOrigen().compareToIgnoreCase(s) > 0) min = mitad+1;
			}
		}
		return -1;
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
		if (i == 0){
			for (int j = 0; j < dicc.length; j++) {
				if (dicc[j] != null) dicc[j].escribeInfo();
			}
		} else if (i == 1){
			for (int j = 0; j < diccord.length; j++) {
				if (dicc[j] != null) diccord[j].escribeInfo();
			}
		}
	}
	public void muestraDiccA(int i, int j){
		if (j > dicc.length) j = dicc.length;
		if (i == 0){
			for (int k = 0; k < j; k++) {
				if (dicc[k] != null) dicc[k].escribeInfo();
			}
		} else if (i == 1){
			for (int k = 0; k < j; k++) {
				if (dicc[k] != null) diccord[k].escribeInfo();
			}
		}
	}
	public void muestraDiccA(int i, int j, char l){
		if (j > dicc.length) j = dicc.length;
		if (i == 0){
			for (int k = 0; k < j; k++) {
				if (dicc[k] != null) dicc[k].escribeInfo(l);
			}
		} else if (i == 1){
			for (int k = 0; k < j; k++) {
				if (dicc[k] != null) diccord[k].escribeInfo(l);
			}
		}
	}
}

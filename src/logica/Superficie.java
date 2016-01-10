package logica;

import java.io.IOException;
import java.util.Scanner;

import celula.Celula;
import celula.CelulaCompleja;
import celula.CelulaSimple;
import excepciones.PalabraIncorrecta;

public class Superficie{
	private Celula[][] superficie;
	private int filas;
	private int columnas;
		
	/**
	 * Constructor de la clase superficie
	 * @param nf valor entero positivo que indica el numero de filas de la superficie
	 * @param nc valor entero positivo que indica el numero de columnas de la superficie
	 */
	public Superficie(int nf, int nc){
		this.filas = nf;
		this.columnas = nc;
		this.superficie = new Celula[this.filas][this.columnas];
        this.reset();         
    }
	
    /**
     * Recorre casilla a casilla la matriz de celulas y las junta en una cadena para mostrar por consola
     * @return La matriz como un string
     */
	public StringBuffer toStringBuffer() {
		StringBuffer matriz = new StringBuffer("");
		for(int i = 0; i < this.filas; i++){
    		for(int j = 0; j < this.columnas; j++){
    			matriz.append(" "); 
    			if(!casillaVacia(i, j)){    				
    				matriz.append(superficie[i][j].toStringBuffer());    				
    			}
    			else
    				matriz.append(" - ");
		    }
    		matriz.append(System.getProperty("line.separator"));
		}
		return matriz;
	}		
	
	/**
	 * Busco las posiciones vacias que hay alrededor de la celula, las guarda en un array de tipo casilla y elijo aleatoriamente
	 * una de las posiciones de todas las disponibles para devolver
	 * @param f Entero que representa la fila de la celula
	 * @param c Entero que representa la columna de la celula
	 * @return posicion en el que se encuentra la casilla seleccionada aleatoriamente
	 */
	public CasillaMensaje ejecutaMovimiento(int f, int c){
		return superficie[f][c].ejecutaMovimiento(f,c, this);
		
	}
	
	/**
	 * Procedimiento que pone a NULL todas las casillas de la superficie
	 */
    public void reset(){
    	for(int i = 0; i < this.filas; i++){
    		for(int j = 0; j < this.columnas; j++){
    			this.superficie[i][j] = null;
		    }
		}		 
	}  
    
	/**
	 * Mira si la casilla pasada por parametro esta vacia
	 * @param f valor entero positivo que indica el numero de filas
	 * @param c valor entero positivo que indica el numero de columnas
	 * @return TRUE si la casilla esta vacia. FALSE para el caso contrario
	 */
	public boolean casillaVacia(int f, int c){
		return this.superficie[f][c] == null;
	}

	/**
	 * Vacia la casilla pasada por parametro si no esta vacia ya y existe
	 * @param f valor entero positivo acotado en un rango valido del numero de filas
	 * @param c valor entero positivo acotado en un rango valido del numero de columnas
	 * @return TRUE si vacio la casilla. FALSE para el caso contrario
	 */
	public boolean vaciarCasilla(int f, int c){
		boolean ok = false;
		if(!casillaVacia(f, c)){
			this.superficie[f][c] = null;
			ok = true;
		}
		return ok;
	}

	/**
	 * Metodo que genera una celula nueva si la casilla esta vacia
	 * @param f valor entero positivo acotado en un rango valido del numero de filas
	 * @param c valor entero positivo acotado en un rango valido del numero de columnas
	 * @param celula le pasa una celula de la clase Celula
	 * @return true si la casilla esta llena y false si esta vacia
	 */
	public boolean llenarCasilla(int f, int c, Celula celula){
		boolean ok = false;
		if(casillaVacia(f, c)){
			this.superficie[f][c] = celula;
			ok = true;
		}
		return ok;
	}	
	
	/**
	 * Metodo que devuelve el valor entero positivo de las filas de la Superficie
	 * @return valor entero positivo de las filas 
	 */
	public int getFilas(){
		return this.filas;
	}
	
	/**
	 * Metodo que devuelve el valor entero positivo de las columnas de la Superficie
	 * @return valor entero positivo de las columnas 
	 */
	public int getColumnas(){
		return this.columnas;
	}	
	
	/**
	 * Metodo que llama a la clase Superficie e iguala la posicion actual
	 * a la nueva en la que se encuentra la celula. Y la antigua la vacia
	 * @param f entero positivo que hace referencia a la fila nueva 
	 * @param c entero positivo que hace referencia a la columna nueva
	 * @param i entero positivo que hace referencia a la fila antigua
	 * @param j entero positivo que hace referencia a la columna antigua
	 */
	public void moverCelula(int f, int c, int i, int j){
		this.superficie[f][c] = this.superficie[i][j];
		this.superficie[i][j] = null;		
	}
	
	/**
	 * Metodo booleano que llama a esComestible de la Superficie
	 * @param i entero positivo que hace referencia a la fila nueva 
	 * @param j entero positivo que hace referencia a la columna nueva
	 * @return Si es comestible la celula
	 */
	public boolean esComestible(int i, int j) {
		return superficie[i][j].esComestible();
	}
	
	/**
	 * Abre el fichero juego.txt, carga las dimensiones del tablero del fichero y las celulas que habia en un nuevo mundo
	 * @param in pasa los controles ya inicializados
	 * @return El nuevo mundo que hemos cargado del fichero
	 * @throws IOException para evitar errores de cargado y guardado
	 */
	public int[] cargar(Scanner entrada, boolean complejo)throws PalabraIncorrecta{
		//celulas[0] = celulas simples, celulas[1] = celulas complejas
		int[] celulas = {0,0};
		//Mientras que no llegue al final del archivo
		while (entrada.hasNext()){
			int f = entrada.nextInt(), c = entrada.nextInt();
			String tipo = entrada.next();
			if (tipo.equalsIgnoreCase("simple")){
				this.superficie[f][c] = new CelulaSimple();
				celulas[0]++;
			}
			else if (tipo.equalsIgnoreCase("compleja") && complejo){
				this.superficie[f][c] = new CelulaCompleja();
				celulas[1]++;
			}
			else {
				throw new PalabraIncorrecta("La palabra que hay no es ni simple ni compleja incorrecta");
			}
	    	superficie[f][c].cargar(entrada);
		}
		return celulas;
	}

	/**
	 * Metodo que guarda la matriz de una partida
	 * @return un string de mensaje en un fichero de texto
	 */
	public String guardar() {
		String mensaje = "";
    	for(int i = 0; i < this.filas; i++){
    		for(int j = 0; j < this.columnas; j++){
    			if (!casillaVacia(i,j)){
    				mensaje += i + " " + j + " " + superficie[i][j].guardar() + System.getProperty("line.separator");
    			}
		    }
		}	
		return mensaje;
	}
	
}
	

	



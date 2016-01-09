package logica;


import java.io.IOException;
import java.lang.StringBuffer;
import java.util.Scanner;

import celula.Celula;
import excepciones.PalabraIncorrecta;


public abstract class Mundo{
	protected int filas;
	protected int columnas;
	protected Superficie superficie;

		
	/**
	 * La clase constructor de Mundo, genera una superficie con una longitud
	 * especifica de filas y columnas, y genera NUMEROCELULAS en posiciones
	 * aleatorias para esa superficie.
	 */	
	public Mundo(){
		this.filas = 0;
		this.columnas = 0;
		this.superficie = new Superficie(this.filas, this.columnas);
	}
	
	/**
	 * Mundo vacio de dimensiones pasadas por parametro usado para cargar de fichero
	 * @param f Numero de filas que tiene la matriz
	 * @param c Numero de columnas que tiene la matriz
	 */	
	public Mundo(int f, int c){
		this.filas = f;
		this.columnas = c;
		this.superficie = new Superficie(this.filas, this.columnas);
		//No me convence hacer esta llamada inutil
		inicializaMundo();
	}
	/**
	 * Metodo que aleatoriamente coloca las celulas en las casillas, inializando la superficie
	 */	
	public abstract void inicializaMundo();
		
	
	/**
	 * Metodo String que llama a la clase Superficie para generar la matriz del juego
	 * @return la matriz de la superficie
	 */
	public StringBuffer toStringBuffer(){
		return(superficie.toStringBuffer());
	}
	
	/**
	 * Metodo que evoluciona segun las reglas de la vida.
	 * Si la celula se puede mover a otra casilla aleatoria colindante a ella, entonces deja una nueva celula
	 * Si no puede moverse, tiene un maximo de paso para poder hacerlo, si no muere
	 * @return el paso de las celulas
	 */
	public String evoluciona(){
		String mensaje = "";
		boolean movido[][] = matriz();
		for(int i = 0; i < this.filas; i++){
    		for(int j = 0; j < this.columnas; j++){
    			if (!superficie.casillaVacia(i, j) && !movido[i][j]){
    				CasillaMensaje casillaMensaje = this.superficie.ejecutaMovimiento(i, j);
    				//Si no se puede mover la celula, no se toca la matriz
    				if (casillaMensaje.infoNovacia()){
    					movido[casillaMensaje.getFila()][casillaMensaje.getColumna()] = true;
    				}
    				mensaje += casillaMensaje.getMensaje() + System.getProperty("line.separator");
    			}
    		}
		}
		return mensaje;
	}
	
	/**
	 * Crea e inicializa una matriz de booleanos con el tamaño de la matriz de celulas, para saber que posicion se ha movido durante el juego
	 * @return La matriz booleana de dimension del tablero, con todas las posiciones inicializadas a falso
	 */	
	private boolean[][] matriz(){
		boolean [][] movido = new boolean [this.filas][this.columnas];
		for(int i = 0; i < this.filas; i++){
    		for(int j = 0; j < this.columnas; j++){
    			movido[i][j] = false;
		    }
		}
		return movido;
	}

	/**
	 * Metodo que reinicia la matriz del tablero dejando las casillas vacias
	 */	
	public void vaciar(){
		superficie.reset();
	}
	
	/**
	 * Crea una celula en la posicion (f,c) de la superficie
	 * @param f Valor entero positivo fila de la matriz
	 * @param c Valor entero positivo columna de la matriz
	 * @param celula pasa las celulas ya inicializadas
	 * @return TRUE se ha hecho el proceso de crear la celula
	 */	
	public boolean crearCelulaSuperficie(int f, int c, Celula celula){ 
		return superficie.llenarCasilla(f, c, celula);  
	}	
	
	/**
	 * Elimina la celula en la posicion (f,c) de la superficie
	 * @param f Valor entero positivo fila de la matriz
	 * @param c Valor entero positivo columna de la matriz
	 * @return TRUE si se ha hecho el proceso de eliminar la celula. False para los demás casos
	 */
	public boolean eliminarCelulaSuperficie(int f, int c){
		return superficie.vaciarCasilla(f,c);
	}
	
	/**
	 * Se encarga de guardar las dimensiones del tablero y luego todo el tablero con el estado actual del juego en juego.txt
	 * @param in los controles inicializados
	 * @return un fichero de texto con el Mundo guardado
	 * @throws IOException  para evitar los errores del guardado y el cargado
	 */
	public String guardar(){
		String mensaje = this.filas + System.getProperty("line.separator") + this.columnas + System.getProperty("line.separator");
		mensaje += superficie.guardar();
		return mensaje;
	}	
	
	/**
	 * Metodo que valida que los valores de fila y columna que pasa el usuario son validos
	 * @param f valores enteros positivos de fila
	 * @param c valores enteros positivos de columna
	 * @return TRUE si los valores de fila y columna es valido
	 * FALSE si los valores de fila y columna no son correctos, no estan dentro de los parametros definidos
	 */
	public boolean validarDatos(int f, int c){
		boolean valido = false;
		if(f >= 0 && f < this.filas){
			if(c >= 0 && c < this.columnas){
				valido = true;
			}
		}
		return valido;
	}	

	/**
	 * Metodo que llama a cargar un string de un fichero de Superficie
	 * @param in le pasa los controles inicializados
	 * @return un string de Mundo
	 * @throws IOException para evitar los errores del guardado y cargado
	 */
	public abstract void cargar(Scanner entrada)throws PalabraIncorrecta;
	
	/**
	 * Metodo abstracto que dice si un mundo es simple o complejo
	 * @return true si es simple o false si es complejo
	 */
	public abstract boolean esSimple();
	
	
}


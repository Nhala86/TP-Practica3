package celula;


import java.util.Scanner;

import logica.CasillaMensaje;
import logica.Superficie;

public interface Celula {
	
	/**
	 * Metodo que llama a la clase CasillaMensaje para poder evolucionar las celulas
	 * @param f valor entero positivo de fila
	 * @param c valor entero positivo de columna
	 * @param superficie pasado para realizar el paso en la superficie
	 * @return las celulas evolucionadas cuando se realiza el comando "Paso"
	 */
	CasillaMensaje ejecutaMovimiento(int f, int c, Superficie superficie);
	
	/**
	 * Metodo booleano que dice que una celula es comestible o no, diferenciando entre celulas
	 * @return true si es la celula simple, ya que es comestible. Y false si es la celula compleja
	 */
	boolean esComestible();
	
	/**
	 * Metodo que devuelve la matriz con casillas llenas con celulas y casillas vacias aleatoriamente
	 * @return la matriz con las celulas simples y complejas
	 */
	String toStringBuffer();
	
	/**
	 * Metodo que carga las celulas en la superficie de un fichero de texto guardado
	 * @param entrada del fichero cargado
	 */
	void cargar(Scanner entrada);
	
	/**
	 * Metodo que guarda las celulas de una partida en un fichero de texto
	 * @return strind de cada celula en su posicion
	 */
	String guardar();
	
}

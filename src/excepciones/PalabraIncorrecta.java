package excepciones;


public class PalabraIncorrecta extends Exception{

	private static final long serialVersionUID = 1L;	
	private int nLinea;
	private String informe;
	
	/**
	 * Metodo constructos de la excepcion
	 */
	public PalabraIncorrecta(){
		super();
	}
	
	/**
	 *  Metodo constructos de la excepcion al que se le pasa el mensaje de error
	 * @param mensaje string del mensaje de error
	 */
	public PalabraIncorrecta(String mensaje){
		super(mensaje);
	}
	
	/**
	 * Metodo constructor al que se le pasa los atributos nLineas y el string mensaje
	 * @param nLinea atributo entero de numero de lineas
	 * @param mensaje string del mensaje del error
	 */
	public PalabraIncorrecta(int nLinea,String mensaje){
		this.informe = mensaje;
		this.nLinea = nLinea;
	}
	
	/**
	 * Metodo string que devuelve el mensaje de error mas el numero de linea donde se produce el fallo del fichero
	 * @return string del mensaje de error
	 */
	public String Informe(){
		return (this.informe + " (LINEA: " + this.nLinea + ")");
	}	
	
	/**
	 * Metodo que solamente devuelve el numero de linea
	 * @return numero entero positivo de linea del fichero
	 */
	public int getLinea(){
		return this.nLinea;
	}
	
	/**
	 * Metodo string que devuelve el informe
	 * @return string del mensaje de error de la excepcion
	 */
	public String getMensaje(){
		return this.informe;
	}	
	
}

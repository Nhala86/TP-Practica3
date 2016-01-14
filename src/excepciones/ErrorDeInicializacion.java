package excepciones;


public class ErrorDeInicializacion extends Exception{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo constructos de la excepcion
	 */
	public ErrorDeInicializacion(){
		super();
	}
	
	/**
	 * Metodo constructos de la excepcion al que se le pasa el mensaje de error
	 * @param mensaje string del mensaje de error
	 */
	public ErrorDeInicializacion(String mensaje){
		super(mensaje);
	}
}

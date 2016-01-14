package excepciones;


public class ErrorDeInicializacion extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorDeInicializacion(){
		super();
	}
	
	public ErrorDeInicializacion(String mensaje){
		super(mensaje);
	}
}

package excepciones;


public class FormatoNumericoIncorrecto extends Exception{
	
	private static final long serialVersionUID = 1L;
		
	/**
	 * Metodo constructos de la excepcion
	 */
	public FormatoNumericoIncorrecto(){
		super();		
	}
	
	/**
	 *  Metodo constructos de la excepcion al que se le pasa el mensaje de error
	 * @param mensaje string del mensaje de error
	 */
	public FormatoNumericoIncorrecto(String mensaje){
		super(mensaje);
		
	}	
}

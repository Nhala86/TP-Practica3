package excepciones;


public class FormatoNumericoIncorrecto extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public FormatoNumericoIncorrecto(){
		super();
		
	}
	public FormatoNumericoIncorrecto(String mensaje){
		super(mensaje);
		
	}	
}

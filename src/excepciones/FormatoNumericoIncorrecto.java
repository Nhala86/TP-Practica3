package excepciones;


public class FormatoNumericoIncorrecto extends Throwable{
	
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

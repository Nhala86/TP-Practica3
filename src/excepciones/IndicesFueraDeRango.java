package excepciones;


public class IndicesFueraDeRango extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Metodo constructos de la excepcion
	 */
	public IndicesFueraDeRango(){
		super();
	}
	
	/**
	 *  Metodo constructos de la excepcion al que se le pasa el mensaje de error
	 * @param mensaje string del mensaje de error
	 */
	public IndicesFueraDeRango(String mensaje){
		super(mensaje);
	}
	
	
	
	
	
}

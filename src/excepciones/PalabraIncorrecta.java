package excepciones;


public class PalabraIncorrecta extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int nLinea;
	private String informe;
	
	public PalabraIncorrecta(){
		super();
	}
	
	public PalabraIncorrecta(String mensaje){
		super(mensaje);
	}
	public PalabraIncorrecta(int nLinea,String mensaje){
		this.informe = mensaje;
		this.nLinea = nLinea;
	}
	
	public String Informe(){
		return (this.getMensaje() + " (LINEA: " + this.nLinea + ")");
	}
	public int getLinea(){
		return this.nLinea;
	}
	public String getMensaje(){
		return this.informe;
	}
	
}

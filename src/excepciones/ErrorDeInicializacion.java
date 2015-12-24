package excepciones;

@SuppressWarnings("serial")
public class ErrorDeInicializacion extends Throwable{
	
	public ErrorDeInicializacion(){
		super();
	}
	
	public ErrorDeInicializacion(String mensaje){
		super(mensaje);
	}
}

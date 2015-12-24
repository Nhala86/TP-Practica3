package excepciones;

@SuppressWarnings("serial")
public class PalabraIncorrecta extends Throwable{

	public PalabraIncorrecta(){
		super();
	}
	
	public PalabraIncorrecta(String mensaje){
		super(mensaje);
	}
}

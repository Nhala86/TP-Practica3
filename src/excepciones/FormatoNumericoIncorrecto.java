package excepciones;

@SuppressWarnings("serial")
public class FormatoNumericoIncorrecto extends Throwable{
	
	public FormatoNumericoIncorrecto(){
		super();
		
	}
	public FormatoNumericoIncorrecto(String mensaje){
		super(mensaje);
	}
}

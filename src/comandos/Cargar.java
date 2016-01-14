package comandos;



import controlador.Controlador;
import excepciones.FormatoNumericoIncorrecto;
import excepciones.PalabraIncorrecta;


public class Cargar implements Comando {
	private String fichero;
	
	public Cargar(){
	}
	
	public Cargar(String nombre) {
		this.fichero = nombre;
	}

	@Override
	public String textoAyuda() {
		return ("CARGAR: cargar de un fichero de texto una partida guardada" + System.getProperty("line.separator"));
	}

	@Override
	public String ejecuta(Controlador controlador) throws PalabraIncorrecta, FormatoNumericoIncorrecto{
		controlador.cargar(this.fichero);
		return "";
	}

	@Override
	public Comando parsea(String[] palabras) {
		Comando comando;
		if(palabras[0].equalsIgnoreCase("cargar") && (palabras.length == 2))
			comando = new Cargar(palabras[1]);
		else comando = null;	
		return comando;		
	}
	
}

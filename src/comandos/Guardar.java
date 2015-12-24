package comandos;



import controlador.Controlador;


public class Guardar implements Comando {
	private String fichero;
	
	public Guardar(String nombre){
		this.fichero = nombre;
	}
	@Override
	public String textoAyuda() {
		return ("GUARDAR: guarda en un fichero de texto una partida" + System.getProperty("line.separator"));
	}

	@Override
	public String ejecuta(Controlador controlador){
		controlador.guardar(this.fichero);
		return "";
	
	}

	@Override
	public Comando parsea(String[] palabras) {
		Comando comando;
		if(palabras[0].equalsIgnoreCase("guardar")&& (palabras.length == 2))
			comando = new Guardar(palabras[1]);
		else comando = null;	
		return comando;	
	}

}
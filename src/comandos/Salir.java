package comandos;


import controlador.Controlador;


public class Salir implements Comando {

	@Override
	public Comando parsea(String[] palabras) {
		Comando comando;
		if(palabras[0].equalsIgnoreCase("salir") && (palabras.length == 1))
			return comando = new Salir();
		else comando = null;
		return comando;
	}

	@Override
	public String textoAyuda() {
		return ("SALIR: Es una instruccion que nos saca de la simulación" + System.getProperty("line.separator"));
	}
 
	@Override
	public String ejecuta(Controlador controlador){
		controlador.terminaSimulacion();
		return "Juego finalizado";
	}

}

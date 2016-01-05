package comandos;

import excepciones.ErrorDeInicializacion;
import excepciones.FormatoNumericoIncorrecto;

public final class ParserComandos {
	
	private static final Comando[] comandos = {
			new Paso(), 
			new Juego(),
			new Iniciar(),
			new CrearCelula(),
			new EliminarCelula(), 
			new Ayuda(),
			new Vaciar(),
			new Guardar(), 
			new Cargar(),
			new Salir(),
	};
	
	/**
	 * Metodo que devuelve los mensajes de los comandos
	 * @return un string por pantalla
	 */	 
	public static String AyudaComandos(){
		String mensaje = insertarSeparador();
		for(int j = 0; j < comandos.length; j++){
			mensaje += (comandos[j].textoAyuda());
		}
		mensaje += insertarSeparador();
		return mensaje;
	}
	
	/**
	 * Metodo que genera una cadena de guiones como separador.
	 * @param buffer de 70 caracteres '_'
	 */
	private static String insertarSeparador(){
		String mensaje = "";
		for(int i = 0; i < 70; i++){
			mensaje += "_";		
		}
		return mensaje + System.getProperty("line.separator");
	}
	
	/**
	 * Metodo que compara la longitud de las palabras metidas por consola por el usuario
	 * @param palabras array de palabras
	 * @return el comando que el usuario a solicitado
	 */
	public static Comando parseaComando(String[] palabras) throws FormatoNumericoIncorrecto, ErrorDeInicializacion{
		int i = 0;
		boolean seguir = true;
		Comando comando = null;
		while(i < comandos.length && seguir){
			comando = comandos[i].parsea(palabras);
			if(comando != null)
				seguir = false;
			else i++;
		}
		return comando;
	}

}

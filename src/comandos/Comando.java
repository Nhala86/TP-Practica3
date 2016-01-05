package comandos;


import controlador.Controlador;
import excepciones.ErrorDeInicializacion;
import excepciones.FormatoNumericoIncorrecto;
import excepciones.IndicesFueraDeRango;




public interface Comando {
	
	/**
	 * Metodo que permite al usuario ejecutar el mundo y a la vez decidir cual fichero quiero o no cargar o guardar
	 * @param mundo le pasa el Mundo para poder empezar la partida
	 * @param in el Scanner para cargar o guardar un fichero
	 * @return un string del mundo con sus celulas y casillas vacias
	 * @throws IOException para evitar problemas en el cargado y guardado del fichero
	 */
	public abstract String ejecuta(Controlador controlador) throws 
		FormatoNumericoIncorrecto, IndicesFueraDeRango;//A�ado el Scanner en la cabecera para poder permitir al usuario ponerle nombre al fichero que quiera cargar o guardar
	
	/**
	 * Metodo que compara lo que el usuario a metido por consola con los comandos, evita que el usuario meta comandos incorrectos
	 * @param palabras array de longitud de palabras
	 * @return el comando elejido por el usuario
	 * @throws FormatoNumericoIncorrecto 
	 */
	public abstract Comando parsea(String[] palabras) throws FormatoNumericoIncorrecto, ErrorDeInicializacion;
	
	/**
	 * Metodo que devuelve los textos del comando AYUDA
	 * @return un string por pantalla para ayudar al usuario a poner correctamente el nombre de los comandos 
	 */
	public abstract String textoAyuda();
	
}

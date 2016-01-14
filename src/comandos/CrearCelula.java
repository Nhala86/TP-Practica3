package comandos;

import controlador.Controlador;
import excepciones.ErrorDeInicializacion;
import excepciones.FormatoNumericoIncorrecto;
import excepciones.IndicesFueraDeRango;


public class CrearCelula implements Comando {
	private int fila;
	private int columna;
	
	/**
	 * Constructora sin argumentos (Vacia)
	 */
	public CrearCelula(){
	}
	
	/**
	 * Metodo constructor de argumentos
	 * @param fila valor entero positivo de fila
	 * @param columna valor entero positivo de columna
	 */
	public CrearCelula(int f, int c) {
		this.fila = f;
		this.columna = c;
	}

	@Override
	public String ejecuta(Controlador controlador) throws FormatoNumericoIncorrecto,IndicesFueraDeRango{
		if(controlador.validarDatos(this.fila, this.columna)){
			controlador.crearCelula(this.fila, this.columna);
		}
		else {
			throw new IndicesFueraDeRango("Los parametros pasados son incorrectos, la posicion no existe en el tablero" );
		}
		return "";
	}

	@Override
	public Comando parsea(String[] palabras)throws FormatoNumericoIncorrecto, ErrorDeInicializacion{
		Comando comando;
		if(palabras[0].equalsIgnoreCase("crearcelula") && (palabras.length == 3)){
			try{
			int f = Integer.parseInt(palabras[1]);
			int c = Integer.parseInt(palabras[2]);
			comando = new CrearCelula(f, c);
			}
			catch (NumberFormatException e){
				throw new FormatoNumericoIncorrecto("No has introducido un numero " + e.getMessage());
			}
			catch (ArrayIndexOutOfBoundsException e){
				throw new ErrorDeInicializacion("La fila o columna indicadas no es/son correctas " + e.getMessage());
			}
		}
		else comando = null;			
		return comando;		
	}		

	@Override
	public String textoAyuda() {
		return ("CREARCELULA F C: crea una nueva celula simple o compleja, dependiendo del juego, en la posicion (f,c) si es posible" + System.getProperty("line.separator")); 
		
	}

}

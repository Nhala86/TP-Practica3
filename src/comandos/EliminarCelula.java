package comandos;



import controlador.Controlador;
import excepciones.ErrorDeInicializacion;
import excepciones.FormatoNumericoIncorrecto;
import excepciones.IndicesFueraDeRango;



public class EliminarCelula implements Comando {
	private int fila;
	private int columna;
	
	/**
	 * Constructora sin argumentos (Vacia)
	 */
	public EliminarCelula(){
	}
	/**
	 * Metodo constructor de argumentos
	 * @param fila valor entero positivo de fila
	 * @param columna valor entero positivo de columna
	 */
	public EliminarCelula(int fila, int columna){
		this.fila = fila;
		this.columna = columna;
	}
	
	@Override
	public Comando parsea(String[] palabras)throws FormatoNumericoIncorrecto, ErrorDeInicializacion{
		Comando comando;
		if(palabras[0].equalsIgnoreCase("eliminarcelula") && (palabras.length == 3)){
			try{
				int f = Integer.parseInt(palabras[1]);
				int c = Integer.parseInt(palabras[2]);
				comando = new EliminarCelula(f, c);
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
		return ("ELIMINARCELULA (F, C): Elimina la celula de la posicion (f, c)" + System.getProperty("line.separator"));
	}
	
	@Override
	public String ejecuta(Controlador controlador)throws IndicesFueraDeRango{
		try{
		if(controlador.eliminarCelulaSuperficie(this.fila, this.columna)){
			return "Se ha eliminado la celula de la posicion(" + this.fila + "," + this.columna + ")" + System.getProperty("line.separator");
		}
		else return "No existe la celula en esa posicion" + System.getProperty("line.separator");
		}
		catch (ArrayIndexOutOfBoundsException e){
			throw new IndicesFueraDeRango("Estas intentado acceder a una posicion que no existe " + e.getMessage());
		}
	}
}

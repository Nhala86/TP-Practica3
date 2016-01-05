package comandos;


import celula.Celula;
import celula.CelulaCompleja;
import celula.CelulaSimple;
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
	public String ejecuta(Controlador controlador) throws FormatoNumericoIncorrecto,
			IndicesFueraDeRango{
		String mensaje, palabra;
		if(controlador.validarDatos(this.fila, this.columna)){
			Celula celula;
			if (controlador.esSimple()){
				celula = new CelulaSimple();
				palabra = "simple";
			}
			else {
				System.out.print("De que tipo: Compleja (1) o Simple (2): ");
				int comando = controlador.getComando();
				if (comando == 1){
					celula = new CelulaCompleja();
					palabra = "compleja";
				}
				else if (comando == 2){
					celula = new CelulaSimple();
					palabra = "simple";
				}
				else {
					throw new IndicesFueraDeRango("No has introducido un 1 o un 2");
				}
			}
			if (controlador.crearCelulaSuperficie(this.fila, this.columna, celula)){
				mensaje = "Creamos la celula " + palabra + " en: (" + this.fila + "," + 
						this.columna + ")";
			}
			else {
				mensaje = "Error, la posicion indicada esta ocupada";
			}
		}
		else {
		throw new IndicesFueraDeRango("Los parametros pasados son incorrectos, la posicion no existe en el tablero" );
		}
		return mensaje;
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

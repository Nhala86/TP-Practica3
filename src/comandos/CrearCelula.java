package comandos;


import celula.Celula;
import celula.CelulaCompleja;
import celula.CelulaSimple;
import controlador.Controlador;
import excepciones.ErrorDeInicializacion;
import excepciones.FormatoNumericoIncorrecto;
import excepciones.IndicesFueraDeRango;
import excepciones.PalabraIncorrecta;

public class CrearCelula implements Comando {
	private int fila;
	private int columna;
	
	public CrearCelula(int f, int c) {
		this.fila = f;
		this.columna = c;
	}

	@Override
	public String ejecuta(Controlador controlador) throws ErrorDeInicializacion, FormatoNumericoIncorrecto,
			IndicesFueraDeRango, PalabraIncorrecta {
		String mensaje, palabra;
		if(controlador.validarDatos(this.fila, this.columna)){
			Celula celula;
			if (controlador.esSimple()){
				celula = new CelulaSimple();
				palabra = "simple";
			}
			else {
				System.out.println("De que tipo: Compleja (1) o Simple (2): ");
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
					throw new FormatoNumericoIncorrecto("El numero no es correcto o no has introducido un numero");
				}
			}
			if (controlador.crearCelulaSuperficie(this.fila, this.columna, celula)){
				mensaje = "Creamos la celula " + palabra + " en: (" + this.fila + "," + 
						this.columna + ")";
			}
			else {
				mensaje = "Error, la posicion indicada esta ocupada" + System.getProperty("line.separator");
			}
		}
		else {
		mensaje = "Los parametros pasados son incorrectos, la celula no existe. Vuelva a introducirlos" 
				+ System.getProperty("line.separator");
		throw new IndicesFueraDeRango(mensaje);
		}
		return mensaje;
	}

	@Override
	public Comando parsea(String[] palabras) {
		Comando comando;
		if(palabras[0].equalsIgnoreCase("crearcelula") && (palabras.length == 3)){
			int f = Integer.parseInt(palabras[1]);
			int c = Integer.parseInt(palabras[2]);
			comando = new CrearCelula(f, c);
		}
		else comando = null;			
		return comando;		
	}

	@Override
	public String textoAyuda() {
		return ("CREARCELULA F C: crea una nueva celula simple o compleja, dependiendo del juego, en la posicion (f,c) si es posible" + System.getProperty("line.separator")); 
		
	}

}

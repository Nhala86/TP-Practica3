package comandos;


import controlador.Controlador;
import excepciones.ErrorDeInicializacion;
import excepciones.FormatoNumericoIncorrecto;
import logica.Mundo;
import logica.MundoComplejo;
import logica.MundoSimple;

public class Juego implements Comando {
	private Mundo mundo;
	
	/**
	 * Metodo constructor que inicializa a vacio
	 */
	public Juego(){		
	}
	
	/**
	 * Metodo constructor generico que inicializa el mundo
	 * @param mundo string de la matriz del mundo
	 */
	public Juego(Mundo mundo){
		this.mundo = mundo;
	}
	
	@Override
	public String ejecuta(Controlador controlador) {
		controlador.juega(this.mundo);
		return "";
	}

	@Override
	public Comando parsea(String[] palabras) throws FormatoNumericoIncorrecto, ErrorDeInicializacion{
		Comando comando;
		if(palabras[0].equalsIgnoreCase("jugar")){
			int f, c, simple;
			//Cambiado el orden porque si introduces la palabra jugar sola, hay un error java.lang.ArrayIndexOutOfBoundsException 1
			if ((palabras.length == 5) && palabras[1].equalsIgnoreCase("simple")){
				try{
				f = Integer.parseInt(palabras[2]); 
				c = Integer.parseInt(palabras[3]);
				simple = Integer.parseInt(palabras[4]);
				if (simple > f*c){
					throw new ErrorDeInicializacion("El numero de celulas es mayor que la superficie");
				}
				this.mundo = new MundoSimple(f,c,simple);
				comando = new Juego(mundo);
				}
				catch (NumberFormatException e){
					throw new FormatoNumericoIncorrecto("No has introducido un numero " + e.getMessage());
				}
				catch (ArrayIndexOutOfBoundsException e){
					throw new ErrorDeInicializacion("El numero de celulas es mayor que la superficie");
				}
			}
			else if ((palabras.length == 6) && palabras[1].equalsIgnoreCase("complejo")){
				try{
					f = Integer.parseInt(palabras[2]); 
					c = Integer.parseInt(palabras[3]);
					simple = Integer.parseInt(palabras[4]);
					int complejo = Integer.parseInt(palabras[5]);
					if ((simple + complejo) > f*c){
						throw new ErrorDeInicializacion("El numero de celulas es mayor que la superficie");
					}
					this.mundo = new MundoComplejo(f,c,simple,complejo);
					comando = new Juego(mundo);
				}
				catch (NumberFormatException e){
					throw new FormatoNumericoIncorrecto("No has introducido un numero " + e.getMessage());
				}
				catch (ArrayIndexOutOfBoundsException e){
					throw new ErrorDeInicializacion("El numero de celulas es mayor que la superficie");
				}
			}
			else comando = null;
		}
		else comando = null;
		return comando;
	}

	@Override
	public String textoAyuda(){
		return ("JUGAR SIMPLE F C S: Inicia el juego con un tablero(F,C) y celulas simples(S) repartidas aleatoriamente en el" + 
				System.getProperty("line.separator") + "JUGAR COMPLEJO F C S C: Inicia el juego con un tablero(F,C), celulas simples(S) "
				+ "y celulas complejas (C) repartidas aleatoriamente en el") + System.getProperty("line.separator");
	}

}

package logica;

import java.util.Scanner;

import celula.Celula;
import celula.CelulaCompleja;
import celula.CelulaSimple;
import excepciones.IndicesFueraDeRango;
import excepciones.PalabraIncorrecta;

public class MundoSimple extends Mundo {
	private int simples;
	
	/**
	 * Metodo constructor generico que inicializa a cero las celulas simples 
	 */
	public MundoSimple(){
		super();
		this.simples = 0;
	}
	
	/**
	 * Metodo constructor que inicializa el mundo
	 * @param f numero entero positivo de filas
	 * @param c numero entero positivo de columnas
	 * @param simples numero entero positivo de celulas simples
	 */
	public MundoSimple(int f,int c, int simples){
		super(f,c);
		this.simples = simples;
		inicializaMundo();
	}
	
	@Override
	public void inicializaMundo() {
		int contCelulas = 0;
		while (contCelulas < simples){
			int f = (int) (Math.random()* this.filas);
			int c = (int) (Math.random()* this.columnas);
			if (superficie.casillaVacia(f, c)){
				this.superficie.llenarCasilla(f, c, new CelulaSimple());
				contCelulas++;
			}
		}
	}

	@Override
	public void cargar(Scanner entrada)throws PalabraIncorrecta{
		int f = entrada.nextInt(), c = entrada.nextInt();
		this.filas = f;
		this.columnas = c;
		this.superficie = new Superficie(this.filas, this.columnas);
		while (entrada.hasNext()){
			int filas = entrada.nextInt(), columnas = entrada.nextInt();
			String tipo = entrada.next();
			if (tipo.equalsIgnoreCase("simple")){
				Celula celula = new CelulaSimple();
				celula.cargar(entrada);
				superficie.llenarCasilla(filas, columnas, celula);
				this.simples++;
			}
			else {
				throw new PalabraIncorrecta("La palabra que hay no es simple");
			}
		}
		
		
		
	}



	@Override
	public String crearCelula(int f, int c, Scanner in){
		Celula celula;
		String mensaje;
		celula = new CelulaSimple();
		if (superficie.llenarCasilla(f, c, celula)){
			mensaje = "Creamos la celula " + "simple" + " en: (" + f + "," + 
					c + ")";
		}
		else {
			mensaje = "Error, la posicion indicada esta ocupada";
		}
		return mensaje;
	}

	@Override
	public String guardar() {
		String mensaje = "simple" + System.getProperty("line.separator") + this.filas + System.getProperty("line.separator") + this.columnas + System.getProperty("line.separator");
		mensaje += superficie.guardar();
		return mensaje;
	}
}

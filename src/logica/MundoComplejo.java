package logica;

import java.util.Scanner;

import celula.Celula;
import celula.CelulaCompleja;
import celula.CelulaSimple;
import excepciones.FormatoNumericoIncorrecto;
import excepciones.IndicesFueraDeRango;
import excepciones.PalabraIncorrecta;

public class MundoComplejo extends Mundo {
	private int complejas;
	private int simples;
	
	/**
	 * Metodo constructor generico que inicializa las simples y complejas a cero
	 */
	public MundoComplejo(){
		super();
		this.simples = 0;
		this.complejas = 0;
	}
	
	/**
	 * Metodo constructor que inicializa el mundo
	 * @param f nuemro entero positivo de filas
	 * @param c nuemro entero positivo de columnas
	 * @param simples numero entero positivo de celulas simples
	 * @param complejas numero entero positivo de celulas complejas
	 */
	public MundoComplejo(int f, int c, int simples, int complejas){
		super(f,c);
		this.simples = simples;
		this.complejas = complejas;
		inicializaMundo();
	}
	
	@Override
	public void inicializaMundo() {
		int contCelulas = 0;
		while (contCelulas < simples){
			int f = (int) (Math.random()* this.filas);
			int c = (int) (Math.random()* this.columnas);
			if (this.superficie.casillaVacia(f, c)) {
				this.superficie.llenarCasilla(f, c, new CelulaSimple());
				contCelulas++;
			}
		}
		contCelulas = 0;
		while (contCelulas < complejas){
			int f = (int) (Math.random()* this.filas);
			int c = (int) (Math.random()* this.columnas);
			if (this.superficie.casillaVacia(f, c)) {
				this.superficie.llenarCasilla(f, c, new CelulaCompleja());
				contCelulas++;
			}
		}
	}

	@Override
	public void cargar(Scanner entrada)throws PalabraIncorrecta, FormatoNumericoIncorrecto {
		Celula celula;
		int cont = 2; // se inicializa a dos para que coincida con el numero de fila de la superficie a cargar
		try{			
			this.filas = Integer.parseInt(entrada.nextLine());
			cont++;
			this.columnas = Integer.parseInt(entrada.nextLine());
			this.superficie = new Superficie(this.filas, this.columnas);
			while (entrada.hasNext()){
				cont++;
				String[] datos = entrada.nextLine().split(" ");				
				int posFilas = Integer.parseInt(datos[0]);
				int posColumnas = Integer.parseInt(datos[1]);					
				if (datos[2].equals("simple") && datos.length == 5){
					celula = new CelulaSimple();
					celula.cargar(datos);
					superficie.llenarCasilla(posFilas, posColumnas, celula);
					this.simples++;
				}
				else if (datos[2].equalsIgnoreCase("compleja")&& datos.length == 4){
					celula = new CelulaCompleja();
					celula.cargar(datos);
					superficie.llenarCasilla(posFilas, posColumnas, celula);
					this.complejas++;
				}
				else {
					throw new PalabraIncorrecta(cont ,"La linea que define la celula esta corrupta");
				}	    	
			}
		}catch (NumberFormatException e){
			throw new PalabraIncorrecta (cont, "Los valores de fila y/o columna para la superficie son incorrectos");
		}catch(FormatoNumericoIncorrecto e){
			throw new PalabraIncorrecta (cont, e.getMessage());
		}
		
	}
	
	@Override
	public String crearCelula(int f, int c, Scanner in)throws IndicesFueraDeRango{
		String mensaje, palabra;
		Celula celula;
			
		System.out.print("De que tipo: Compleja (1) o Simple (2): ");
		int comando = in.nextInt();
		//Limpio el scanner despues de leer el entero
		in.nextLine();
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
			if (superficie.llenarCasilla(f, c, celula)){
				mensaje = "Creamos la celula " + palabra + " en: (" + f + "," + 
						c + ")";
			}
			else {
				throw new IndicesFueraDeRango("La celula no se puede crear");
			}
			return mensaje;		
	}

	@Override
	public String guardar() {
		String mensaje = "complejo" + System.getProperty("line.separator") + this.filas + System.getProperty("line.separator") + this.columnas + System.getProperty("line.separator");
		mensaje += superficie.guardar();
		return mensaje;
	}

}

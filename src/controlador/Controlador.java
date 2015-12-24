package controlador;

import java.util.Scanner;

import logica.Mundo;
import logica.MundoComplejo;
import logica.MundoSimple;

import java.io.*;

import celula.Celula;
import comandos.Comando;
import comandos.ParserComandos;
import excepciones.ErrorDeInicializacion;
import excepciones.FormatoNumericoIncorrecto;
import excepciones.IndicesFueraDeRango;
import excepciones.PalabraIncorrecta;


public class Controlador {
	private Mundo mundo;
	private Scanner in;
	private boolean simulacionTerminada = false;
	
	/**
	 * Metodo constructor que inicializa el mundo y el scanner
	 */
	/*
	public Controlador(){
		this.mundo = new Mundo();
		this.in = new Scanner(System.in);
	}
	*/
	
	/**
	 * Metodo constructor del Controlador que define los parametros mundo e in
	 * @param mundo le pasa el nuevo mundo inicializado
	 * @param in le pasa los controles ya inicializados
	 */
    public Controlador(Mundo mundo, Scanner in){
        this.mundo = mundo;
        this.in = in;
    }
    
    /**
	 * Metodo que valida que los valores de fila y columna que pasa el usuario son validos
	 * @param f valores enteros positivos de fila
	 * @param c valores enteros positivos de columna
	 * @return TRUE si los valores de fila y columna es valido
	 * FALSE si los valores de fila y columna no son correctos, no estan dentro de los parametros definidos
	 */
	public boolean validarDatos(int f, int c){
		return this.mundo.validarDatos(f,c);
	}
    
	/**
	 * Metodo que lee lo que el usuario mete por consola e impide que no sea el comando correcto
	 * @param in le pasa los controles inicializados
	 * @return un string del comando correcto
	 */
    private String[] crearComando(Scanner in){
    	String[] palabras = null;
    	System.out.println(mundo.toStringBuffer());
		System.out.print("Comando > ");
		String string = in.nextLine();
		String stringlower = string.toLowerCase();
		palabras = stringlower.split(" ");
		if(palabras.length == 3){ // Protege de que el usuario meta chorradas junto con el comando o meta una fila y columna incorrecta
			if(!this.validarDatos(Integer.parseInt(palabras[1]),Integer.parseInt(palabras[2]))){
				System.out.println("Valores de fila y columna no validos");
				this.crearComando(in);
			}
		}
		return  palabras;
    	
    }
    
    /**
     * Metodo encargado de los controles que el usuario introduce para el funcionamiento del juego
     * y encargado de llamar a las funciones en otras clases para mostrar por pantalla el juego y sus movimientos
     * @throws IOException para evitar los errores del cargado y el guardado
     */

	public void realizaSimulacion(){
		System.out.println("Bienvenido al juego de la vida: ");
		String mensaje = "";
		while (!this.simulacionTerminada){
			String [] palabras = crearComando(this.in);			
			Comando comando = ParserComandos.parseaComando(palabras);

			if (comando != null){
				try {
					mensaje = comando.ejecuta(this);
				} catch (IndicesFueraDeRango e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				} catch (PalabraIncorrecta e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				} catch (FormatoNumericoIncorrecto e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				} catch (ErrorDeInicializacion e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				System.out.println(mensaje);
			}
			else {
				System.out.println("Comando desconocido (Escriba AYUDA para infomarse de los comandos disponibles)");
			}
		}	
	}
	
	public void juega(Mundo mundo){
		
	}
	
	public void cargar(String nombreFichero){
		String nombre = nombreFichero;
		File archivo = new File(nombre);
		//No se porque tengo que meter todo en el try para que funcione
		try {
			Scanner entrada = new Scanner(archivo);
			String tipo = entrada.nextLine();
			if (tipo.equalsIgnoreCase("simple")){
				this.mundo = new MundoSimple();
			}
			else if (tipo.equalsIgnoreCase("complejo")){
				this.mundo = new MundoComplejo();
			}
			else {
				entrada.close();
				throw new PalabraIncorrecta();
			}
			mundo.cargar(entrada);
			entrada.close();
		} catch (FileNotFoundException e) {
			System.out.println("El nombre del fichero especificado no existe" + System.getProperty("line.separator"));
			e.printStackTrace();
		}
		catch(PalabraIncorrecta e){
			System.out.println("La palabra que encabeza el fichero no es ni simple ni complejo" + System.getProperty("line.separator"));
		}
		System.out.println("Carga realizada con exito");
		
	}
	
	public void guardar(String nombreFichero){
		File archivo = new File(nombreFichero + ".txt");
		FileWriter escribir;
		try {
			escribir = new FileWriter(archivo);
			if (mundo.esSimple()){
				escribir.append("simple");
			}
			else {
				escribir.append("complejo");
			}
			escribir.append(System.getProperty("line.separator"));
			escribir.append(mundo.guardar());
			escribir.close();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
		System.out.println("Partida guardada correctamente");
	}
	
		/*
		int fila = entrada.nextInt(), columna = entrada.nextInt();
			//Mundo mundo = new Mundo(fila, columna);
			if (this.filas == fila && this.columnas == columna){
				for (int i=0; i < fila; i++){
					for (int j=0; j < columna; j++){
						String cadena = entrada.next();
						if (!cadena.equals("-")){
							//Crear nueva celula
							String [] posicion = cadena.split("-");
							if (posicion.length == 1){
								int explota = Integer.parseInt(cadena);
								this.superficie[i][j] = new CelulaCompleja(explota);
							}
							else{
								int SinMover = Integer.parseInt(posicion[0]);
								int Reproduccion = Integer.parseInt(posicion[1]);
								this.superficie[i][j] = new CelulaSimple (SinMover, Reproduccion);
							}
						}
						else {
							vaciarCasilla(i, j);
						}
					}
				}
			}
			
			else {
				System.out.println("La dimension del tablero del juego a cargar no es correcta, ajuste la dimension y vuelva a intentarlo"
						+ System.getProperty("line.separator"));
			}
			entrada.close();
			System.out.println("Partida cargada correctamente" + System.getProperty("line.separator"));
		}
		*/

	public String daUnPaso() {
		return this.mundo.evoluciona();
	}
	
	/**
	 * Metodo que iguala a true la simulacionTerminada
	 */
	public void terminaSimulacion() {
		this.simulacionTerminada = true;
	}
	
	public void vaciar(){
		this.mundo.vaciar();
	}
	public void generarCelulas(){
		this.mundo.inicializaMundo();
	}
	public boolean eliminarCelulaSuperficie(int f, int c){
		return this.mundo.eliminarCelulaSuperficie(f,c);
	}
	public boolean crearCelulaSuperficie(int f, int c, Celula celula){
		return this.mundo.crearCelulaSuperficie(f,c,celula);
	}

	public boolean esSimple(){
		return mundo.esSimple();
	}
	
	/**
	 * Permite recoger un comando numerico del Scanner ya abierto sin tener que abrirlo de nuevo 
	 * @return Un entero recogido del Scanner
	 */
	public int getComando(){
		int comando = in.nextInt();
		return comando;
	}

	
	
	
	
}
		
		
		
		
		
		
		
		

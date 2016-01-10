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
		System.out.print("Comando > ");
		String string = in.nextLine();
		String stringlower = string.toLowerCase();
		String[] palabras = stringlower.split(" ");
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
			System.out.println(mundo.toStringBuffer());
			String [] palabras = crearComando(this.in);	
			//Mundo mundoAntiguo = this.mundo;
			try {
				Comando comando = ParserComandos.parseaComando(palabras);
				if (comando != null){
				mensaje = comando.ejecuta(this);
				//Movido para que solo muestre este mensaje si no hay excepciones
				System.out.println(mensaje);
				} 
				else {
					System.out.println("Comando desconocido (Escriba AYUDA para infomarse de los comandos disponibles)");
				}
			}
			catch (IndicesFueraDeRango e) {
				System.out.println(e.getMessage());
				
			}catch (FormatoNumericoIncorrecto e) {
				System.out.println(e.getMessage());
				
			} catch (ErrorDeInicializacion e) {
				System.out.println(e.getMessage());				
				//this.mundo = mundoAntiguo;
			}
		}	
	}
	
	/**
	 * Metodo que inicializa juego inicializando el mundo 
	 * @param mundo matriz de la superficie
	 */
	public void juega(Mundo mundo){
		this.mundo = mundo;
	}
	
	/**
	 * Metodo que carga un fichero de texto de una partida guardada anteriormente con el mundo que se estaba jugando 
	 * @param nombreFichero string de la superficie guardada
	 */
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
				throw new PalabraIncorrecta("La palabra que encabeza el fichero no es ni simple ni complejo");
			}
			mundo.cargar(entrada);
			entrada.close();
			System.out.println("Carga realizada con exito");
		} catch (FileNotFoundException e) {
			System.out.println("El nombre del fichero especificado no existe");
			
		}
		catch(PalabraIncorrecta e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo que guarda una partida completa en un fichero de texto
	 * @param nombreFichero string del mundo
	 */
	public void guardar(String nombreFichero){
		File archivo = new File(nombreFichero);
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

	/**
	 * Metodo que llama a evoluciona de mundo para realizar el paso
	 * @return string del mundo evolucionado
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
	
	/**
	 * metodo que llama al vaciar del mundo
	 */
	public void vaciar(){
		this.mundo.vaciar();
	}
	
	/**
	 * Metodo que inicializa el mundo
	 */
	public void generarCelulas(){
		this.mundo.inicializaMundo();
	}
	
	/**
	 * Metodo que elimina una celula de la superficie
	 * @param f numero entero positivo de la fila
	 * @param c numero entero positivo de la columna
	 * @return true si se ha eliminado una celula de la superficie y false si no lo ha hecho
	 */
	public boolean eliminarCelulaSuperficie(int f, int c){
		return this.mundo.eliminarCelulaSuperficie(f,c);
	}
	
	/**
	 * Metodo que crea una celula simple o compleja segun haya sido elegida
	 * @param f numero entero positivo de la fila
	 * @param c numero entero positivo de la columna
	 * @param celula la celula simple o compleja segun sea
	 * @return true si se ha creado una celula de la superficie y false si no lo ha hecho
	 */
	public boolean crearCelulaSuperficie(int f, int c, Celula celula){
		return this.mundo.crearCelulaSuperficie(f,c,celula);
	}

	/**
	 * Metodo que dice si el mundo es simple o complejo
	 * @return true si el mundo es simple o false si es complejo
	 */
	public boolean esSimple(){
		return mundo.esSimple();
	}
	
	/**
	 * Permite recoger un comando numerico del Scanner ya abierto sin tener que abrirlo de nuevo 
	 * @return Un entero recogido del Scanner
	 */
	public int getComando(){
		int comando = in.nextInt();
		//Limpio el scanner despues de leer el entero
		in.nextLine();
		return comando;
	}	
	
}
		
		
		
		
		
		
		
		

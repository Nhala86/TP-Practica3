package main;

import java.io.IOException;
import java.util.Scanner;
import controlador.Controlador;
import excepciones.PalabraIncorrecta;
import logica.Mundo;
import logica.MundoSimple;

public class Main {
	
	/**
	 * Metodo constructor del Main, que inicializa Scanner, Mundo y Controlador, y realiza la simulacion del juego
	 * @param args valor predeterminado por java
	 * @throws IOException para evitar errores en el guardado y el cargado
	 * @throws PalabraIncorrecta si los parametros introducidos son incorrectos salta la excepcion
	 */
	public static void main(String[] args) throws IOException, PalabraIncorrecta {
		Scanner in = new Scanner(System.in);
		Mundo mundo = new MundoSimple();
		Controlador controlador = new Controlador(mundo, in);
		controlador.realizaSimulacion(args);
	}

}

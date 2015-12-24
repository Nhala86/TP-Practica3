package main;

import java.io.IOException;
import java.util.Scanner;

import controlador.Controlador;
import logica.Mundo;
import logica.MundoSimple;



public class Main {
	/**
	 * Metodo constructor del Main, que inicializa Scanner, Mundo y Controlador, y realiza la simulacion del juego
	 * @param args valor predeterminado por java
	 * @throws IOException para evitar errores en el guardado y el cargado
	 */
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		//Pongo mundo simple porque no se puede crear un objeto de una clase abstracta (MUNDO)
		Mundo mundo = new MundoSimple();
		Controlador controlador = new Controlador(mundo, in);
		controlador.realizaSimulacion();
	}

}

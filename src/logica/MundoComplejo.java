package logica;

import java.util.Scanner;

import celula.CelulaCompleja;
import celula.CelulaSimple;

public class MundoComplejo extends Mundo {
	private int complejas;
	private int simples;
	
	public MundoComplejo(){
		super();
		this.simples = 0;
		this.complejas = 0;
	}
	
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
	public void cargar(Scanner entrada) {
		int f = entrada.nextInt();
		int c = entrada.nextInt();
		this.filas = f;
		this.columnas = c;
		this.superficie = new Superficie(this.filas, this.columnas);
		if (superficie.cargar(entrada, true)){
			this.simples++;
		}
		else {
			this.complejas++;
		}
		
	}

	@Override
	public boolean esSimple() {
		return false;
	}


}

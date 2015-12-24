package logica;


import java.util.Scanner;

import celula.CelulaSimple;

public class MundoSimple extends Mundo {
	private int simples;
	
	public MundoSimple(){
		super();
		this.simples = 0;
	}
	
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
	public void cargar(Scanner entrada){
		int f = entrada.nextInt(), c = entrada.nextInt();
		this.filas = f;
		this.columnas = c;
		this.superficie = new Superficie(this.filas, this.columnas);
		/*
		Si llamo a cargar por MundoSimple, no reconozca a las celulas complejas
		Si la celula es simple, incrementamos el contador
		*/
		if (superficie.cargar(entrada, false)){
			this.simples++;
		}
		
	}

	@Override
	public boolean esSimple() {
		return true;
	}

	

}

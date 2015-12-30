package comandos;


import controlador.Controlador;
import logica.Mundo;
import logica.MundoComplejo;
import logica.MundoSimple;

public class Juego implements Comando {
	private Mundo mundo;
	
	
	public Juego(Mundo mundo){
		this.mundo = mundo;
	}
	
	@Override
	public String ejecuta(Controlador controlador) {
		controlador.juega(this.mundo);
		return "";
	}

	@Override
	public Comando parsea(String[] palabras) {
		Comando comando;
		if(palabras[0].equalsIgnoreCase("jugar")){
			int f, c, simple;
			if (palabras[1].equalsIgnoreCase("simple") && (palabras.length == 5)){
				f = Integer.parseInt(palabras[2]); 
				c = Integer.parseInt(palabras[3]);
				simple = Integer.parseInt(palabras[4]);
				this.mundo = new MundoSimple(f,c,simple);
				comando = new Juego(mundo);
			}
			else if (palabras[1].equalsIgnoreCase("complejo") && (palabras.length == 6)){
				f = Integer.parseInt(palabras[2]); 
				c = Integer.parseInt(palabras[3]);
				simple = Integer.parseInt(palabras[4]);
				int complejo = Integer.parseInt(palabras[5]);
				this.mundo = new MundoComplejo(f,c,simple,complejo);
				comando = new Juego(mundo);
			}
			else {
				comando = null;
			}
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

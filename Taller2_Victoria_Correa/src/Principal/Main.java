package Principal;
import processing.core.PApplet;

public class Main extends PApplet{

	Logica logica;
	
	public static void main(String[] args) {
		PApplet.main("Principal.Main");
		
	}
	
	public void setup() {
		logica= new Logica(this);
	}
	
	public void settings() {
		size(1200,700);
	}
	
	public void draw() {
		logica.pintar();
	}
	
	public void mousePressed() {
		logica.mouse();
	//	
	}
	
	public void keyPressed() {
		logica.ejecutarTeclado();
	}

}

package Principal;


import CajaDeComunicacion.Mensaje;
import processing.core.PApplet;
import processing.core.PImage;

public class Madre extends Personaje{
  
	
	private PImage madre;
	private int punt;

  public Madre (Logica log, int x, int y) {
    super(log, x, y);
    
   //CARGAR IMAGENES ARREGLO

    madre =app.loadImage("madre.png");
    madre.resize(170, 170);

  }

  @Override
  public void pintar() {

   app.image(madre,x,y);
    
   if (app.frameCount % 15==0) {
      punt++;
    }    
      if (punt>1) {
        punt=0;
      }
      
    }
  
  public void obtengoMensaje(Mensaje m) {
		saltar = m.saltar;
		pasar = m.pasar;
		
	}
  
}
  
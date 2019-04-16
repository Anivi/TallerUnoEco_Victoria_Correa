package Principal;


import CajaDeComunicacion.Mensaje;
import processing.core.PApplet;
import processing.core.PImage;

public class Padre extends Personaje{
  
	
	private PImage padre;
	private int punt;

  public Padre (Logica log, int x, int y) {
    super(log, x, y);
    
   //CARGAR IMAGENES ARREGLO

    padre =app.loadImage("padre.png");
    padre.resize(170, 170);
    
  }

  @Override
  public void pintar() {

   app.image(padre,x,y);
    
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
  
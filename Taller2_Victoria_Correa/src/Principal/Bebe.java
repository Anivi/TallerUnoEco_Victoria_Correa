package Principal;

import CajaDeComunicacion.Mensaje;
import processing.core.PApplet;
import processing.core.PImage;

public class Bebe extends Personaje{
  
	
	private PImage bebe;
	private int punt;

  public Bebe (Logica log, int x, int y) {
    super(log, x, y);
    
   //CARGAR IMAGENES ARREGLO

    	bebe =app.loadImage("bebecito.png");
    	bebe.resize(150, 150);

  }

  @Override
  public void pintar() {

   app.image(bebe,x,y);
    
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
  
  
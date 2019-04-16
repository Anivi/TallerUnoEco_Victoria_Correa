package Principal;
import processing.core.PApplet;
import processing.core.PImage;

public class Pentagrama extends Recogible {
  
   private PImage penta;
   
   public Pentagrama(PApplet app, int x, int y, int vel){
   super(app, x, y, vel);
   
   penta= app.loadImage("pentagrama.png");
   
  
}
  
   public void pintar(){

     app.image(penta,x,y);
      x -= vel;
      
   }

  

}
package Principal;
import processing.core.PApplet;
import processing.core.PImage;

public class Vela extends Recogible {
  
   private PImage vela;
   
   public Vela(PApplet app, int x, int y, int vel){
   super(app, x, y, vel);
   
   vela= app.loadImage("vela.png");
  
  
}
  
   public void pintar(){

     app.image(vela,x,y);
      x -= vel;
      
   }

  

}
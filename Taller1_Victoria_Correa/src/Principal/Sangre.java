package Principal;
import processing.core.PApplet;
import processing.core.PImage;

public class Sangre extends Recogible {
  
   private PImage sangre;
   
   public Sangre(PApplet app, int x, int y, int vel){
   super(app, x, y, vel);
   
   sangre= app.loadImage("sangre.png");
   
  
}
  
   public void pintar(){

     app.image(sangre,x,y);
      x -= vel;
      
   }

  

}
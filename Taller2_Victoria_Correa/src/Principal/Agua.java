package Principal;
import processing.core.PApplet;
import processing.core.PImage;

public class Agua extends Obstaculo {
  
   private PImage agua;
   
   public Agua(PApplet app, int x, int y, int vel){
   super(app, x, y, vel);
   
   agua= app.loadImage("agua.png");
   agua.resize(140, 210);
  
}
  
   public void pintar(){

     app.image(agua,x,y);
      x -= vel;
      
   }

  

}
package Principal;
import processing.core.PApplet;
import processing.core.PImage;

public class Cruz extends Obstaculo {
  
   private PImage cruz;
   
   public Cruz(PApplet app, int x, int y, int vel){
   super(app, x, y, vel);
   
   cruz= app.loadImage("cruz.png");
	cruz.resize(140, 210);
  
}
  
   public void pintar(){

     app.image(cruz,x,y);
      x -= vel;
      
   }

  

}
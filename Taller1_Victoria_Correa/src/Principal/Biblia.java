package Principal;
import processing.core.PApplet;
import processing.core.PImage;

public class Biblia extends Obstaculo {
  
   private PImage biblia;
   
   public Biblia(PApplet app, int x, int y, int vel){
   super(app, x, y, vel);
   
   biblia= app.loadImage("biblia.png");
   biblia.resize(140, 210);
  
}
  
   public void pintar(){

     app.image(biblia,x,y);
      x -= vel;
      
   }

  

}
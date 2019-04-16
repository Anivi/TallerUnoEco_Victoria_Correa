package Principal;
import processing.core.PApplet;

public abstract class Obstaculo {

		protected int x;
		protected int y;
		protected int vel;  
		protected PApplet app;
	  
	  
	    
	  public Obstaculo(PApplet app, int x, int y,int vel){
		this.app=app;
	    this.x=x;
	    this.y=y;
	    this.vel=vel;
	    
	    vel=5;
	    
	  
	  }
	  public abstract void pintar();
	  
	  
	   public int getY (){
	  return y;
	  }
	  
	   public int getX (){
	  return x;
	  }
	  
	 
	  
	  public void setX(int x){
	  this.x=x;
	}
	
}

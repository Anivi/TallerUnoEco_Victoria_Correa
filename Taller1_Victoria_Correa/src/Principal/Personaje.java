package Principal;
import CajaDeComunicacion.Receptor;
import CajaDeComunicacion.Receptor.OnRecieved;
import processing.core.PApplet;

public abstract class Personaje implements OnRecieved{
  protected int x;
  protected int y;
  protected int vel;
  protected int puntaje;
  protected PApplet app;
  
  boolean vivo=true;
  boolean saltar, pasar;
	
	protected Logica log;
	protected Receptor receptor;
  

  public Personaje(Logica log, int x, int y) {
	this.log=log;
	this.app=log.getPApplet();
    this.x=x;
    this.y=y;
    vel=0;
    
    obtenerIndependencia();
  }

  public abstract void pintar();
  
  public void movimiento() {
	  if(saltar) {
		  y-=vel;
	  }
	  
	  if(pasar) {
		  switch(log.getPantalla()) {
		  	case 0:
		  		log.setPantalla(1);
		  		System.out.println(pasar + "----------" + log.getPantalla());
		  		break;
		  		
		  	case 1:
		  		log.setPantalla(2);
		  		break;
		  		
		  	case 2:
		  		log.setPantalla(3);
		  		break;
		  		
		  	case 3:
		  		log.setIniciar(true);
		  		break;

		  }
	  
  }
  

  }
  
  public void construirComunicacion(Receptor r) {
		this.receptor=r;
		this.receptor.setMensajote(this);
	}
  
  
  public void obtenerIndependencia() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(vivo) {
					try {
						movimiento();
						Thread.sleep(20);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
  
  public int getX (){
  return x;
  }
  
  public int getY (){
  return y;
  }
  
  public void setY (int y){
  this.y=y;
  }
  
}
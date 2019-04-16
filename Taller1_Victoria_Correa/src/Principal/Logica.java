package Principal;

import java.awt.Image;
import java.util.ArrayList;

import CajaDeComunicacion.Servidor;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class Logica {
	
	Servidor servidor;
	
	PApplet app;
	private int pantalla;
	private PImage inicio, elegir, fondoJ, fondoJ2, resumen, bye, ganaste,perdiste,instrucciones; //Pantallas
	private int panX, vel; // Movimiento de pantalla
	private PImage start, start1, punt, fin, vaj, atras ; // Botones
	private PImage meta; //barra
	public Personaje p;
	private Boolean iniciar=false;
	private Boolean tocarObstaculo=false;
	private int titilar=0;
	private ArrayList <Obstaculo>obstaculos;
	private ArrayList <Recogible>recogibles;
	private int puntaje;
	private int tiempo=35;
	private boolean tocarLimite=false;
	private int contadorMapa=0;
	private PFont letra;
	private int sonido;
	private Minim minim;
	private AudioPlayer sonido_inicio;  
	private AudioPlayer sonido_juego;  
	private AudioPlayer sonido_ganaste;  
	private AudioPlayer sonido_perdiste;  
	
	
	
	
	public Logica(PApplet app) {
		this.app=app;
		pantalla=0;
		vel=4;
		panX=0;
		inicio = app.loadImage("inicio.png");
		elegir = app.loadImage("eleccion.png");
		fondoJ = app.loadImage("fondo.png");
		fondoJ2 = app.loadImage("fondo.png");
		start = app.loadImage("btnStart.png");
		start1 = app.loadImage("btnStart1.png");
		meta = app.loadImage("Meta.png");
		punt = app.loadImage("punt.png");
		ganaste = app.loadImage("ganaste.png");
		perdiste = app.loadImage("Perdiste.jpeg");
		atras = app.loadImage("atras.png");
		instrucciones=app.loadImage("instrucciones.png");
		letra= app.createFont("Vampires.otf", 27);
	    app.textFont(letra);
	    
	    servidor = new Servidor(this);
		servidor.start();

		
		//Arreglo y ubicacion de obstaculos
	    obstaculos= new ArrayList<Obstaculo>();
	    for(int i=0; i<4; i++){
	      obstaculos.add(new Cruz (app,(int)app.random(300,7000), (int)app.random(203,503),vel)); 
	      }
	    
	    for(int i=0; i<4; i++){
		      obstaculos.add(new Agua (app,(int)app.random(300,7000), (int)app.random(203,503),vel)); 
		      }
	    
	    for(int i=0; i<4; i++){
		      obstaculos.add(new Biblia (app,(int)app.random(300,7000), (int)app.random(203,503),vel)); 
		      }
	    
	    
	    recogibles= new ArrayList<Recogible>();
	    for(int i=0; i<4; i++){
	    	recogibles.add(new Pentagrama (app,(int)app.random(300,7000), (int)app.random(203,503),vel)); 
	      }
	    
	    for(int i=0; i<4; i++){
	    	recogibles.add(new Sangre (app,(int)app.random(300,7000), (int)app.random(203,503),vel)); 
	      }
	    
	    for(int i=0; i<4; i++){
	    	recogibles.add(new Vela (app,(int)app.random(300,7000), (int)app.random(203,503),vel)); 
	      }
	    
	    //SONIDOS
	    minim= new Minim(app);
	    sonido_inicio= minim.loadFile("sonido_inicio.mp3");
	    sonido_juego= minim.loadFile("sonido_juego.mp3");
	    sonido_ganaste= minim.loadFile("sonido_ganaste.mov");
	    sonido_perdiste= minim.loadFile("sonido_perdiste.mp3");
	    
	}
	
	public void pintar() {
		
		 switch(sonido){
		  case 0:
		    if(!sonido_inicio.isPlaying()){
		      sonido_inicio.rewind();
		      sonido_inicio.play();}
		     sonido_juego.pause();
		     sonido_ganaste.pause();
		     sonido_perdiste.pause();
		  break;
		  case 1:
		  if(!sonido_juego.isPlaying()){
		      sonido_juego.rewind();
		      sonido_juego.play();}
		     sonido_inicio.pause();
		     sonido_ganaste.pause();
		     sonido_perdiste.pause();
		    
		  break;
		  
		  case 2:
			  if(!sonido_ganaste.isPlaying()){
				  sonido_ganaste.rewind();
				  sonido_ganaste.play();}
			     sonido_inicio.pause();
			     sonido_juego.pause();
			     sonido_perdiste.pause();
			    
			  break;
			  
		  case 3:
			  if(!sonido_perdiste.isPlaying()){
				  sonido_perdiste.rewind();
				  sonido_perdiste.play();}
			     sonido_inicio.pause();
			     sonido_juego.pause();
			     sonido_ganaste.pause();
			    
			  break;
		 
		    }
		
		
		app.imageMode(app.CORNER);
		switch(pantalla) {
			case 0:
				sonido=0;
				app.image(inicio,0,0);
				break;
				
			case 1:
				sonido=0;
				app.image(elegir, 0, 0);
				
				break;
				
			case 2:
				sonido=0;
				app.image(instrucciones, 0, 0);
				
				break;
				
			case 3:
				sonido=1;
				app.image(fondoJ, panX, 0);
				app.imageMode(app.CENTER);
				app.image(start, app.width/2, app.height/2);
				
				
				app.imageMode(app.CORNER);
				
				if(iniciar==true) {
					app.image(fondoJ, panX, 0);
					app.image(fondoJ, panX+1200, 0);
					app.image(fondoJ, panX+2400, 0);
					app.image(fondoJ, panX+3600, 0);
					app.image(fondoJ, panX+4800, 0);
					app.image(fondoJ, panX+6000, 0);
					app.image(fondoJ, panX+7200, 0);
					app.image(meta, panX+7200, 0);
					app.imageMode(app.CENTER);
					
					app.imageMode(app.CORNER);
					panX-=vel;
					
					//Condicion para ganar
					if(panX<-7200 && tiempo>0) {
						pantalla=4;
						iniciar=false;
					}
					
					//Temporizador
					if (app.frameCount % 60 == 0) {
						tiempo--; 
					}
					
					
					p.setY(p.getY()+p.vel);
					
					if ( p.getY() >= 550) { 

				        p.vel=-3;
				        tocarLimite = true;
				    
				      }
				      //Limite de arriba
				      if ( p.getY() <= 100 ) { 
				    	  
				        p.vel=5;
				   
				     
				      }
				      
				      //Parar mapa si toca limite de abajo
				      if ( tocarLimite == true ) {
				    	  	panX+=vel;
				         contadorMapa++;
				          
				          
				          
				          //Si toca los limites los objetos y obstaculos paran de moverse
				          for ( int i = 0; i < recogibles.size() ; i++){
				        	  recogibles.get(i).setX(recogibles.get(i).getX()+4);
				          }
				          
				          for ( int i = 0; i < obstaculos.size() ; i++){
				          
				          obstaculos.get(i).setX(obstaculos.get(i).getX()+4);

				          
				        }
				         
				          
				        //Limites del mapa y contador para que se empiece a mover de nuevo
				          if (contadorMapa == 90) {
				            tocarLimite = false;
				            contadorMapa = 0;
				          }
				        }
				      
				      if (app.frameCount % 4 == 0) p.vel += 1;
				      
				      
				          if (tocarObstaculo == true){
				            if (titilar >= 10) { 
				              tocarObstaculo = false;
				              titilar = 0;
				          }
				            if (app.frameCount % 10 == 0 ){
				           
				             p.pintar(); 
				             titilar ++;
				            }
				            
				          }
				          
				          if (tocarObstaculo == true){
				        	  if (app.frameCount % 60 == 0 ){
				        	  	puntaje= puntaje-1;
				        	  	
				        	  	}
				          }
				          
				          //Pintar obstaculos
				          for (int i=0; i<obstaculos.size(); i++) {
				            obstaculos.get(i).pintar();
				          }
				          
				        
				        //Si personaje toca los obstaculos
				        for (int i=0; i<obstaculos.size(); i++) {
				            if (app.dist(obstaculos.get(i).getX(), obstaculos.get(i).getY(), p.getX(), p.getY())<50) {  
				            	tocarObstaculo=true;

				            
				            }
				            //System.out.println(app.dist(obstaculos.get(i).getX(), obstaculos.get(i).getY(), p.getX(), p.getY())<70);
				        }
				        
				          //Pintar Recogibles
				          for (int i=0; i<recogibles.size(); i++) {
				        	  recogibles.get(i).pintar();
				          }
				          
				          
				        //Si toca objetos, desaparece objeto y suma contador del mismo
				          if(tocarObstaculo==false){
				          for (int i=0; i<recogibles.size(); i++) {
				            
				            if (app.dist(recogibles.get(i).getX(), recogibles.get(i).getY(), p.getX(), p.getY())<50) {
				              if (recogibles.get(i) instanceof Pentagrama) {
				                puntaje+=2;
				              }
				              
				              if (recogibles.get(i) instanceof Sangre) {
				                puntaje+=3;
				              }
				              
				              if (recogibles.get(i) instanceof Vela) {
				            	  puntaje+=5;
				              }
				            }

				            if (app.dist(recogibles.get(i).getX(), recogibles.get(i).getY(), p.getX(), p.getY())<50) {
				            	recogibles.remove(i); 
				              
				            }
				          }
				          }
				          
				          
				          
				        
				          if (tocarObstaculo == false){
				        //	  app.fill(255,0,0);
				        //	 app.ellipse(p.getX(), p.getY(), 20, 20);
					          p.pintar();
					          } 
				          
				          //Puntaje y tiempo
				          app.fill(255);
				          app.textAlign(app.CENTER);
				          app.textSize(40);
				          app.text("Puntaje", 312, 55);
				          app.text("Tiempo", 560, 55);
				          app.textSize(30);
				          app.text(puntaje, 312, 88);
				          app.text(tiempo, 560, 88);
				          
				          //Condiciones con las que se pierde
				          
				          
				          if(puntaje<0) {
				        	  puntaje=0;
				        	  pantalla=5;
				          }
				          if(tiempo<0) {
				        	  tiempo=0;
				        	  pantalla=5;
				          }
				          
				          
				  
				}
				
			
			
			
		      
				break;
			
			case 4: 
				sonido=2;
				app.image(ganaste, 0, 0);
				break;
			
			case 5:
				sonido=3;
				app.image(perdiste, 0, 0);				
				break;
			
			
		
		}
		
		botones();
		
	}
	
	public void botones() {
		
		app.imageMode(app.CENTER);
		switch(pantalla) {
	
			
		case 1:
			if(app.mouseX > 95 && app.mouseX <365 && app.mouseY > 170  && app.mouseY<520) {
				//app.fill(0);
				//app.rect(95, 170, 270, 350);
				app.fill(255);
				app.textSize(60);
				app.textAlign(app.CENTER);
				app.text("Madre", 230, 620);
			}
			if(app.mouseX > 480 && app.mouseX < 750 && app.mouseY > 170  && app.mouseY < 520) {
				//app.fill(0);
				//app.rect(480, 170, 270, 350);
				app.fill(255);
				app.textSize(60);
				app.textAlign(app.CENTER);
				app.text("Bebe", 615, 620);
			}
			if(app.mouseX > 855 && app.mouseX < 1125 && app.mouseY > 170  && app.mouseY < 520) {
				//app.fill(0);
				//app.rect(855, 170,270, 350);
				app.fill(255);
				app.textSize(60);
				app.textAlign(app.CENTER);
				app.text("Padre", 990, 620);
			}
			
			
			break;
			
		case 3:
			
			if(app.mouseX > 404 && app.mouseX < 795 && app.mouseY > 300  && app.mouseY < 400 && iniciar==false) {
				app.imageMode(app.CENTER);
				app.image(start1, app.width/2, app.height/2);
				app.imageMode(app.CORNER);
			}
			
			
			break;
		
	}
		
	}
	
	public void mouse() {
		System.out.println("x: " + app.mouseX + " Y: " + app.mouseY);
		
		switch(pantalla) {
		
		case 0:
		pantalla=1;
		break;
		
		case 1:
			if(app.mouseX > 95 && app.mouseX <365 && app.mouseY > 170  && app.mouseY<520) {
				p=new Madre (this,100, 350);
				pantalla=2;
			}
			if(app.mouseX > 480 && app.mouseX < 750 && app.mouseY > 170  && app.mouseY < 520) {
				p=new Bebe (this,100, 350);
				pantalla=2;
			}
			if(app.mouseX > 855 && app.mouseX < 1125 && app.mouseY > 170  && app.mouseY < 520) {
				p=new Padre (this,100, 350);
				pantalla=2;
			}
			break;
			
		case 2:
			pantalla=3;
			break;
		
		case 3:
			if(app.mouseX > 404 && app.mouseX < 795 && app.mouseY > 300  && app.mouseY < 400 && iniciar==false) {
				iniciar=true;
			}
			
			break;
			
		}
	}
	
	public void ejecutarTeclado() {
	    if (app.key==' ') {
	      switch(pantalla) {
	      case 3:
	        p.vel=-6;
	        break;
	      }
	    }
	  }	
	
	
	
	public int getPantalla() {
		return pantalla;
	}

	public void setPantalla(int pantalla) {
		this.pantalla = pantalla;
	}
	
	

	public Boolean getIniciar() {
		return iniciar;
	}

	public void setIniciar(Boolean iniciar) {
		this.iniciar = iniciar;
	}

	public PApplet getPApplet() {
		return app;
		
	}
}


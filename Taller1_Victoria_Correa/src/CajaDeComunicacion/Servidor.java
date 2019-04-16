package CajaDeComunicacion;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Principal.Logica;

public class Servidor extends Thread{

	Logica log;
	ServerSocket ss;
	Mensaje mensaje;
	ArrayList<Receptor> receptores;
	static int puerto=6000;
	
	
	public Servidor(Logica log) {
		this.log=log;
		
		receptores = new ArrayList<>();
		mensaje = new Mensaje();
		
		try {
			ss= new ServerSocket(puerto);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println("Esperando Conexion");
				Socket s=ss.accept();
				System.out.println("Conexion exitosa");
				Receptor r= new Receptor(s);
				r.start();
				
				receptores.add(r);
				
				if(receptores.size()==2) {
					log.p.construirComunicacion(r);
					System.out.println("Conectado jugador 1");
					mensaje.id=1;
					r.enviar(mensaje);
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList obtenerRecceptores() {
		return receptores;
		
	}
	
	
	
}

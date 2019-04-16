package CajaDeComunicacion;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.net.Socket;

public class Receptor extends Thread {

	Socket s;
	OnRecieved mensajote;
	Mensajero mensajero;
	
	
	public Receptor(Socket s) {
		this.s= s;
		
	}
	public void run() {
		while(true) {
			recibir();
		}
	}
	
	public void recibir() {
		
		try {
			InputStream entrada= s.getInputStream();
			ObjectInputStream obEntrada= new ObjectInputStream(entrada);
							
			Mensaje mRecibido= (Mensaje) obEntrada.readObject();
			if(mensajote !=null) {
			mensajote.obtengoMensaje(mRecibido);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void enviar(final Mensaje mensaje){

		new Thread(new Runnable() {
		@Override
		public void run() {
		try {
		OutputStream salida= s.getOutputStream();

		ObjectOutputStream obSalida= new ObjectOutputStream(salida);

		obSalida.writeObject(mensaje);

		} catch (IOException e) {
		e.printStackTrace();
		}

		}
		}).start();

		}
	
	public interface OnRecieved{
		 public void obtengoMensaje(Mensaje mensajeRecibido);
	}

	public void setMensajote(OnRecieved mensajote) {
		this.mensajote = mensajote;
	}
	
	public interface Mensajero{
		public void respuesta(Mensaje mensaje);
	}
	
	public void cambiarMensajero(Mensajero reemplazo) {
		this.mensajero=reemplazo;
	}
}

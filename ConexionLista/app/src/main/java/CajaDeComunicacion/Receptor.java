package CajaDeComunicacion;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Receptor extends Thread{

    Socket socket;
    Mensajero mensajero;

    public Receptor(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        while (true){
            recibir();
        }
    }

    public void recibir(){
        try {
            InputStream entrada= socket.getInputStream();
            ObjectInputStream oEntrada= new ObjectInputStream(entrada);
            Mensaje mensaje= (Mensaje) oEntrada.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public interface Mensajero{
        public void respuesta(Mensaje mensaje);
    }

    public void cambiarMensajero(Mensajero reemplazo){
        this.mensajero=reemplazo;
    }
}

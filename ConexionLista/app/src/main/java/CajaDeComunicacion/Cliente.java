package CajaDeComunicacion;

import android.widget.Toast;

import com.example.estudiante.conexionlista.MainActivity;
import com.example.estudiante.conexionlista.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente extends Thread{

    Socket socket;
    String IPWifi;
    int puerto;
    int id =0;
    MainActivity main;
    dato datos;


    public Cliente(int puerto, String IPWifi, MainActivity main){
        this.puerto=puerto;
        this.IPWifi=IPWifi;
        this.main=main;

        start();

    }

    @Override
    public void run() {
        try {
            socket = new Socket(IPWifi,puerto);

            recibir();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inSocket(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(IPWifi,puerto);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void enviar(final Mensaje mensaje){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OutputStream salida= socket.getOutputStream();

                    ObjectOutputStream obSalida= new ObjectOutputStream(salida);

                    obSalida.writeObject(mensaje);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    public void recibir() {



                try {
                    InputStream entrada= socket.getInputStream();
                    ObjectInputStream obEntrada= new ObjectInputStream(entrada);

                    Mensaje mRecibido= (Mensaje) obEntrada.readObject();
                    id=mRecibido.id;

                    System.out.println(id+"     gggggggggggggg"+main);
                    datos.obtenerId(id);



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e){

                }


    }

    public long getId(){
        return id;
    }

    public interface dato{
        public void obtenerId(int id);
    }

    public void cambiarObserver(dato d){
        this.datos=d;
    }
}


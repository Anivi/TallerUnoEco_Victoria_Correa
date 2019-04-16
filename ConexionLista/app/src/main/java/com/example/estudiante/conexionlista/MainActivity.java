package com.example.estudiante.conexionlista;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import CajaDeComunicacion.Cliente;
import CajaDeComunicacion.Mensaje;
import CajaDeComunicacion.Cliente.dato;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,dato {

    Cliente cliente;
    Mensaje mensaje;
    Button btn;
    ConstraintLayout layout;
    public ImageView fondo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // btn=findViewById(R.id.btn_prueba);

        Button saltar = findViewById(R.id.ini);
        Button pasar = findViewById(R.id.botonA);
        layout= findViewById(R.id.layout);

        fondo1= findViewById(R.id.fondo1);

       // btn.setOnTouchListener(this);
        saltar.setOnTouchListener(this);
        pasar.setOnTouchListener(this);


        mensaje= new Mensaje();
        cliente= new Cliente(6000, "192.168.0.18",this);

    }

    public void funcionBotones(View view){
        switch (view.getId()){

            case R.id.ini:
                mensaje.saltar=true;
                cliente.enviar(mensaje);
                break;

            case R.id.botonA:
                mensaje.pasar=true;
                cliente.enviar(mensaje);
                Log.i("MyApp", "boton funciona");
                break;

        }
    }

    public void funcionBotonesRelease(View view){
        switch (view.getId()){
            case R.id.ini:

                mensaje.saltar=!true;
                cliente.enviar(mensaje);
                break;

            case R.id.botonA:
                mensaje.pasar=!true;
                cliente.enviar(mensaje);
                break;
        }
    }

    public void cambiarFondo(int a){
        if(a==2){
            fondo1.setImageResource(R.drawable.Control);
        }else {
            fondo1.setImageResource(R.drawable.control1);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

            funcionBotones(view);

        }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
            funcionBotonesRelease(view);
        }


        return false;
    }

    @Override
    public void obtenerId(int id) {
        if(id==2){
            fondo1.setImageResource(R.drawable.control1);
          //  layout.setBackgroundColor();

        }else if(id!=2){
            fondo1.setImageResource(R.drawable.control2);
        }
    }
}


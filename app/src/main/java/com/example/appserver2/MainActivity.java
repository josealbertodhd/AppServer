package com.example.appserver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button b_entrar;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_entrar = findViewById(R.id.b_entrar);

        b_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    String dateTime = simpleDateFormat.format(calendar.getTime());
                    OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("LogAccesos.txt", Context.MODE_APPEND));
                    archivo.write( dateTime +"\n");
                    archivo.flush();
                    archivo.close();


                }catch (IOException e){
                    Toast.makeText(MainActivity.this, "Error, no se pudo crear el archivo", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(MainActivity.this, Inicio.class);
                startActivity(intent);
            }
        });

        //log de acceso, compartir
    }
}
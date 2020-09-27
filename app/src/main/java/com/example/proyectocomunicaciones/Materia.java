package com.example.proyectocomunicaciones;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Materia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_materia);
    }

    public void BotonGuardar(View view){
        Toast.makeText(this, "Se ingreso la nueva materia", Toast.LENGTH_SHORT).show();
    }


}

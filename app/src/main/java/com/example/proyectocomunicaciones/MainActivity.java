package com.example.proyectocomunicaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> telefonos=new ArrayList<String>();
    ArrayAdapter<String>  adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BotonIngNuevaMateria(View view){
        setContentView(R.layout.crear_materia);
    }

    public void BotonRevisarMaterias(View view){
        setContentView(R.layout.materias);
        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,telefonos);
        ListView listaMaterias = (ListView)findViewById(R.id.ListaMaterias);
        listaMaterias.setAdapter(adaptador);
        listaMaterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = parent.getAdapter().getItem(position);
                Toast.makeText(getBaseContext(), object.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void agregarMateria(View v) {
        EditText text = (EditText) findViewById(R.id.NombreMateria);
        telefonos.add(text.getText().toString());
        adaptador.notifyDataSetChanged();
        text.setText("");
    }

    public void BotonGuardarMateria(){
        setContentView(R.layout.crear_materia);
    }

    public void BotonVolver(View view){
        setContentView(R.layout.activity_main);
    }
}
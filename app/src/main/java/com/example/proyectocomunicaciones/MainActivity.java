package com.example.proyectocomunicaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> materias=new ArrayList<String>();
    ArrayAdapter<String>  adaptador;
    ArrayList<Integer> listaCreditos=new ArrayList<Integer>();



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
        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,materias);
        ListView listaMaterias = (ListView)findViewById(R.id.ListaMaterias);
        listaMaterias.setAdapter(adaptador);
        listaMaterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = parent.getAdapter().getItem(position);
                Toast.makeText(getBaseContext(), object.toString(), Toast.LENGTH_SHORT).show();
                setContentView(R.layout.materias_info);
                TextView materia = (TextView) findViewById(R.id.materia);
                materia.setText(object.toString());
                TextView creditos = (TextView) findViewById(R.id.creditos);
                creditos.setText(listaCreditos.get(position).toString());
            }
        });
    }


    public void agregarMateria(View v) {
        EditText nombreMateria = (EditText) findViewById(R.id.NombreMateria);
        EditText cantidadCreditos = (EditText) findViewById(R.id.CantidadCreditos);
        materias.add(nombreMateria.getText().toString());
        String texto = cantidadCreditos.getText().toString();
        int num =Integer.parseInt(texto);
        listaCreditos.add(num);
        //adaptador.notifyDataSetChanged();
        nombreMateria.setText("");
        cantidadCreditos.setText("");
    }

    public void BotonVolver(View view){
        setContentView(R.layout.activity_main);
    }
}
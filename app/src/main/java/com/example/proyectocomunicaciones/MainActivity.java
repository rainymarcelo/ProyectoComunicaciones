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
    ArrayList<Materia> listaMaterias=new ArrayList<Materia>();
    ArrayList<String> listaNombres = new ArrayList<String>();
    ArrayAdapter<String>  adaptador;
    int posicion;


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
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaNombres);
        ListView listaMateria = (ListView)findViewById(R.id.ListaMaterias);
        listaMateria.setAdapter(adaptador);
        listaMateria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = parent.getAdapter().getItem(position);
                Toast.makeText(getBaseContext(), object.toString(), Toast.LENGTH_SHORT).show();
                setContentView(R.layout.materias_info);
                TextView materia = (TextView) findViewById(R.id.materia);
                TextView creditos = (TextView) findViewById(R.id.creditos);
                materia.setText(object.toString());
                creditos.setText(listaMaterias.get(position).getCreditos().toString());
                posicion = position;
            }
        });
    }


    public void agregarMateria(View v) {
        EditText nombreMateria = (EditText) findViewById(R.id.NombreMateria);
        EditText cantidadCreditos = (EditText) findViewById(R.id.CantidadCreditos);
        String texto = cantidadCreditos.getText().toString();
        Integer num = Integer.parseInt(texto);

        Materia materia = new Materia(nombreMateria.getText().toString(), num, 0);
        listaMaterias.add(materia);
        listaNombres.add(materia.getNombreMateria());
        //adaptador.notifyDataSetChanged();
        nombreMateria.setText("");
        cantidadCreditos.setText("");
        Toast.makeText(getBaseContext(), "La materia se ha guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    public void AgregarNota(View view){
        Materia materia = listaMaterias.get(posicion);
        EditText nombreNota = (EditText) findViewById(R.id.nombreActividadT);
        EditText notaNota = (EditText) findViewById(R.id.notaT);
        TextView notaNecesaria = (TextView) findViewById(R.id.notaNecesariaT);
        TextView porcentaje = (TextView) findViewById(R.id.porcentajeFinalT);
        EditText porcentajeNota = (EditText) findViewById(R.id.porcentajeT);

        if(Integer.parseInt(notaNota.getText().toString()) > 100 || Integer.parseInt(notaNota.getText().toString()) < 1){
            Toast.makeText(getBaseContext(), "El porcentaje debe estar entre 1% y 100%", Toast.LENGTH_SHORT).show();
        }else if(Integer.parseInt(notaNota.getText().toString()) > 5 || Integer.parseInt(notaNota.getText().toString()) < 0) {
            Toast.makeText(getBaseContext(), "La nota debe estar entre 0 y 5", Toast.LENGTH_SHORT).show();
        }else if(100 - Integer.parseInt(notaNota.getText().toString()) < Integer.parseInt(porcentajeNota.getText().toString())){
            Toast.makeText(getBaseContext(), "El porcentaje excede el 100%", Toast.LENGTH_SHORT).show();
        }else{
            TextView notaFinal = (TextView) findViewById(R.id.notaFinalT);

            Notas nota = new Notas(nombreNota.getText().toString(), Integer.parseInt(notaNota.getText().toString())
                    , Integer.parseInt(porcentajeNota.getText().toString()));
            materia.addNota(nota);
            materia.notaActual();
            Double notaF = materia.getNota();
            notaFinal.setText(notaF.toString());

            Integer percent = materia.porcentajeActual();
            porcentaje.setText(percent.toString() + "%");

            notaNecesaria.setText(materia.notaNecesaria().toString());

            nombreNota.setText("");
            notaNota.setText("");
            porcentajeNota.setText("");
        }
    }

    public void BotonVolver(View view){
        setContentView(R.layout.activity_main);
    }

    public void BotonVolverMaterias(View view){
        setContentView(R.layout.materias);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaNombres);
        ListView listaMateria = (ListView)findViewById(R.id.ListaMaterias);
        listaMateria.setAdapter(adaptador);
        listaMateria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = parent.getAdapter().getItem(position);
                Toast.makeText(getBaseContext(), object.toString(), Toast.LENGTH_SHORT).show();
                setContentView(R.layout.materias_info);
                TextView materia = (TextView) findViewById(R.id.materia);
                TextView creditos = (TextView) findViewById(R.id.creditos);
                materia.setText(object.toString());
                creditos.setText(listaMaterias.get(position).getCreditos().toString());
                posicion = position;
            }
        });
    }
}
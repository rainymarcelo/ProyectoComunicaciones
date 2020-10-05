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
    Integer posicion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BotonIngNuevaMateria(View view){//cambia layout  la de crear materia
        setContentView(R.layout.crear_materia);
    }

    public void BotonRevisarMaterias(View view){//selecciona la materia para mostrar la info de esta
        setContentView(R.layout.materias);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaNombres);
        ListView listaMateria = (ListView)findViewById(R.id.ListaMaterias);
        listaMateria.setAdapter(adaptador);
        listaMateria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//revisa que materia selecciono el usuario
                Object object = parent.getAdapter().getItem(position);
                Toast.makeText(getBaseContext(), object.toString(), Toast.LENGTH_SHORT).show();
                setContentView(R.layout.materias_info);
                TextView materiaT = (TextView) findViewById(R.id.materia);
                TextView creditosT = (TextView) findViewById(R.id.creditos);
                materiaT.setText(object.toString());
                creditosT.setText(listaMaterias.get(position).getCreditos().toString());
                posicion = position;
            }
        });
    }


    public void agregarMateria(View v) {// boton para guardar la nueva materia
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

    public void AgregarNota(View view){// boton agreagar nota
        Materia materia = listaMaterias.get(posicion);
        EditText nombreNota = (EditText) findViewById(R.id.nombreActividadT);
        EditText notaNota = (EditText) findViewById(R.id.notaT);
        TextView notaNecesaria = (TextView) findViewById(R.id.notaNecesariaT);
        TextView porcentaje = (TextView) findViewById(R.id.porcentajeFinalT);
        EditText porcentajeNota = (EditText) findViewById(R.id.porcentajeT);

        if(Integer.parseInt(porcentajeNota.getText().toString()) > 100 || Integer.parseInt(porcentajeNota.getText().toString()) < 1){
            Toast.makeText(getBaseContext(), "El porcentaje debe estar entre 1% y 100%", Toast.LENGTH_SHORT).show();
        }else if(Double.parseDouble(notaNota.getText().toString()) > 5 || Double.parseDouble(notaNota.getText().toString()) < 0) {
            Toast.makeText(getBaseContext(), "La nota debe estar entre 0 y 5", Toast.LENGTH_SHORT).show();
        }else if(100 - Double.parseDouble(notaNota.getText().toString()) < Integer.parseInt(porcentajeNota.getText().toString())){
            Toast.makeText(getBaseContext(), "El porcentaje excede el 100%", Toast.LENGTH_SHORT).show();
        }else{
            TextView notaFinal = (TextView) findViewById(R.id.notaFinalT);

            Notas nota = new Notas(nombreNota.getText().toString(), Double.parseDouble(notaNota.getText().toString())
                    , Integer.parseInt(porcentajeNota.getText().toString()));

            materia.addNota(nota);
            materia.notaActual();
            Double notaF = round(materia.getNota(), 2);
            notaFinal.setText(notaF.toString());

            Integer percent = materia.porcentajeActual();
            porcentaje.setText(percent.toString() + "%");

            Double ntanes = round(materia.notaNecesaria(), 2);
            notaNecesaria.setText(ntanes.toString());


            nombreNota.setText("");
            notaNota.setText("");
            porcentajeNota.setText("");

            adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, materia.lista());
            ListView listaMateria = (ListView)findViewById(R.id.actividades);
            listaMateria.setAdapter(adaptador);
        }
    }

    public void BotonVolver(View view){// boton volver al inicio
        setContentView(R.layout.activity_main);
    }

    public void BotonVolverMaterias(View view){// boton volver a seleccionar materia
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

    public void verEvaluaciones(View view){// boton mostrar notas de una materia
        Materia materia=listaMaterias.get(posicion);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, materia.lista());
        ListView listaMateria = (ListView)findViewById(R.id.actividades);
        listaMateria.setAdapter(adaptador);

        TextView notaFinal = (TextView) findViewById(R.id.notaFinalT);
        Double notaF = round(materia.getNota(), 2);
        notaFinal.setText(notaF.toString());

        TextView porcentaje = (TextView) findViewById(R.id.porcentajeFinalT);
        Integer percent = materia.porcentajeActual();
        porcentaje.setText(percent.toString() + "%");

        TextView notaNecesaria = (TextView) findViewById(R.id.notaNecesariaT);
        Double ntanes = round(materia.notaNecesaria(), 2);
        notaNecesaria.setText(ntanes.toString());
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
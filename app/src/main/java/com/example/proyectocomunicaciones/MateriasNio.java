package com.example.proyectocomunicaciones;

import android.os.Build;

import androidx.annotation.RequiresApi;// no se que es esto

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MateriasNio {

    private final static String NOMBRE_ARCHIVO="materias";
    private final static Path ARCHIVO= Paths.get(NOMBRE_ARCHIVO);
    private final static String FIELD_SEPARATOR=",";
    private final static String RECORD_SEPARATOR=System.lineSeparator();

    public MateriasNio() throws IOException {
        if (!Files.exists(ARCHIVO)){
            Files.createFile(ARCHIVO);
        }
    }

    public void registrarMaterias(Materia materia){
        String materiasString = parseMaterias2String(materia);
        byte[] datosRegistro = materiasString.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(datosRegistro);
        try (FileChannel fileChannel=FileChannel.open(ARCHIVO,APPEND)){
            fileChannel.write(byteBuffer);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private String parseMaterias2String(Materia materias){
        StringBuilder sb=new StringBuilder();
        sb.append(materias.getNombreMateria()).append(FIELD_SEPARATOR)
                .append(materias.getCreditos()).append(FIELD_SEPARATOR)
                .append(materias.getEvaluaciones()).append(RECORD_SEPARATOR);
        return sb.toString();
    }

    public List<Materia> ListarMaterias(){
        final List<Materia> materias= new ArrayList<>();
        try (Stream<String> stream= Files.lines(ARCHIVO)){
            stream.forEach(materiasString -> materias.add(parseMaterias2Object(materiasString)));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return materias;
    }

    private Materia parseMaterias2Object(String materiasString){
        String[] datosMateria= materiasString.split(FIELD_SEPARATOR);
        // todo:validar que el tamaño del arreglo sea de 3 elementos
        Materia materia= new Materia(datosMateria[0],
                Integer.parseInt(datosMateria[1]),
                Double.parseDouble(datosMateria[2]));
        return materia;
    }

}

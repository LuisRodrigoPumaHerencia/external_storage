package com.rodrigo.clase07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LeerContenidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_contenido);

        //ONTENIENDO LOS COMPONENTES
        TextView txt_contenido = findViewById(R.id.contenido_archivo);

        pedir_persmisos();

        File ruta = Environment.getExternalStorageDirectory();
        File archivo = new File(ruta, "clase12.txt");

        int tamano = (int) archivo.length();

        byte[] bytes = new byte[tamano];

        FileInputStream input = null;

        try {
            input = new FileInputStream(archivo);
            input.read(bytes);
            input.close();

            String contenido_str = new String(bytes);
            txt_contenido.setText(contenido_str);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void pedir_persmisos(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
            }else{
                ActivityCompat.requestPermissions(LeerContenidoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
            }
        }
    }
}
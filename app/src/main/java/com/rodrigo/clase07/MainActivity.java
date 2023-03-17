package com.rodrigo.clase07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OBTENIENDO COMPONENTES
        TextInputEditText input_contenido = findViewById(R.id.contenido);
        MaterialButton btn_escribir = findViewById(R.id.btn_escribir);
        MaterialButton btn_ver_contenido = findViewById(R.id.btn_ver_contenido);

        btn_escribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedir_persmisos();
                File ruta = Environment.getExternalStorageDirectory();
                File archivo = new File(ruta, "clase12.txt");
                FileOutputStream stream = null;

                try {

                    stream = new FileOutputStream(archivo, true);
                    String contenido_txt = String.valueOf(input_contenido.getText())+"\n";
                    stream.write(contenido_txt.getBytes());
                    stream.close();
                    Toast.makeText(MainActivity.this, "Contenido agregado", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, LeerContenidoActivity.class);
                    startActivity(i);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });


    }

    public void pedir_persmisos(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            }else{
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
            }
        }
    }
}
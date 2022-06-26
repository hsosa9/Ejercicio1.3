package com.example.ejercicio13;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText txt_Nombre, txt_Apellido, txt_Edad, txt_Correo, txt_Direccion;
    Button btn_Salvar;
    Button btn_Siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Nombre = (EditText) findViewById(R.id.txt_Nombre);
        txt_Apellido = (EditText) findViewById(R.id.txt_Apellido);
        txt_Edad = (EditText) findViewById(R.id.txt_Edad);
        txt_Correo = (EditText) findViewById(R.id.txt_Correo);
        txt_Direccion = (EditText) findViewById(R.id.txt_Direccion);

    }

    public void Salvar (View view){

        SQLliteConexion conexion = new SQLliteConexion(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, txt_Nombre.getText().toString());
        valores.put(Transacciones.apellidos, txt_Apellido.getText().toString());
        valores.put(Transacciones.edad, txt_Edad.getText().toString());
        valores.put(Transacciones.correo, txt_Correo.getText().toString());
        valores.put(Transacciones.direccion, txt_Direccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablaPersona, Transacciones.id, valores);
        Toast.makeText(this, "Registro Ingresado Correctamente", Toast.LENGTH_LONG).show();
        db.close();// Cerrar conexion a base de datos

        ClearScreen(); //borrar


    }



    private void ClearScreen() {
        txt_Nombre.setText("");
        txt_Apellido.setText("");
        txt_Correo.setText("");
        txt_Edad.setText("");
        txt_Direccion.setText("");


    }

    public void Siguiente (View view){

        Intent i = new Intent(this, ActivityConsulta.class);
        startActivity(i);
    }

}
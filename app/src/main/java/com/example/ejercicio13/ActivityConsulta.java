package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityConsulta extends AppCompatActivity {

    SQLliteConexion conexion;

    EditText txt_sid;
    EditText txt_snombre;
    EditText txt_sapellido;
    EditText txt_sedad;
    EditText txt_scorreo;
    EditText txt_sdireccion;

    Button btn_Buscar;
    Button btn_Actualizar;
    Button btn_Eliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        conexion = new SQLliteConexion(this, Transacciones.NameDataBase, null, 1);

        txt_sid = (EditText) findViewById(R.id.txt_sid);
        txt_snombre = (EditText) findViewById(R.id.txt_snombre);
        txt_sapellido = (EditText) findViewById(R.id.txt_sapellido);
        txt_sedad = (EditText) findViewById(R.id.txt_sedad);
        txt_scorreo = (EditText) findViewById(R.id.txt_scorreo);
        txt_sdireccion = (EditText) findViewById(R.id.txt_sdireccion);

        btn_Buscar = (Button) findViewById(R.id.btn_Buscar);
        btn_Actualizar = (Button) findViewById(R.id.btn_Actualizar);
        btn_Eliminar = (Button) findViewById(R.id.btn_Eliminar);

    }

    private void BuscarPersona() {
        try{

            SQLiteDatabase db = conexion.getWritableDatabase();
            //Parametros de busqueda de la sentencia Select

            //String [] params = {id.getText().toString()};

            String [] params ={txt_sid.getText().toString()};
            //Campos a retornar de la sentencia Select

            String [] fields = {Transacciones.nombres,
                    Transacciones.apellidos,
                    Transacciones.edad,
                    Transacciones.correo,
                    Transacciones.direccion};

            String WhereCondition = Transacciones.id + "=?";

            Cursor cdata = db.query(Transacciones.tablaPersona,
                    fields,
                    WhereCondition, params, null, null, null);

            cdata.moveToFirst();

            if (cdata.getCount()>0){

                txt_snombre.setText(cdata.getString(0));
                txt_sapellido.setText(cdata.getString(1));
                txt_sedad.setText(cdata.getString(2));
                txt_scorreo.setText(cdata.getString(3));
                txt_sdireccion.setText(cdata.getString(4));

                Toast.makeText(this, "Consultado con exito", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "No Hay Datos" + "", Toast.LENGTH_LONG).show();
            }



        }
        catch (Exception ex){
            Toast.makeText(this, "Error" + "", Toast.LENGTH_LONG).show();
        }


    }

    public void Buscar(View view){
        BuscarPersona();


    }

    private void Delete() {
        conexion = new SQLliteConexion(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {txt_sid.getText().toString()};

        db.delete(Transacciones.tablaPersona,Transacciones.id+"=?",params);
        Toast.makeText(getApplicationContext(), "Dato Eliminado", Toast.LENGTH_LONG).show();
    }

    public void Eliminar (View view){
        Delete();
        txt_sid.setText("");
        txt_snombre.setText("");
        txt_sapellido.setText("");
        txt_sedad.setText("");
        txt_scorreo.setText("");
        txt_sdireccion.setText("");

    }

    public void Regresar (View view){

        Intent x = new Intent(this, MainActivity.class);
        startActivity(x);

    }

    public void actu (){

        conexion = new SQLliteConexion(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {txt_sid.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, txt_snombre.getText().toString());
        valores.put(Transacciones.apellidos, txt_sapellido.getText().toString());
        valores.put(Transacciones.edad, txt_sedad.getText().toString());
        valores.put(Transacciones.correo, txt_scorreo.getText().toString());
        valores.put(Transacciones.direccion, txt_sdireccion.getText().toString());
        db.update(Transacciones.tablaPersona, valores, Transacciones.id + "=?", params);
        Toast.makeText(this, "Dato Actualizado" , Toast.LENGTH_LONG).show();
    }

    public void Actualizar(View view){
        actu ();

        txt_sid.setText("");
        txt_snombre.setText("");
        txt_sapellido.setText("");
        txt_sedad.setText("");
        txt_scorreo.setText("");
        txt_sdireccion.setText("");
    }


}
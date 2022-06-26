package com.example.ejercicio13;

public class Transacciones {

    // NOMBRE DE LA BASE DE DATOS

    public static final String NameDataBase = "EJ13";

    //Creacion de la tablas de la base de datos

    public static final String tablaPersona = "persona";

    // Campos de la tabla persona

    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    // Sentencia SQL para crear tabla
    public static final String CreateTablePersona = "CREATE TABLE persona" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombres TEXT, apellidos TEXT, edad INTEGER," +
            "correo TEXT, direccion TEXT)";

    //Eliminar tabla de la base de datos
    public static final String DropTablePersona = "DROP TABLE IF EXISTS persona";
}

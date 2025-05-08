package com.example.mainactivity;

public class DefBD {

  public static final String tabla_libros = "libros";

  public static final String col_libro_codigo = "codigo";
  public static final String col_libro_nombre = "nombre";
  public static final String col_libro_autor = "autor";
  public static final String col_libro_editorial = "editorial";

  // Sentencia SQL para crear la tabla de libros
  public static final String create_tabla_libros = "CREATE TABLE " + tabla_libros + " (" +
    col_libro_codigo + " INTEGER PRIMARY KEY," +  // o TEXT si deseas usar códigos alfanuméricos
    col_libro_nombre + " TEXT NOT NULL," +
    col_libro_autor + " TEXT NOT NULL," +
    col_libro_editorial + " TEXT NOT NULL)";
}

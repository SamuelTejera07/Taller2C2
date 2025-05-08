package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class LibroController {

  private BaseDatos bd;
  private Context c;

  public LibroController(Context c) {
    this.bd = new BaseDatos(c, 1);
    this.c = c;
  }

  public void agregarLibro(Libro l) {
    try {
      ContentValues valores = new ContentValues();
      valores.put(DefBD.col_libro_codigo, l.getCodigo());
      valores.put(DefBD.col_libro_nombre, l.getNombre());
      valores.put(DefBD.col_libro_autor, l.getAutor());
      valores.put(DefBD.col_libro_editorial, l.getEditorial());

      if (!buscarLibro(l)) {
        SQLiteDatabase sql = bd.getWritableDatabase();
        sql.insert(DefBD.tabla_libros, null, valores);
        Toast.makeText(c, "Libro registrado", Toast.LENGTH_LONG).show();
      } else {
        Toast.makeText(c, "Libro ya existe", Toast.LENGTH_LONG).show();
      }
    } catch (Exception ex) {
      Toast.makeText(c, "Error agregando libro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
    }
  }

  public boolean buscarLibro(Libro l) {
    String[] args = {l.getCodigo()};
    SQLiteDatabase sql = bd.getReadableDatabase();
    Cursor cursor = sql.query(DefBD.tabla_libros, null, "codigo=?", args, null, null, null);
    boolean encontrado = cursor.getCount() > 0;
    cursor.close();
    bd.close();
    return encontrado;
  }

  public boolean buscarLibro(String cod) {
    String[] args = {cod};
    SQLiteDatabase sql = bd.getReadableDatabase();
    Cursor cursor = sql.query(DefBD.tabla_libros, null, "codigo=?", args, null, null, null);
    boolean encontrado = cursor.getCount() > 0;
    cursor.close();
    bd.close();
    return encontrado;
  }

  public Cursor allLibros() {
    try {
      SQLiteDatabase sql = bd.getReadableDatabase();
      return sql.query(DefBD.tabla_libros, null, null, null, null, null, null);
    } catch (Exception ex) {
      Toast.makeText(c, "Error al consultar libros: " + ex.getMessage(), Toast.LENGTH_LONG).show();
      return null;
    }
  }

  public Cursor allLibros2() {
    try {
      SQLiteDatabase sql = bd.getReadableDatabase();
      return sql.rawQuery("SELECT codigo AS _id, nombre, autor, editorial FROM libros ORDER BY " + DefBD.col_libro_codigo, null);
    } catch (Exception ex) {
      Toast.makeText(c, "Error al consultar libros: " + ex.getMessage(), Toast.LENGTH_LONG).show();
      return null;
    }
  }

  public void eliminarLibro(String cod) {
    try {
      if (!buscarLibro(cod)) {
        Toast.makeText(c, "Código no existe", Toast.LENGTH_LONG).show();
      } else {
        SQLiteDatabase sql = bd.getWritableDatabase();
        sql.delete(DefBD.tabla_libros, "codigo=?", new String[]{cod});
        Toast.makeText(c, "Libro eliminado", Toast.LENGTH_LONG).show();
      }
    } catch (Exception ex) {
      Toast.makeText(c, "Error al eliminar libro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
    }
  }

  public void actualizarLibro(Libro l) {
    try {
      SQLiteDatabase sql = bd.getWritableDatabase();
      ContentValues valores = new ContentValues();
      valores.put(DefBD.col_libro_nombre, l.getNombre());
      valores.put(DefBD.col_libro_autor, l.getAutor());
      valores.put(DefBD.col_libro_editorial, l.getEditorial());
      sql.update(DefBD.tabla_libros, valores, "codigo=?", new String[]{l.getCodigo()});
      Toast.makeText(c, "Libro actualizado", Toast.LENGTH_LONG).show();
    } catch (Exception ex) {
      Toast.makeText(c, "Error al actualizar libro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
    }
  }
}

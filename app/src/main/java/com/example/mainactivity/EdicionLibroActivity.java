package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EdicionLibroActivity extends AppCompatActivity {

  EditText codigo, nombre, autor, editorial;
  Button actualizar, eliminar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edicion);


    Intent i = getIntent();
    String cod = i.getStringExtra("codigo");
    String nom = i.getStringExtra("nombre");
    String aut = i.getStringExtra("autor");
    String edi = i.getStringExtra("editorial");


    codigo = findViewById(R.id.edtcod);
    nombre = findViewById(R.id.edtnom);
    autor = findViewById(R.id.edtautor);
    editorial = findViewById(R.id.edteditorial);
    actualizar = findViewById(R.id.btnactualizar);
    eliminar = findViewById(R.id.btneliminar);


    codigo.setText(cod);
    codigo.setEnabled(false); // Código no editable
    nombre.setText(nom);
    autor.setText(aut);
    editorial.setText(edi);

    eliminar.setOnClickListener(view -> {
      AlertDialog.Builder builder = new AlertDialog.Builder(EdicionLibroActivity.this);
      builder.setPositiveButton("SI", (dialog, id) -> {
        LibroController lc = new LibroController(getApplication());
        lc.eliminarLibro(codigo.getText().toString());
        startActivity(new Intent(getApplicationContext(), ListadoActivity.class));
      });
      builder.setNegativeButton("NO", (dialog, id) -> {
        // Cancelar
      });
      builder.setMessage("¿Está seguro de eliminar el libro?")
        .setTitle("Biblioteca");
      builder.show();
    });

    actualizar.setOnClickListener(view -> {
      Libro libro = new Libro(
        codigo.getText().toString(),
        nombre.getText().toString(),
        autor.getText().toString(),
        editorial.getText().toString()
      );
      LibroController lc = new LibroController(getApplication());
      lc.actualizarLibro(libro);
      startActivity(new Intent(getApplicationContext(), ListadoActivity.class));
    });
  }
}

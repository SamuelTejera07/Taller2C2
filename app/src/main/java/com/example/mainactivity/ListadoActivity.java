package com.example.mainactivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListadoActivity extends AppCompatActivity {

  ListView listado;
  LibroController libroController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listado);

    listado = findViewById(R.id.lstlistado);
    libroController = new LibroController(this);


    Cursor c = libroController.allLibros2();


    LibroCursorAdapter adapter = new LibroCursorAdapter(this, c, false);
    listado.setAdapter(adapter);


    listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView cod = view.findViewById(R.id.txtcodigo);
        TextView nombre = view.findViewById(R.id.txtnombre);
        TextView autor = view.findViewById(R.id.txtautor);
        TextView editorial = view.findViewById(R.id.txteditorial);

        // Mostrar un mensaje temporal
        Toast.makeText(
          getApplicationContext(),
          cod.getText().toString() + "," + nombre.getText().toString()
            + "," + autor.getText().toString() + "," + editorial.getText().toString(),
          Toast.LENGTH_LONG
        ).show();

        // Enviar datos a la actividad de edición
        Intent i = new Intent(getApplicationContext(), EdicionLibroActivity.class);
        i.putExtra("codigo", cod.getText().toString());
        i.putExtra("nombre", nombre.getText().toString());
        i.putExtra("autor", autor.getText().toString());
        i.putExtra("editorial", editorial.getText().toString());
        startActivity(i);
      }
    });
  }
}

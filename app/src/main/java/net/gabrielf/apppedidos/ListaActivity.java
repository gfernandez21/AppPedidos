package net.gabrielf.apppedidos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListaActivity extends AppCompatActivity {
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        if (NavigationActivity.global ==1) {
            DatabaseHelper dbHandler;
            dbHandler = new DatabaseHelper(this);
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            cursor = dbHandler.listaritems();
        }

        if (NavigationActivity.global ==2) {
            DatabaseHelper dbHandler;
            dbHandler = new DatabaseHelper(this);
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            cursor = dbHandler.listaritems2();
        }

        if (NavigationActivity.global ==3) {
            DatabaseHelper dbHandler;
            dbHandler = new DatabaseHelper(this);
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            cursor = dbHandler.listaritems3();
        }

        if (NavigationActivity.global ==4) {
            DatabaseHelper dbHandler;
            dbHandler = new DatabaseHelper(this);
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            cursor = dbHandler.listaritems4();
        }

        if (NavigationActivity.global ==5) {
            DatabaseHelper dbHandler;
            dbHandler = new DatabaseHelper(this);
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            cursor = dbHandler.listaritems5();
        }

        if (NavigationActivity.global ==6) {
            DatabaseHelper dbHandler;
            dbHandler = new DatabaseHelper(this);
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            cursor = dbHandler.listaritems6();
        }

        if (NavigationActivity.global ==7) {
            DatabaseHelper dbHandler;
            dbHandler = new DatabaseHelper(this);
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            cursor = dbHandler.listaritems7();
        }

        if (NavigationActivity.global ==8) {
            DatabaseHelper dbHandler;
            dbHandler = new DatabaseHelper(this);
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            cursor = dbHandler.listaritems8();
        }

        if (NavigationActivity.global ==9) {
            DatabaseHelper dbHandler;
            dbHandler = new DatabaseHelper(this);
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            cursor = dbHandler.listaritems9();
        }

        ListView lvlitems = (ListView) findViewById(R.id.lvlitems);
        lvlitems.setTextFilterEnabled(true);
        final TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, cursor);
        lvlitems.setAdapter(todoAdapter);

        lvlitems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "posicion " + (i + 1) + personas[i], Toast.LENGTH_SHORT).show();
                /*switch (posicion) {
                    case 0:
                        Intent ii = new Intent(getApplicationContext(), CantidadActivity.class);
                        ii.putExtra ( " jobNo ", ii.jobNo );
                        startActivity(ii);
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "hola soy ramiro", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "no soy ramiro ni pablo", Toast.LENGTH_SHORT).show();
                }*/
                String valor= (String) adapterView.getItemAtPosition(position);
                Intent nuevoActi = new Intent(ListaActivity.this,CantidadActivity.class);
                nuevoActi.putExtra("data",valor);
                startActivity(nuevoActi);
            }
        });
    }



}

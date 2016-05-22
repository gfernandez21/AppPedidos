package net.gabrielf.apppedidos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListaPedidosActivity extends AppCompatActivity {
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        DatabaseHelper dbHandler;
        dbHandler = new DatabaseHelper(this);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        cursor = dbHandler.listarpedidoscabe();

        ListView lvlPedido = (ListView) findViewById(R.id.lvlitemsPedido);
        lvlPedido.setTextFilterEnabled(true);
        final TodoCursorAdapterPedido todoAdapter = new TodoCursorAdapterPedido(this, cursor);
        lvlPedido.setAdapter(todoAdapter);

        lvlPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if (position >= 0) {
                    Cursor colCur = (Cursor) adapterView.getItemAtPosition(position);
                    String posicion=String.valueOf(position);
                    //String col = colCur.getString(colCur.getColumnIndex("nombreprod"));
                    Intent i = new Intent(ListaPedidosActivity.this, PedidoDetaActivity.class);
                    i.putExtra("position",posicion);
                    startActivity(i);

                }

            }
        });
    }
}

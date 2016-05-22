package net.gabrielf.apppedidos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class PedidoDetaActivity extends AppCompatActivity {
    Cursor cursor;
    private TextView position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_deta);

        position=(TextView)findViewById(R.id.tv_position);
        //position1 = getIntent().getStringExtra("position");
        //int position2=Integer.parseInt(position1);
        //int position2=0;
        Bundle parametros=getIntent().getExtras();
        if (parametros != null) {
            position.setText(parametros.getString("position"));

        }
        String position2 = position.getText().toString();
        int p = Integer.parseInt(position2);

        DatabaseHelper dbHandler;
        dbHandler = new DatabaseHelper(this);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        cursor = dbHandler.listarpedidosDeta(p);

        ListView lvlPedidodeta = (ListView) findViewById(R.id.lvlitemsPedidoDeta);
        lvlPedidodeta.setTextFilterEnabled(true);
        final TodoCursorAdapterPedidoDeta todoAdapter = new TodoCursorAdapterPedidoDeta(this, cursor);
        lvlPedidodeta.setAdapter(todoAdapter);

    }
}

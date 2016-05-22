package net.gabrielf.apppedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PrincipalPedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_pedido);
    }

    public void onNewPedClick(View v){
        if (v.getId() == R.id.btn_newPed) {
            Intent i = new Intent(PrincipalPedidoActivity.this, PedidofinalActivity.class);
            startActivity(i);

        }

    }

    public void onListaPedClick(View v){
        if (v.getId() == R.id.btn_ListaPed) {
            Intent i = new Intent(PrincipalPedidoActivity.this, ListaPedidosActivity.class);
            startActivity(i);

        }

    }
}

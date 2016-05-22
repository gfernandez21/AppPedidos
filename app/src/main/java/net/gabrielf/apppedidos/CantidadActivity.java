package net.gabrielf.apppedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CantidadActivity extends AppCompatActivity {
        private TextView lblmensaje;
        private TextView lbl_mensaje1;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantidad);
        lblmensaje=(TextView)findViewById(R.id.lbl_mensaje);
        lbl_mensaje1=(TextView)findViewById(R.id.lbl_mensaje1);
        Bundle parametros=getIntent().getExtras();
        if (parametros != null){
            lblmensaje.setText(parametros.getString("data"));

            lbl_mensaje1.setText(parametros.getString("data1"));
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String edit1 = lblmensaje.getText().toString();
        String edit2 = lbl_mensaje1.getText().toString();

        outState.putString("STRING1", edit1);
        outState.putString("STRING2", edit2);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String edit1 = savedInstanceState.getString("STRING1");
        String edit2 = savedInstanceState.getString("STRING2");

        lblmensaje.setText(edit1);
        lbl_mensaje1.setText(edit2);
    }

    public void onPedClick(View v){
        //if (v.getId() == R.id.btn_agregar) {
            //Intent i = new Intent(CantidadActivity.this, PedidofinalActivity.class);
            //i.putExtra("ped",lblmensaje.getText().toString());
            //startActivity(i);

        //}

    }
}

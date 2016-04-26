package net.gabrielf.apppedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CantidadActivity extends AppCompatActivity {
    private TextView lblmensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantidad);
        lblmensaje=(TextView)findViewById(R.id.lbl_mensaje);
        Bundle parametros=getIntent().getExtras();
        if (parametros != null){
            lblmensaje.setText(parametros.getString("data"));
        }
    }
}

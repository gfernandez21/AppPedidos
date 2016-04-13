package net.gabrielf.apppedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Pre_modificarActivity extends AppCompatActivity {
    EditText modificar_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_modificar);
        modificar_input = (EditText) findViewById(R.id.modificar_input);
    }

    public void modificar_clicked(View view){

        Intent i = new Intent(this, ModificarActivity.class);
        modificar_input = (EditText) findViewById(R.id.modificar_input);
        i.putExtra("id_item", modificar_input.getText().toString());
        startActivity(i);
    }
}

package net.gabrielf.apppedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MiPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
    }

    public void onMiperfilClick(View v){
        if (v.getId() == R.id.btn_actuMiper) {

            EditText name = (EditText)findViewById(R.id.tv_nombre2);
            EditText email = (EditText)findViewById(R.id.tv_correo2);
            EditText pass1 = (EditText)findViewById(R.id.pass2);
            EditText pass2 = (EditText)findViewById(R.id.pass2_2);

            String namstr = name.getText().toString();
            String emailstr = email.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();


            if (namstr.equals("") || emailstr.equals("") || pass1str.equals("") || pass2str.equals("")){

                //popup msg
                Toast pass = Toast.makeText(MiPerfilActivity.this , "Favor completar todos los campos", Toast.LENGTH_SHORT);
                pass.show();


            }

        }

    }
}

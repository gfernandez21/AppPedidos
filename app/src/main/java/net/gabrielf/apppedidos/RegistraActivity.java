package net.gabrielf.apppedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistraActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra);
    }

    public void onSignUpClick(View v){

        if(v.getId() == R.id.btn_registrar){

            EditText name = (EditText)findViewById(R.id.tv_nombre);
            EditText email = (EditText)findViewById(R.id.tv_correo);
            EditText user = (EditText)findViewById(R.id.tv_usuario);
            EditText pass1 = (EditText)findViewById(R.id.tv_pass1);
            EditText pass2 = (EditText)findViewById(R.id.tv_pass2);

            String namstr = name.getText().toString();
            String emailstr = email.getText().toString();
            String userstr = user.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if (!pass1str.equals(pass2str)){

                //popup msg
                Toast pass = Toast.makeText(RegistraActivity.this , "Las passwords no coinciden!", Toast.LENGTH_SHORT);
                pass.show();

            }
            else{

                //insertar el detalle en la base de datos
                Contact c = new Contact();
                c.setName(namstr);
                c.setEmail(emailstr);
                c.setUname(userstr);
                c.setPass(pass1str);

                helper.insertContact(c);
            }


        }


    }
}

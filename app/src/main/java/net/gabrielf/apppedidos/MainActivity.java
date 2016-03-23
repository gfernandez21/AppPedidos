package net.gabrielf.apppedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v){

        if (v.getId() == R.id.btn_ingresar){

            EditText a = (EditText)findViewById(R.id.tv_user);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.tv_pass);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);

            if (pass.equals("") || str.equals("")){

                Toast temp = Toast.makeText(MainActivity.this , "Favor ingresar y/o usuario y password!", Toast.LENGTH_SHORT);
                temp.show();

            }
            else if (pass.equals(password)){

                Intent i = new Intent(MainActivity.this, NavigationActivity.class);
                i.putExtra("Username",str);
                startActivity(i);

            }

            else{
                //popup msg
                Toast temp = Toast.makeText(MainActivity.this , "Usuario y/o Password incorrectos!", Toast.LENGTH_SHORT);
                temp.show();

            }




        }
        if (v.getId() == R.id.btn_singin){

            Intent i = new Intent(MainActivity.this, RegistraActivity.class);

            startActivity(i);


        }
    }
}

package net.gabrielf.apppedidos;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener  {

    @NotEmpty(message = "Escriba su usuario" )
    EditText nombreEditText;
    @NotEmpty(message = "Escriba su password" )
    EditText passwordEditText;
    Validator validator;


    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Datos ingresados correctamente", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onValidationFailed(List<ValidationError> errors)
    {
        for (ValidationError error : errors)
        {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            }
            else
            {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }


    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_MainActivity);
        nombreEditText = (EditText)findViewById(R.id.tv_user);
        passwordEditText = (EditText)findViewById(R.id.tv_pass);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void onButtonClick(View v){

        if (v.getId() == R.id.btn_ingresar){

            EditText a = (EditText)findViewById(R.id.tv_user);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.tv_pass);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);
            String usuario = helper.searchUser(str);

            if (pass.equals("") || str.equals("")){

                //Toast temp = Toast.makeText(MainActivity.this , "Favor ingresar usuario y/o password!", Toast.LENGTH_SHORT);
                //temp.show();
                validator.validate();

            }
            else if (pass.equals(password) && str.equals(usuario)){

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

    public void onForgotPassClick(View v){
        if (v.getId() == R.id.tv_forgotpass) {
            Intent i = new Intent(this, RecuperarPass.class);
            startActivity(i);

        }

    }
}

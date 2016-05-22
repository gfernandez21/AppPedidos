package net.gabrielf.apppedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class RegistraActivity extends AppCompatActivity implements Validator.ValidationListener {
    @Email(message = "Email incorrecto")
    EditText emailEditTextRegis;
    @NotEmpty(message = "Escriba su nombre" )
    EditText tv_nombre;

    @NotEmpty(message = "Escriba su usuario" )
    EditText userEditTextRegis;
    //@NotEmpty(message = "Escriba su password" )
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS, message = "La contraseña debe ser alfanumérica, contener simbolos, mayusculas y minusculas")
    EditText passwordEditText;
    @NotEmpty(message = "Escriba Repetir password" )
    @ConfirmPassword(message = "Passwords no coinciden")
    EditText confirmarPasswordEditText;

    Validator validator;

    @Override
    public void onValidationSucceeded() {
        regis();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra);

        tv_nombre = (EditText)findViewById(R.id.tv_nombre2);
        emailEditTextRegis = (EditText)findViewById(R.id.tv_correo2);
        userEditTextRegis = (EditText)findViewById(R.id.tv_usuario);
        passwordEditText = (EditText)findViewById(R.id.tv_pass1);
        confirmarPasswordEditText = (EditText)findViewById(R.id.pass2_2);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }



    public void lim(){

        EditText name1 = (EditText)findViewById(R.id.tv_nombre2);
        EditText email1 = (EditText)findViewById(R.id.tv_correo2);
        EditText user1 = (EditText)findViewById(R.id.tv_usuario);
        EditText pass11 = (EditText)findViewById(R.id.tv_pass1);
        EditText pass21 = (EditText)findViewById(R.id.pass2_2);

        name1.setText("");
        email1.setText("");
        user1.setText("");
        pass11.setText("");
        pass21.setText("");

    }



    public void onSignUpClick(View v){


        if(v.getId() == R.id.btn_registrar){

            EditText name = (EditText)findViewById(R.id.tv_nombre2);
            EditText email = (EditText)findViewById(R.id.tv_correo2);
            EditText user = (EditText)findViewById(R.id.tv_usuario);
            EditText pass1 = (EditText)findViewById(R.id.tv_pass1);
            EditText pass2 = (EditText)findViewById(R.id.pass2_2);

            String namstr = name.getText().toString();
            String emailstr = email.getText().toString();
            String userstr = user.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            String usuario = helper.searchUser(userstr);
            String mail = helper.searchEmail(emailstr);

            if (namstr.equals("") || emailstr.equals("") || userstr.equals("") ||pass1str.equals("") || pass2str.equals("")){

                //popup msg
                //Toast pass = Toast.makeText(RegistraActivity.this , "Favor completar todos los campos", Toast.LENGTH_SHORT);
                //pass.show();
                validator.validate();

            }

            else if (!pass1str.equals(pass2str)){

                //popup msg
                //Toast pass = Toast.makeText(RegistraActivity.this , "Las passwords no coinciden!", Toast.LENGTH_SHORT);
                //pass.show();
                validator.validate();

            }

            else if (userstr.equals(usuario)){

                //popup msg
                Toast usu = Toast.makeText(RegistraActivity.this , "El nombre de usuario ya existe", Toast.LENGTH_SHORT);
                usu.show();
                //validator.validate();

            }

            else if (emailstr.equals(mail)){

                //popup msg
                Toast usu = Toast.makeText(RegistraActivity.this , "El email ya se encuentra registrado", Toast.LENGTH_SHORT);
                usu.show();
                //validator.validate();

            }

            else if (!validator.equals(null) && !validator.equals("")){

                validator.validate();
            }
        }
    }

    public void regis(){

        EditText name = (EditText)findViewById(R.id.tv_nombre2);
        EditText email = (EditText)findViewById(R.id.tv_correo2);
        EditText user = (EditText)findViewById(R.id.tv_usuario);
        EditText pass1 = (EditText)findViewById(R.id.tv_pass1);
        EditText pass2 = (EditText)findViewById(R.id.pass2_2);

        String namstr = name.getText().toString();
        String emailstr = email.getText().toString();
        String userstr = user.getText().toString();
        String pass1str = pass1.getText().toString();
        String pass2str = pass2.getText().toString();

        Contact c = new Contact();
        c.setName(namstr);
        c.setEmail(emailstr);
        c.setUname(userstr);
        c.setPass(pass1str);

        helper.insertContact(c);
        lim();
        Intent i = new Intent(RegistraActivity.this, MainActivity.class);
        startActivity(i);

    }
}

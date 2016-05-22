package net.gabrielf.apppedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class MisDireccionesActivity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty(message = "Escriba su Dirección" )
    EditText RegistraDireccion;
    @NotEmpty(message = "Escriba su Comuna" )
    EditText RegistraComuna;
    @NotEmpty(message = "Escriba su Ciudad" )
    EditText RegistraCiudad;
    @NotEmpty(message = "Escriba su Celular" )
    EditText RegistraCelular;

    Validator validator;

    @Override
    public void onValidationSucceeded() {
        RegDireccion();
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
        setContentView(R.layout.activity_mis_direcciones);
        RegistraDireccion = (EditText)findViewById(R.id.tv_direccion);
        RegistraComuna = (EditText)findViewById(R.id.tv_comuna);
        RegistraCiudad = (EditText)findViewById(R.id.tv_ciudad);
        RegistraCelular =(EditText)findViewById(R.id.tv_celular);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void lim(){

        EditText Direccion = (EditText)findViewById(R.id.tv_direccion);
        EditText Comuna = (EditText)findViewById(R.id.tv_comuna);
        EditText Ciudad = (EditText)findViewById(R.id.tv_ciudad);
        EditText Celular = (EditText)findViewById(R.id.tv_celular);

        Direccion.setText("");
        Comuna.setText("");
        Ciudad.setText("");
        Celular.setText("");
    }

    public void onClick(View v){
        if(v.getId() == R.id.btn_AceptarDireccion){

            EditText Direccion = (EditText)findViewById(R.id.tv_direccion);
            EditText Comuna = (EditText)findViewById(R.id.tv_comuna);
            EditText Ciudad = (EditText)findViewById(R.id.tv_ciudad);
            EditText Celular = (EditText)findViewById(R.id.tv_celular);

            String VarDireccion = Direccion.getText().toString();
            String VarComuna = Comuna.getText().toString();
            String VarCiudad = Ciudad.getText().toString();
            String VarCelular = Celular.getText().toString();

            if (VarDireccion.equals("") || VarComuna.equals("") || VarCiudad.equals("") || VarCelular.equals("")){

                validator.validate();

            }

            else if (!validator.equals(null) && !validator.equals("")){

                validator.validate();
            }
        }
    }

    public void RegDireccion(){

        EditText Direccion = (EditText)findViewById(R.id.tv_direccion);
        EditText Comuna = (EditText)findViewById(R.id.tv_comuna);
        EditText Ciudad = (EditText)findViewById(R.id.tv_ciudad);
        EditText Celular = (EditText)findViewById(R.id.tv_celular);

        String VarDireccion = Direccion.getText().toString();
        String VarComuna = Comuna.getText().toString();
        String VarCiudad = Ciudad.getText().toString();
        String VarCelular = Celular.getText().toString();



        Item d = new Item ();
        //d.setNombreprod(VarDireccion);
        //d.setDetalleprod(VarComuna);
        //d.setDetalleprod(VarCiudad);
        //d.setPrecioprod(Integer.parseInt(VarCelular));


        //helper.insertProd(d);
        //lim();
        //Intent i = new Intent(RegistraProdActivity.this, RegistraProdActivity.class);
        //startActivity(i);

    }
}

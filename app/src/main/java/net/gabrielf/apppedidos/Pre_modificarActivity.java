package net.gabrielf.apppedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class Pre_modificarActivity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty(message = "Debe ingresar id a modificar" )
    EditText modificar_input;
    Validator validator;

    @Override
    public void onValidationSucceeded() {
        //Toast.makeText(this, "Dato ingresado correctamente", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_pre_modificar);
        modificar_input = (EditText) findViewById(R.id.modificar_input);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void modificar_clicked(View view){

        EditText a = (EditText)findViewById(R.id.modificar_input);
        String update = a.getText().toString();

        String item = helper.searchItem(update);

        if (update.equals("") || update.equals(null)){

            validator.validate();

        }
        else if (!update.matches(item)){

            Toast.makeText(this, "El id ingresado no existe", Toast.LENGTH_SHORT).show();


        }
        else{
            Intent i = new Intent(this, ModificarActivity.class);
            modificar_input = (EditText) findViewById(R.id.modificar_input);
            i.putExtra("id_item", modificar_input.getText().toString());
            startActivity(i);
            limpiarcampos();
        }

    }

    public void limpiarcampos(){

        modificar_input.setText("");

    }
}

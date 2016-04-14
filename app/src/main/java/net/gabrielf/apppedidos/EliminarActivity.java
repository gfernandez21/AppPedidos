package net.gabrielf.apppedidos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class EliminarActivity extends AppCompatActivity implements Validator.ValidationListener {

    DatabaseHelper dbHandler;
    @NotEmpty(message = "Debe ingresar id a eliminar" )
    EditText elimina_input;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        elimina_input = (EditText) findViewById(R.id.modificar_input);
        dbHandler = new DatabaseHelper(this);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void eliminar_clicked(View view){

        EditText a = (EditText)findViewById(R.id.modificar_input);
        String delete = a.getText().toString();

        if (delete.equals("") || delete.equals(null)){

            validator.validate();
        }
        else{
            dbHandler.borrarItems(Integer.parseInt(elimina_input.getText().toString()));
            confirmacion();
            limpiarcampos();
        }

    }

    public void confirmacion(){

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(elimina_input.getText());
        dlgAlert.setTitle("Se ha eliminado satisfactoriamente!!!");
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    public void limpiarcampos(){

        elimina_input.setText("");

    }
}

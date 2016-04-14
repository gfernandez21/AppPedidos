package net.gabrielf.apppedidos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class ModificarActivity extends AppCompatActivity implements Validator.ValidationListener {

    DatabaseHelper dbHandler;
    @NotEmpty(message = "Debe ingresar nombre" )
    EditText nombreprod_input;
    @NotEmpty(message = "Debe ingresar detalle" )
    EditText detalleprod_input;
    @NotEmpty(message = "Debe ingresar precio" )
    EditText precio_input;
    int idglobal;
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
        setContentView(R.layout.activity_modificar);

        nombreprod_input = (EditText) findViewById(R.id.tv_nomProd);
        detalleprod_input = (EditText) findViewById(R.id.tv_detalleProd);
        precio_input = (EditText) findViewById(R.id.tv_precioProd);
        dbHandler = new DatabaseHelper(this);
        Item item = new Item();
        Intent i = getIntent(); // gets the previously created intent
        String stringid = i.getStringExtra("id_item");
        int id = Integer.parseInt(stringid);
        Cursor c = dbHandler.itemByid(id);

        //Vuelve a rellenar los inputs con los valores del cursor
        nombreprod_input.setText(c.getString(c.getColumnIndexOrThrow("nombreprod")));
        detalleprod_input.setText(c.getString(c.getColumnIndexOrThrow("detalleprod")));
        precio_input.setText(c.getString(c.getColumnIndexOrThrow("precioprod")));
        idglobal = c.getInt(c.getColumnIndexOrThrow("_id"));

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void modificar_clicked(View view){

        EditText a = (EditText)findViewById(R.id.tv_nomProd);
        String name = a.getText().toString();

        EditText b = (EditText)findViewById(R.id.tv_detalleProd);
        String deta = b.getText().toString();

        EditText c = (EditText)findViewById(R.id.tv_precioProd);
        String precio = c.getText().toString();

        if (name.equals("") || deta.equals("") || precio.equals("")){

            validator.validate();
        }
        else{
            Item item = new Item(nombreprod_input.getText().toString(), detalleprod_input.getText().toString(), Integer.parseInt(precio_input.getText().toString()));
            item.set_id(idglobal);
            dbHandler.updateItem(item);
            confirmacion();
            limpiarcampos();
            finish(); //Termina la actividad y vuelve al menu principal

        }



    }

    public void confirmacion(){

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Se ha modificado exitosamente!");
        dlgAlert.setTitle("Agregar item");
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    //Limpia los valores entrados para efectos de estetica
    public void limpiarcampos(){

        nombreprod_input.setText("");
        detalleprod_input.setText("");
        precio_input.setText("");

    }
}

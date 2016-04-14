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

public class RegistraProdActivity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty(message = "Escriba el nombre" )
    EditText nombreEditTextRegisProd;
    @NotEmpty(message = "Escriba el detalle" )
    EditText nombreDetaEditTextRegisProd;
    @NotEmpty(message = "Escriba el precio" )
    EditText precioEditTextRegisProd;

    Validator validator;

    @Override
    public void onValidationSucceeded() {
        regisProd();
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
        setContentView(R.layout.activity_registra_prod);

        nombreEditTextRegisProd = (EditText)findViewById(R.id.tv_nomProd);
        nombreDetaEditTextRegisProd = (EditText)findViewById(R.id.tv_detalleProd);
        precioEditTextRegisProd = (EditText)findViewById(R.id.tv_precioProd);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void lim(){

        EditText nameprod1 = (EditText)findViewById(R.id.tv_nomProd);
        EditText nameproddeta1 = (EditText)findViewById(R.id.tv_detalleProd);
        EditText priceprod1 = (EditText)findViewById(R.id.tv_precioProd);


        nameprod1.setText("");
        nameproddeta1.setText("");
        priceprod1.setText("");

    }

    public void onClick(View v){


        if(v.getId() == R.id.btn_prod){

            EditText nameprod = (EditText)findViewById(R.id.tv_nomProd);
            EditText nameproddeta = (EditText)findViewById(R.id.tv_detalleProd);
            EditText priceprod = (EditText)findViewById(R.id.tv_precioProd);

            String namprodstr = nameprod.getText().toString();
            String nameproddetastr = nameproddeta.getText().toString();
            String pricestr = priceprod.getText().toString();


            if (namprodstr.equals("") || nameproddetastr.equals("") || pricestr.equals("")){

                validator.validate();

            }

            else if (!validator.equals(null) && !validator.equals("")){

                validator.validate();
            }
        }
    }

    public void regisProd(){

        EditText nameprod = (EditText)findViewById(R.id.tv_nomProd);
        EditText nameproddeta = (EditText)findViewById(R.id.tv_detalleProd);
        EditText priceprod = (EditText)findViewById(R.id.tv_precioProd);

        String namprodstr = nameprod.getText().toString();
        String nameproddetastr = nameproddeta.getText().toString();
        String pricestr = priceprod.getText().toString();



        Item d = new Item ();
        d.setNombreprod(namprodstr);
        d.setDetalleprod(nameproddetastr);
        d.setPrecioprod(Integer.parseInt(pricestr));


        helper.insertProd(d);
        lim();
        //Intent i = new Intent(RegistraProdActivity.this, RegistraProdActivity.class);
        //startActivity(i);

    }

    public void onEliminar(View view){
        Intent i = new Intent(this, EliminarActivity.class);
        startActivity(i);
    }

    public void premodificar(View view){
        Intent i = new Intent(this, Pre_modificarActivity.class);
        startActivity(i);
    }
}

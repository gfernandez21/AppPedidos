package net.gabrielf.apppedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLDataException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PedidofinalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //DatabaseHelper helper = new DatabaseHelper(this);
    //private Spinner spinner;
    //private List<String> lista;
    Spinner spNombre, spPrecio;
    TextView tvfecha;
    ArrayList<String> itemslist,preciolist;
    DatabaseHelper db;
    String recuIdEmpleado;
    String recuprod;
    String maxID;

    DatabaseHelper helper = new DatabaseHelper(this);
    private Spinner editTxt;
    private Spinner editTxt2;
    private EditText editTxt3;
    private TextView editTxt4;
    private TextView editTxt5;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private ImageButton btn;
    public int totalF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidofinal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setfecha();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        try {
        //Muestra nombre de usuario en la Activity
        String username1 = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.tv_userOrder);
        tv.setText(username1);

        recuIdEmpleado= helper.recuIdEmpleado(username1);

        TextView text1 = (TextView) findViewById(R.id.tv_empleado);
        text1.setText(recuIdEmpleado);
        }catch (RuntimeException e){
            System.out.print("error " + e);

        }
        try {
            maxID= helper.maxIDPedCabe(null);
            TextView text2 = (TextView) findViewById(R.id.tv_idpedido);
            text2.setText(maxID);
            }catch (RuntimeException e){
                System.out.print("error " + e);
            }

        spNombre=(Spinner)findViewById(R.id.spinner);
        spPrecio=(Spinner)findViewById(R.id.spinner2);
        tvfecha=(TextView)findViewById(R.id.tvfecha);
        db=new DatabaseHelper(this);
        itemslist= db.getItems();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,itemslist);
        itemsAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spNombre.setAdapter(itemsAdapter);

        spNombre.setOnItemSelectedListener(this);
        spPrecio.setOnItemSelectedListener(this);


        /*txtEditCant.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_0:
                        //handle code for pressing 0
                        subtTotal();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });*/

        /*txtEditCant.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                txtEditCant.setText(new String(s.toString()));

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                txtEditCant.setText(new String(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

                txtEditCant.setText(new String(s.toString()));
            }

        });/*

        /*spinner = (Spinner) findViewById(R.id.spinner);
        lista = new ArrayList<String>();
        lista=helper.listaSpinner();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/



        /*txtEditCant.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_NUM) {
                    subtTotal();
                }
                return true;
            }

        });*/
        EditText txtEditCant = (EditText) findViewById(R.id.et_cantidad);
        txtEditCant.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if (!hasFocus) {
                        subtTotal();
                        //total();
                    }
                }catch (RuntimeException e){
                    System.out.print("error" + e);
                }

            }
        });
        editTxt = (Spinner) findViewById(R.id.spinner);
        editTxt2 = (Spinner) findViewById(R.id.spinner2);
        editTxt3 = (EditText) findViewById(R.id.et_cantidad);
        editTxt4 = (TextView) findViewById(R.id.tvSubtotal);
        editTxt5 = (TextView) findViewById(R.id.tv_idproducto);
        list = (ListView) findViewById(R.id.listViewDetaPed);
        btn = (ImageButton) findViewById(R.id.ib_addlist);

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_layout, arrayList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Producto " + list.getItemAtPosition(position), Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    arrayList.add(editTxt5.getText().toString() + " " + editTxt.getSelectedItem().toString() + " " + editTxt2.getSelectedItem().toString() + " " + editTxt3.getText().toString() + " " + editTxt4.getText().toString());
                    adapter.notifyDataSetChanged();
                    //editTxt.setSelected(" ");
                    subtTotal();
                    total();
                    regisOrderDeta();


                }catch (RuntimeException e){
                    System.out.print("error " + e);
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()){
            case R.id.spinner:
                preciolist=db.getPrecio(spNombre.getSelectedItem().toString());
                ArrayAdapter<String> precioAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,preciolist);
                precioAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spPrecio.setAdapter(precioAdapter);
                lim();
                recuIdProd();
                break;

            case R.id.spinner2:

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void recuIdProd(){

        Spinner nombredeta = (Spinner) findViewById(R.id.spinner);
        String q= nombredeta.getSelectedItem().toString();

        recuprod= helper.recuIdProdSelec(q);

        TextView text2 = (TextView) findViewById(R.id.tv_idproducto);
        text2.setText(recuprod);
    }

    public void setfecha(){

        TextView tv = (TextView) findViewById(R.id.tvfecha);

        Date fechaActual = new Date();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        //DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        //formatoFecha.format(fechaActual);
        String hoyFormat = formatoFecha.format(fechaActual) ;//formato hoy
        //formatoHora.format(fechaActual);

        tv.setText(hoyFormat.toString());

    }

    public void subtTotal() {

        Spinner price = (Spinner) findViewById(R.id.spinner2);
        String b= price.getSelectedItem().toString();
        int brprice = Integer.parseInt(b);
        EditText cantidad = (EditText) findViewById(R.id.et_cantidad);
        String a= cantidad.getText().toString();
        int acant = Integer.parseInt(a);
        int resultado = brprice * acant;
        TextView subtotal = (TextView) findViewById(R.id.tvSubtotal);
        subtotal.setText(String.valueOf(resultado));
    }

    public void total() {
        Spinner price = (Spinner) findViewById(R.id.spinner2);
        String b= price.getSelectedItem().toString();
        int brprice = Integer.parseInt(b);
        EditText cantidad = (EditText) findViewById(R.id.et_cantidad);
        String a= cantidad.getText().toString();
        int acant = Integer.parseInt(a);
        int resultado = brprice * acant;
        totalF=resultado+totalF;
        TextView total = (TextView) findViewById(R.id.tv_total);
        total.setText(String.valueOf(totalF));

    }

    public void lim(){
        EditText cantidad = (EditText) findViewById(R.id.et_cantidad);
        cantidad.setText("");


        TextView subtotal = (TextView) findViewById(R.id.tvSubtotal);
        subtotal.setText("");

    }

    public void regisOrder(){

        TextView tvfecha = (TextView)findViewById(R.id.tvfecha);
        TextView tv_empleado = (TextView)findViewById(R.id.tv_empleado);
        TextView tv_Total = (TextView)findViewById(R.id.tv_total);

        String tvfechastr = tvfecha.getText().toString();
        String tv_empleadostr = tv_empleado.getText().toString();
        int tvem = Integer.parseInt(tv_empleadostr);
        String tv_Totalstr = tv_Total.getText().toString();
        int ttotal = Integer.parseInt(tv_Totalstr);

        Pedidos c = new Pedidos();

        c.setId_empleado(tvem);
        c.setFecha(tvfechastr);
        c.setTotal(ttotal);

        helper.insertOrderCabe(c);

       }

    public void regisOrderDeta(){

        TextView tv_id_pedido = (TextView)findViewById(R.id.tv_idpedido);
        TextView tv_id_producto = (TextView)findViewById(R.id.tv_idproducto);
        EditText tv_Cantidad = (EditText)findViewById(R.id.et_cantidad);
        TextView tv_Precioxunidad = (TextView)findViewById(R.id.tvSubtotal);

        String tvid_pedidostr = tv_id_pedido.getText().toString();
        int a = Integer.parseInt(tvid_pedidostr);

        String tv_id_productostr = tv_id_producto.getText().toString();
        int b = Integer.parseInt(tv_id_productostr);

        String tv_Cantidadstr = tv_Cantidad.getText().toString();
        int r = Integer.parseInt(tv_Cantidadstr);

        String tv_Precioxunidadstr = tv_Precioxunidad.getText().toString();
        int d = Integer.parseInt(tv_Precioxunidadstr);

        PedidosDeta c = new PedidosDeta();

        c.set_id_pedido(a);
        c.set_id_producto(b);
        c.setCantidad(r);
        c.setPrecioxunidad(d);

        helper.insertOrderDeta(c);
    }

    public void onOrderClick(View v){
        if (v.getId() == R.id.fab) {
            try {
                regisOrder();
                Toast ped = Toast.makeText(PedidofinalActivity.this , "Pedido realizado", Toast.LENGTH_SHORT);
                ped.show();
                Intent intent = new Intent(this, PedidofinalActivity.class);
                startActivity(intent);
                finish();
                }catch (RuntimeException e){
                System.out.print("error " + e);
            }


        }

    }

}

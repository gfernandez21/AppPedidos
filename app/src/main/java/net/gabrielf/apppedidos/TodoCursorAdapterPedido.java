package net.gabrielf.apppedidos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by pangui-dev-2015 on 07-05-2016.
 */
public class TodoCursorAdapterPedido extends CursorAdapter {
    public TodoCursorAdapterPedido(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_lista_pedidos, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView id_txt_Pedido = (TextView) view.findViewById(R.id.id_txt_Pedido_Cabe_Deta);
        TextView idempleado_txt_Pedido = (TextView) view.findViewById(R.id.idempleado_txt_Pedido);
        TextView fecha_txt_Pedido = (TextView) view.findViewById(R.id.fecha_txt_Pedido);
        TextView total_txt_Pedido = (TextView) view.findViewById(R.id.PxU_txt_Pedido);

        // Extraccion de las propiedades del cursor


        int txtid_txt_Pedido = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String txtidempleado_txt_Pedido = cursor.getString(cursor.getColumnIndexOrThrow("_id_empleado"));
        String txtfecha_txt_Pedido = cursor.getString(cursor.getColumnIndexOrThrow("fechaped"));
        String txttotal_txt_Pedido = cursor.getString(cursor.getColumnIndexOrThrow("total"));

        id_txt_Pedido.setText(String.valueOf(txtid_txt_Pedido));
        idempleado_txt_Pedido.setText(txtidempleado_txt_Pedido);
        fecha_txt_Pedido.setText(txtfecha_txt_Pedido);
        total_txt_Pedido.setText(txttotal_txt_Pedido);

    }
}

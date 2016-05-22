package net.gabrielf.apppedidos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by pangui-dev-2015 on 09-05-2016.
 */
public class TodoCursorAdapterPedidoDeta extends CursorAdapter {
    public TodoCursorAdapterPedidoDeta(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_pedido_deta, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView id_txt_Pedido_Cabe_Deta = (TextView) view.findViewById(R.id.id_txt_Pedido_Cabe_Deta);
        TextView idProducto_txt_Pedido = (TextView) view.findViewById(R.id.idProducto_txt_Pedido);
        TextView cantidad_txt_Pedido = (TextView) view.findViewById(R.id.cantidad_txt_Pedido);
        TextView PxU_txt_Pedido = (TextView) view.findViewById(R.id.PxU_txt_Pedido);

        // Extraccion de las propiedades del cursor


        int txt_id_txt_Pedido_Cabe_Deta = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        int txt_idProducto_txt_Pedido = cursor.getInt(cursor.getColumnIndexOrThrow("_id_producto"));
        int txt_cantidad_txt_Pedido = cursor.getInt(cursor.getColumnIndexOrThrow("cantidad"));
        int txt_PxU_txt_Pedido = cursor.getInt(cursor.getColumnIndexOrThrow("precioxunidad"));

        id_txt_Pedido_Cabe_Deta.setText(String.valueOf(txt_id_txt_Pedido_Cabe_Deta));
        idProducto_txt_Pedido.setText(String.valueOf(txt_idProducto_txt_Pedido));
        cantidad_txt_Pedido.setText(String.valueOf(txt_cantidad_txt_Pedido));
        PxU_txt_Pedido.setText(String.valueOf(txt_PxU_txt_Pedido));

    }
}

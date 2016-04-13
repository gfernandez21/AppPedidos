package net.gabrielf.apppedidos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by pangui-dev-2015 on 12-04-2016.
 */


public class TodoCursorAdapter extends CursorAdapter {
    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_lista, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView idtxt = (TextView) view.findViewById(R.id.id_txt);
        TextView nombreprodtxt = (TextView) view.findViewById(R.id.nombreprod_txt);
        TextView detalleprodtxt = (TextView) view.findViewById(R.id.detalleprod_txt);
        TextView preciotxt = (TextView) view.findViewById(R.id.precioprod_txt);

        // Extraccion de las propiedades del cursor


        int txtid = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String txtnombreprod = cursor.getString(cursor.getColumnIndexOrThrow("nombreprod"));
        String txtdetalleprod = cursor.getString(cursor.getColumnIndexOrThrow("detalleprod"));
        String txtprecio = cursor.getString(cursor.getColumnIndexOrThrow("precioprod"));

        idtxt.setText(String.valueOf(txtid));
        nombreprodtxt.setText(txtnombreprod);
        detalleprodtxt.setText(txtdetalleprod);
        preciotxt.setText(txtprecio);

    }
}




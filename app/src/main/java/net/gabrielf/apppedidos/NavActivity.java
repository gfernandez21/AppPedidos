package net.gabrielf.apppedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_navigation);

        //Muestra nombre de usuario en la Activity navigation
        //String username2 = getIntent().getStringExtra("Username");
        TextView tv2 = (TextView)findViewById(R.id.textView);
        tv2.setText("hola");
    }
}

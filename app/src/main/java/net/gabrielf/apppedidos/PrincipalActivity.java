package net.gabrielf.apppedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        String username = getIntent().getStringExtra("Username");
        EditText tv = (EditText)findViewById(R.id.TV_username);
        tv.setText(username);
    }
}

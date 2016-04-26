package net.gabrielf.apppedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static int global=0;
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Muestra nombre de usuario en la Activity navigation
        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.textViewNombre);
        tv.setText(username);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView text = (TextView) header.findViewById(R.id.tv_test);
        text.setText(username);


        String recuMail = helper.recuEmail(username);

        TextView text1 = (TextView) header.findViewById(R.id.textView);
        text1.setText(recuMail);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent i = new Intent(this, RegistraProdActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onCategoriaPizzaClick(View v){
        if (v.getId() == R.id.imageButton11) {
            global=1;
            Intent i = new Intent(this, ListaActivity.class);
            startActivity(i);

        }

    }

    public void onCategoriaHotDogClick(View v){
         if (v.getId() == R.id.imageButton13) {
             global=2;
            Intent i = new Intent(this, ListaActivity.class);
            startActivity(i);

         }

    }

    public void onCategoriaNuggetClick(View v){
        if (v.getId() == R.id.imageButton17) {
            global=3;
            Intent i = new Intent(this, ListaActivity.class);
            startActivity(i);

        }

    }

    public void onCategoriaPapasFritasClick(View v){
        if (v.getId() == R.id.imageButton14) {
            global=4;
            Intent i = new Intent(this, ListaActivity.class);
            startActivity(i);

        }

    }

    public void onCategoriaChurrascoClick(View v){
        if (v.getId() == R.id.imageButton15) {
            global=5;
            Intent i = new Intent(this, ListaActivity.class);
            startActivity(i);

        }

    }

    public void onCategoriaBebidasClick(View v){
        if (v.getId() == R.id.imageButton18) {
            global=6;
            Intent i = new Intent(this, ListaActivity.class);
            startActivity(i);

        }

    }

    public void onCategoriaTacoClick(View v){
        if (v.getId() == R.id.imageButton12) {
            global=7;
            Intent i = new Intent(this, ListaActivity.class);
            startActivity(i);

        }

    }

    public void onCategoriaCombosClick(View v){
        if (v.getId() == R.id.imageButton16) {
            global=8;
            Intent i = new Intent(this, ListaActivity.class);
            startActivity(i);

        }

    }

    public void onCategoriaCafeClick(View v){
        if (v.getId() == R.id.imageButton10) {
            global=9;
            Intent i = new Intent(this, ListaActivity.class);
            startActivity(i);

        }

    }

    public void onCorreoClick(View v){
        if (v.getId() == R.id.fab) {
            Intent i = new Intent(this, Correo.class);
            startActivity(i);

        }

    }

}

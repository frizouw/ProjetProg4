package com.example.geni.projetprog4;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Drawer sur le cote gauche
    //XML: activite_main2.xml

    //PROPRIÉTÉS
    private NavigationView navigationView;
    private TextView txtUsername, txtEmail;
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*Pour associer le username de l'utilisateur qui s'est connecté
        SOURCE: https://stackoverflow.com/questions/33560219/in-android-how-to-set-navigation-drawer-header-image-and-name-programmatically-i
        Pour accèder au nav_header_main2.xml*/
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        //Aller chercher le textview de la navigation
        txtUsername = (TextView) view.findViewById(R.id.txtUsernameHeader);
        txtEmail = (TextView) view.findViewById(R.id.txtCourrielHeader);
        avatar = (ImageView) view.findViewById(R.id.imgAvatarHeader);

        //Récupérer l'intent
        Intent intent = getIntent();
        //Emmagasiner l'extra du username dans une variable *Peut être optimisé I guess*
        if(intent.getExtras().containsKey("data"))
        {
            String data = (String) intent.getStringExtra("data");
            //Implément le TextView
            txtUsername.setText(data.split(";")[0].split("=")[1]);
            txtEmail.setText(data.split(";")[1].split("=")[1]);
            Bitmap bitmap = BitmapFactory.decodeFile(data.split(";")[2].split("=")[1]);
            avatar.setImageBitmap(bitmap);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Bonjour");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // set le fragment de départ
        navigationView.setCheckedItem(R.id.nav_home);
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
        getMenuInflater().inflate(R.menu.main2, menu);
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

        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_home)
        {
            fragmentManager.beginTransaction().replace(R.id.content_main2, new RecettesCarousel()).commit();
        }
        else if (id == R.id.nav_profile)
        {
            fragmentManager.beginTransaction().replace(R.id.content_main2, new ProfileFragment()).commit();
        }
        else if (id == R.id.nav_mes_recettes)
        {
            fragmentManager.beginTransaction().replace(R.id.content_main2, new Favorite()).commit();
        }
        else if (id == R.id.nav_amies)
        {

        }
        else if (id == R.id.nav_calendrier)
        {
            fragmentManager.beginTransaction().replace(R.id.content_main2, new Calendrier()).commit();
        }
        else if (id == R.id.nav_epicerie)
        {
            fragmentManager.beginTransaction().replace(R.id.content_main2,new ListeEpicerie()).commit();
        }
        else if (id == R.id.nav_decouverte)
        {
            fragmentManager.beginTransaction().replace(R.id.content_main2,new Decouvrir()).commit();

        }
        else if (id == R.id.nav_deconnexion)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
            .setTitle("Déconnexion")
            .setMessage("Êtes-vous sure de vouloir vous déconnectez?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent retour = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(retour);
                }
            })
            .setNegativeButton("Non", null)
            .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

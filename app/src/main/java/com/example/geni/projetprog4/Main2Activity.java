package com.example.geni.projetprog4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

// Anthony Whelan, Genevieve Rollin, Claire Bun
public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Drawer sur le cote gauche
    //XML: activite_main2.xml

    //PROPRIÉTÉS
    private NavigationView navigationView;
    private TextView txtUsername, txtEmail;
    private ImageView avatar;
    private static int REQUESTCODE =0;
    private int id = 0;

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
        if(intent != null && intent.getExtras().containsKey("data"))
        {
            String data = intent.getStringExtra("data");
            String username = data.split(";")[0].split("=")[1];
            String pays = data.split(";")[1].split("=")[1];
            String email = data.split(";")[2].split("=")[1];
            String urlImage = data.split(";")[3].split("=")[1];
            int points = Integer.valueOf(data.split(";")[4].split("=")[1]);
            //Implément le TextView
            txtUsername.setText(username);
            txtEmail.setText(email);

            Utils.CURRENT_USER = new Users(username, pays, email, urlImage, points, avatar);
        }

        //Proprietes pour la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Bonjour");

        //Proprietes pour le drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //proprietes pour la navigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // set le fragment de départ
        navigationView.setCheckedItem(R.id.nav_home);

        //Les fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main2, new RecettesCarousel()).commit();
    }

    // https://stackoverflow.com/questions/44447056/convert-adaptiveicondrawable-to-bitmap-in-android-o-preview
    //Bitmap
    @NonNull
    private Bitmap getBitmapFromDrawable(@NonNull Drawable drawable) {
        final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bmp;
    }

    @Override
    public void onBackPressed() {
        //pour le drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
            ProfileFragment frag = new ProfileFragment();
            Bundle data = new Bundle();
            data.putString("username", txtUsername.getText().toString());
            data.putString("email", txtEmail.getText().toString());
            data.putInt("points", Utils.CURRENT_USER.getPoints());

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            getBitmapFromDrawable(avatar.getDrawable()).compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            data.putByteArray("avatar", byteArray);
            frag.setArguments(data);
            fragmentManager.beginTransaction().replace(R.id.content_main2, frag).commit();
        }
        else if (id == R.id.nav_mes_recettes)
        {
            fragmentManager.beginTransaction().replace(R.id.content_main2, new Favorite()).commit();
        }
        else if (id == R.id.nav_amies)
        {
            fragmentManager.beginTransaction().replace(R.id.content_main2, new ListeAmies()).commit();
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
            .setMessage("Êtes-vous sure de vouloir vous déconnecter?")
            .setIcon(R.drawable.ic_warning_black_24dp)
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

    //Methode pour update l'interface du calendrier
    public void updateUICalendrier(ArrayList<String> data)
    {
        if(!data.isEmpty())
        {
            //montre les recettes selectionnees a la date
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.liste_custom, data);
            ((ListView)findViewById(R.id.listCalendrier)).setAdapter(adapter);
            Notifier();
        }
        else
        {
            ((ListView)findViewById(R.id.listCalendrier)).setAdapter(null);
        }
    }


    //Methode public pour creer la notification
    public void Notifier()
    {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, Utils.CHANNEL_ID)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(getString(R.string.noticationtitle))
            .setContentText(getString(R.string.notificationtexte))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(id, mBuilder.build());
        id++;
    }

    //Methode pour update le ui de la liste d'amis
    public void updateUIAmies()
    {
        ArrayList<String> amis = new ArrayList<>();

        for(Users user : Utils.AMIS)
            amis.add(String.format("%s | %s points", user.getUsername(), user.getPoints()));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.liste_custom, amis);
        ((ListView)findViewById(R.id.listAmies)).setAdapter(adapter);
    }

    //Methode pour update le ui de la liste d'épicerie
    public void updateUIEpicerie(ArrayList<ItemEpicerie> liste)
    {
        if(!liste.isEmpty())
        {
            ListeEpicerieAdapter adapter = new ListeEpicerieAdapter(this, liste);
            ((ListView)findViewById(R.id.listeEpicerie)).setAdapter(adapter);
        }
        else
        {
            ((ListView)findViewById(R.id.listeEpicerie)).setAdapter(null);
        }
    }
}

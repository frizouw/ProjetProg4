package com.example.geni.projetprog4;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

public class PageRecette extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recette);
        tab = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager)findViewById(R.id.viewpager_id);

        //Pour retourner à la page précédente
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new RecetteResumeFragment(), "Resume");
        adapter.AddFragment(new RecetteIngredientFragment(), "Ingredients");
        adapter.AddFragment(new RecetteEtapeFragment(), "Étapes");

        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
    }

    //Pour retourner à la page précédente
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return false;
        }
    }

    //Pour retourner à la page précédente
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
}

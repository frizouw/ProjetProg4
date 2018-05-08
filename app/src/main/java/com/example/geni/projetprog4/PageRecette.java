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
    Recettes recette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recette);
        tab = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager)findViewById(R.id.viewpager_id);

        //Pour retourner à la page précédente
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if(getIntent().getExtras().containsKey("recette"))
            recette = (Recettes) getIntent().getExtras().get("recette");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle b = new Bundle();
        b.putSerializable("recette", recette);

        RecetteResumeFragment resume = new RecetteResumeFragment();
        resume.setArguments(b);
        RecetteIngredientFragment ingredient = new RecetteIngredientFragment();
        ingredient.setArguments(b);
        RecetteEtapeFragment etape = new RecetteEtapeFragment();
        etape.setArguments(b);

        adapter.AddFragment(resume, "Resume");
        adapter.AddFragment(ingredient, "Ingredients");
        adapter.AddFragment(etape, "Étapes");


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

package com.example.geni.projetprog4;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;

public class PageRecette extends AppCompatActivity {

    TabLayout tab;
    AppBarLayout appBarLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recette);
        tab = (TabLayout) findViewById(R.id.tabLayout_id);
        appBarLayout = (AppBarLayout)findViewById(R.id.appbarid);
        viewPager = (ViewPager)findViewById(R.id.viewpager_id);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new RecetteEtapeFragment(), "Resume");
        adapter.AddFragment(new RecetteIngredientFragment(), "Ingredient");
        adapter.AddFragment(new RecetteResumeFragment(), "Etape");

        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
    }
}

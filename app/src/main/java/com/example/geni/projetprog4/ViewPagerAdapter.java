package com.example.geni.projetprog4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

//Adapter pour la page d'une recette et ses onglets
// Anthony Whelan, Genevieve Rollin, Claire Bun
public class ViewPagerAdapter extends FragmentPagerAdapter {

    //classe qui permet d'ajouter plusieurs fragment pour le resume, l'etape et les ingredients dans
    //chacune des recettes selectionnees
    //Proprietes
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentListTitre = new ArrayList<>();

    //constructeur
    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        return fragmentList.get(position);
    }

    @Override
    public int getCount(){
        return fragmentListTitre.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragmentListTitre.get(position);
    }

    public void AddFragment(Fragment fragment, String titre){
        fragmentList.add(fragment);
        fragmentListTitre.add(titre);
    }
}

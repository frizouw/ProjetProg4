package com.example.geni.projetprog4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

//Adapter pour la page d'une recette et ses onglets

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentListTitre = new ArrayList<>();

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

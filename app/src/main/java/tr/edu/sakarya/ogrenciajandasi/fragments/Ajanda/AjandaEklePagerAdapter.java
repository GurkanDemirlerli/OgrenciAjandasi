package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Hatirlaticilar.HatirlaticiEkleFragment;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Odevler.OdevEkleFragment;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Sinavlar.SinavEkleFragment;

import static tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.AjandaListFragment.int_items;

public class AjandaEklePagerAdapter extends FragmentPagerAdapter {

    public AjandaEklePagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HatirlaticiEkleFragment();
            case 1:
                return new SinavEkleFragment();
            case 2:
                return new OdevEkleFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Yeni Hatırlatıcı";
            case 1:
                return "Yeni Sınav";
            case 2:
                return "Yeni Ödev";
        }
        return "Yeni Etkinlik";
    }
}

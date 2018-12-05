package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Hatirlaticilar.HatirlaticiListFragment;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Odevler.OdevListFragment;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Sinavlar.SinavListFragment;

import static tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.AjandaListFragment.int_items;

public class AjandaListPagerAdapter extends FragmentPagerAdapter {

    public AjandaListPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HatirlaticiListFragment();
            case 1:
                return new SinavListFragment();
            case 2:
                return new OdevListFragment();
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
                return "Hatırlatıcılar";
            case 1:
                return "Sınavlar";
            case 2:
                return "Ödevler";
        }
        return "Ajanda";
    }
}

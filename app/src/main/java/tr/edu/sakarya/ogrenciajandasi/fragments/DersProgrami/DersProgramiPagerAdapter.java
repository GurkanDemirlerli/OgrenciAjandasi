package tr.edu.sakarya.ogrenciajandasi.fragments.DersProgrami;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


public class DersProgramiPagerAdapter extends FragmentPagerAdapter {
    public Context context;
    public DersProgramiPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle(2);
        bundle.putInt("gun", position);
        switch (position){
            case 0:
                return Fragment.instantiate(context, DersGunuFragment.class.getName(), bundle);
            case 1:
                return Fragment.instantiate(context, DersGunuFragment.class.getName(), bundle);
            case 2:
                return Fragment.instantiate(context, DersGunuFragment.class.getName(), bundle);
            case 3:
                return Fragment.instantiate(context, DersGunuFragment.class.getName(), bundle);
            case 4:
                return Fragment.instantiate(context, DersGunuFragment.class.getName(), bundle);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "PZT";
            case 1:
                return "SAL";
            case 2:
                return "CAR";
            case 3:
                return "PER";
            case 4:
                return "CUM";
        }
        return "Ders Programı";
    }
}

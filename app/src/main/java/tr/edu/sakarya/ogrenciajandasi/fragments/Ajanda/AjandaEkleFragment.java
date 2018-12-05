package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tr.edu.sakarya.ogrenciajandasi.R;

public class AjandaEkleFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3;
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ajanda_ekle,container,false);
        getActivity().setTitle("Yeni Etkinlik");
        final TabLayout tabLayout = (TabLayout) v.findViewById(R.id.ajanda_ekle_tabs);
        ViewPager Pager = (ViewPager) v.findViewById(R.id.ajanda_ekle_viewpager);
        AjandaEklePagerAdapter tabPagerAdapter = new AjandaEklePagerAdapter(getChildFragmentManager());
        Pager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(Pager);
        return v;
    }
}

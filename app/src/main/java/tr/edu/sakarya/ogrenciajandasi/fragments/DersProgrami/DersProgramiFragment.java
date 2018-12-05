package tr.edu.sakarya.ogrenciajandasi.fragments.DersProgrami;

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
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.AjandaListPagerAdapter;

public class DersProgramiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ders_programi,container,false);
        getActivity().setTitle("Ders Programı");
        final TabLayout tabLayout = (TabLayout) v.findViewById(R.id.ders_programi_tabs);
        ViewPager Pager = (ViewPager) v.findViewById(R.id.ders_programi_viewpager);
        DersProgramiPagerAdapter tabPagerAdapter = new DersProgramiPagerAdapter(getChildFragmentManager());
        tabPagerAdapter.context = getContext();
        Pager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(Pager);
        return v;
    }
}

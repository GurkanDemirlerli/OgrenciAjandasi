package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tr.edu.sakarya.ogrenciajandasi.R;

public class AjandaListFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ajanda_list,container,false);
        getActivity().setTitle("Ajanda");
        final TabLayout tabLayout = (TabLayout) v.findViewById(R.id.ajanda_list_tabs);
        ViewPager Pager = (ViewPager) v.findViewById(R.id.ajanda_list_viewpager);
        AjandaListPagerAdapter tabPagerAdapter = new AjandaListPagerAdapter(getChildFragmentManager());
        Pager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(Pager);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.ajanda_list_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjandaEkleFragment ajandaEkleFragment = new AjandaEkleFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,ajandaEkleFragment,"ajandaEkleFragment");
                transaction.addToBackStack("trsToajandaEkleFragment");
                transaction.commit();
            }
        });

        return v;
    }
}

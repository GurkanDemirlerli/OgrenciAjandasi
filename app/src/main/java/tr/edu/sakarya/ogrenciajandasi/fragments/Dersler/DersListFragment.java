package tr.edu.sakarya.ogrenciajandasi.fragments.Dersler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import tr.edu.sakarya.ogrenciajandasi.R;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.AjandaEkleFragment;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.AjandaListPagerAdapter;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Hatirlaticilar.HatirlaticiAdapter;

public class DersListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dersler_list,container,false);
        getActivity().setTitle("Dersler");

        ListView dersListView = (ListView) v.findViewById(R.id.ders_list_view);
        DersAdapter dersAdapter = new DersAdapter(getContext());
        dersListView.setAdapter(dersAdapter);
        dersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                // Your code for item clicks
            }
        });

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.ders_list_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DersEkleFragment dersEkleFragment = new DersEkleFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,dersEkleFragment,"dersEkleFragment");
                transaction.addToBackStack("trsTodersEkleFragment");
                transaction.commit();
            }
        });
        return v;
    }
}

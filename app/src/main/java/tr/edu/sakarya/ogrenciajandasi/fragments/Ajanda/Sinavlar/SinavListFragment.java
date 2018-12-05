package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Sinavlar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import tr.edu.sakarya.ogrenciajandasi.R;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Odevler.OdevAdapter;

public class SinavListFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sinav_list,container,false);
        ListView sinavlarListView = (ListView) v.findViewById(R.id.sinavlarListView);
        SinavAdapter sinavAdapter = new SinavAdapter(getContext());
        sinavlarListView.setAdapter(sinavAdapter);


        sinavlarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                // Your code for item clicks
            }
        });


        return v;
    }
}

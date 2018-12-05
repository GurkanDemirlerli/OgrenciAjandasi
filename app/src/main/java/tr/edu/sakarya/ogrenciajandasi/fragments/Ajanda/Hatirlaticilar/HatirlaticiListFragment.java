package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Hatirlaticilar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import tr.edu.sakarya.ogrenciajandasi.R;

public class HatirlaticiListFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hatirlatici_list,container,false);
        ListView hatirlaticilarListView = (ListView) v.findViewById(R.id.hatirlaticilarListView);
        HatirlaticiAdapter hatirlaticiAdapter = new HatirlaticiAdapter(getContext());
        hatirlaticilarListView.setAdapter(hatirlaticiAdapter);


        hatirlaticilarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                // Your code for item clicks
            }
        });

        Log.i("Listeleme","hatirlaticilarstart");

        return v;
    }
}

package tr.edu.sakarya.ogrenciajandasi.fragments.Dersler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tr.edu.sakarya.ogrenciajandasi.R;
import tr.edu.sakarya.ogrenciajandasi.database.DatabaseQueryClass;
import tr.edu.sakarya.ogrenciajandasi.models.Ders;

public class DersEkleFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ders_ekle,container,false);
        getActivity().setTitle("Yeni Ders");


        Button ders_ekle_btn = (Button) v.findViewById(R.id.ders_ekle_btn);
        final EditText ders_ekle_adi_edt = (EditText) v.findViewById(R.id.ders_ekle_adi_edt);
        final EditText ders_ekle_sinif_edt = (EditText) v.findViewById(R.id.ders_ekle_sinif_edt);
        final EditText ders_ekle_ogr_edt = (EditText) v.findViewById(R.id.ders_ekle_ogr_edt);

        ders_ekle_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());

                Ders ders = new Ders();
                ders.adi=ders_ekle_adi_edt.getText().toString();
                ders.sinifi = ders_ekle_sinif_edt.getText().toString();
                ders.ogretmen = ders_ekle_ogr_edt.getText().toString();

                long id = databaseQueryClass.insertDers(ders);

                if(id>0){
                    ders.id = id;
                    Toast.makeText(getContext(), "Ders Basariyla Eklendi.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Ekleme işlemi yapılırken bir sorun oluştu.", Toast.LENGTH_SHORT).show();
                }
                getActivity().getSupportFragmentManager().popBackStack(null, getActivity().getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DersListFragment());
            }
        });

        return v;
    }
}

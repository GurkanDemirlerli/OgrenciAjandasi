package tr.edu.sakarya.ogrenciajandasi.fragments.Dersler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import tr.edu.sakarya.ogrenciajandasi.R;
import tr.edu.sakarya.ogrenciajandasi.database.DatabaseQueryClass;
import tr.edu.sakarya.ogrenciajandasi.models.Ders;
import tr.edu.sakarya.ogrenciajandasi.models.DersSaati;

public class DersDuzenleFragment extends Fragment {
    Ders ders;
    List<DersSaati> dersSaatleri;
    String selectedGun = "Pazartesi";
    String selectedSaat = "07:00";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ders_duzenle,container,false);
        getActivity().setTitle("Ders Düzenle");
        DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
        ders = databaseQueryClass.getDersById(Long.valueOf(getArguments().getString("itemId")).longValue());
        dersSaatleri = databaseQueryClass.getDersSaatleriByDersId(Long.valueOf(getArguments().getString("itemId")).longValue());

        Button ders_dznl_kaydet_btn = (Button) v.findViewById(R.id.ders_dznl_kaydet_btn);
        Button ders_dznl_sil_btn = (Button) v.findViewById(R.id.ders_dznl_sil_btn);
        Button ders_dznl_ders_saati_ekle_btn = (Button) v.findViewById(R.id.ders_dznl_ders_saati_ekle_btn);

        ListView ders_saati_listview = (ListView) v.findViewById(R.id.ders_saati_listview);
        DersSaatiAdapter dersSaatiAdapter = new DersSaatiAdapter(getContext(),Long.valueOf(getArguments().getString("itemId")).longValue());
        ders_saati_listview.setAdapter(dersSaatiAdapter);



        Spinner saat_spinner = (Spinner) v.findViewById(R.id.saat_spinner);
        ArrayAdapter<CharSequence> saat_adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.saatler,android.R.layout.simple_spinner_item);
        saat_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        saat_spinner.setAdapter(saat_adapter);
        saat_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text =parent.getItemAtPosition(position).toString();
                selectedSaat = text;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        Spinner gun_spinner = (Spinner) v.findViewById(R.id.gun_spinner);
        ArrayAdapter<CharSequence> gun_adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.gunler,android.R.layout.simple_spinner_item);
        gun_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gun_spinner.setAdapter(gun_adapter);
        gun_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text =parent.getItemAtPosition(position).toString();
                selectedGun = text;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        final EditText ders_dznl_adi_edt = (EditText) v.findViewById(R.id.ders_dznl_adi_edt);
        final EditText ders_dznl_sinif_edt = (EditText) v.findViewById(R.id.ders_dznl_sinif_edt);
        final EditText ders_dznl_ogr_edt = (EditText) v.findViewById(R.id.ders_dznl_ogr_edt);


        ders_dznl_adi_edt.setText(ders.adi);
        ders_dznl_sinif_edt.setText(ders.sinifi);
        ders_dznl_ogr_edt.setText(ders.ogretmen);


        ders_dznl_kaydet_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
                ders.adi = ders_dznl_adi_edt.getText().toString();
                ders.sinifi = ders_dznl_sinif_edt.getText().toString();
                ders.ogretmen = ders_dznl_ogr_edt.getText().toString();

                long rowCount = databaseQueryClass.updateDers(ders);

                if(rowCount>0){
                    Toast.makeText(getContext(), "Ders Basariyla Düzenlendi.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Düzenleme işlemi yapılırken bir sorun oluştu.", Toast.LENGTH_SHORT).show();
                }
                getFragmentManager().popBackStack(null, getFragmentManager().POP_BACK_STACK_INCLUSIVE);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DersListFragment()).commit();
            }
        });

        ders_dznl_sil_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
                long rowCount =databaseQueryClass.deleteDersById((Long.valueOf(getArguments().getString("itemId")).longValue()));
                if (rowCount >0){
                    Toast.makeText(getContext(), "Ders silindi.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Ders silerken bir hata olustu.", Toast.LENGTH_SHORT).show();
                }
                getFragmentManager().popBackStack(null, getFragmentManager().POP_BACK_STACK_INCLUSIVE);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DersListFragment()).commit();
            }
        });

        ders_dznl_ders_saati_ekle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DersSaati dersSaati = new DersSaati();
                switch (selectedGun){
                    case "Pazartesi":
                        dersSaati.gun=0;
                        break;
                    case "Salı":
                        dersSaati.gun=1;
                        break;
                    case "Çarşamba":
                        dersSaati.gun=2;
                        break;
                    case "Perşembe":
                        dersSaati.gun=3;
                        break;
                    case "Cuma":
                        dersSaati.gun=4;
                        break;
                    default:
                        dersSaati.gun=0;
                        break;
                }
                dersSaati.dersId =(Long.valueOf(getArguments().getString("itemId")).longValue());
                dersSaati.saat= selectedSaat;
                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
                dersSaati.id = databaseQueryClass.insertDersSaati(dersSaati);
                if (dersSaati.id >0){
                    Toast.makeText(getContext(), "Yeni ders saati eklendi.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Ders saati eklerken bir hata olustu.", Toast.LENGTH_SHORT).show();
                }
                getFragmentManager().popBackStack(null, getFragmentManager().POP_BACK_STACK_INCLUSIVE);
                DersDuzenleFragment dersDuzenleFragment = new DersDuzenleFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "itemId" ,getArguments().getString("itemId"));
                dersDuzenleFragment.setArguments(arguments);
                FragmentTransaction transaction = ((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,dersDuzenleFragment,"dersDuzenleFragment");
                //transaction.addToBackStack("trsTodersDuzenleFragment");
                transaction.commit();
            }
        });

        return v;
    }
}

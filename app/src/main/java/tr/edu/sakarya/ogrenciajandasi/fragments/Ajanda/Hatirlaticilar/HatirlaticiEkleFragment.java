package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Hatirlaticilar;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

import tr.edu.sakarya.ogrenciajandasi.R;
import tr.edu.sakarya.ogrenciajandasi.database.DatabaseQueryClass;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.AjandaListFragment;
import tr.edu.sakarya.ogrenciajandasi.models.Etkinlik;
import tr.edu.sakarya.ogrenciajandasi.models.Hatirlatici;

public class HatirlaticiEkleFragment extends Fragment {
    private int year,month,day;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hatirlatici_ekle,container,false);


        Button htr_ekle_btn = (Button) v.findViewById(R.id.htr_ekle_btn);
        ImageView htr_ekle_trh_pckr = (ImageView) v.findViewById(R.id.htr_ekle_trh_pckr);
        final EditText htr_ekle_bslk_edt = (EditText) v.findViewById(R.id.htr_ekle_bslk_edt);
        final EditText htr_ekle_trh_edt = (EditText) v.findViewById(R.id.htr_ekle_trh_edt);
        final EditText htr_ekle_dty_edt = (EditText) v.findViewById(R.id.htr_ekle_dty_edt);




        htr_ekle_trh_pckr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        htr_ekle_trh_edt.setText(dayOfMonth + "/" + (month+1) + "/"+year);
                    }
                },day,month,year);
                datePickerDialog.show();
            }
        });

        htr_ekle_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());

                Hatirlatici hatirlatici = new Hatirlatici();
                hatirlatici.etkinlik = new Etkinlik();
                hatirlatici.etkinlik.baslik = htr_ekle_bslk_edt.getText().toString();
                hatirlatici.etkinlik.detay = htr_ekle_dty_edt.getText().toString();
                hatirlatici.etkinlik.etkinlikTipi = "Hatirlatici";
                hatirlatici.etkinlik.tarih = htr_ekle_trh_edt.getText().toString();

                long id = databaseQueryClass.insertHatirlatici(hatirlatici);

                if(id>0){
                    hatirlatici.id=id;
                    Toast.makeText(getContext(), "Hatırlatıcı Basariyla Eklendi.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Ekleme işlemi yapılırken bir sorun oluştu.", Toast.LENGTH_SHORT).show();
                }
                getActivity().getSupportFragmentManager().popBackStack(null, getActivity().getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AjandaListFragment());
                Log.i("CRSPOP","2");
                Log.i("CRSPOP","3");

            }
        });

        return v;
    }
}

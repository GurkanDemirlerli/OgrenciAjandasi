package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Sinavlar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import tr.edu.sakarya.ogrenciajandasi.models.Hatirlatici;
import tr.edu.sakarya.ogrenciajandasi.models.Sinav;


public class SinavDuzenleFragment extends Fragment {
    Sinav sinav;
    private int year,month,day;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sinav_duzenle,container,false);
        DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
        sinav = databaseQueryClass.getSinavById(Long.valueOf(getArguments().getString("itemId")).longValue());

        Button snv_dznl_btn = (Button) v.findViewById(R.id.snv_dznl_btn);
        ImageView snv_dznl_trh_pckr = (ImageView) v.findViewById(R.id.snv_dznl_trh_pckr);
        final EditText snv_dznl_bslk_edt = (EditText) v.findViewById(R.id.snv_dznl_bslk_edt);
        final EditText snv_dznl_trh_edt = (EditText) v.findViewById(R.id.snv_dznl_trh_edt);
        final EditText snv_dznl_dty_edt = (EditText) v.findViewById(R.id.snv_dznl_dty_edt);
        final EditText snv_dznl_ders_edt = (EditText) v.findViewById(R.id.snv_dznl_ders_edt);
        final EditText snv_dznl_yer_edt = (EditText) v.findViewById(R.id.snv_dznl_yer_edt);


        snv_dznl_bslk_edt.setText(sinav.etkinlik.baslik);
        snv_dznl_trh_edt.setText(sinav.etkinlik.tarih);
        snv_dznl_dty_edt.setText(sinav.etkinlik.detay);
        snv_dznl_ders_edt.setText(sinav.dersAdi);
        snv_dznl_yer_edt.setText(sinav.sinavYeri);
        snv_dznl_trh_pckr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        snv_dznl_trh_edt.setText(dayOfMonth + "/" + (month+1) + "/"+year);
                    }
                },day,month,year);
                datePickerDialog.show();
            }
        });

        snv_dznl_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
                sinav.etkinlik.baslik = snv_dznl_bslk_edt.getText().toString();
                sinav.etkinlik.detay = snv_dznl_dty_edt.getText().toString();
                sinav.etkinlik.etkinlikTipi = "Sinav";
                sinav.etkinlik.tarih = snv_dznl_trh_edt.getText().toString();
                sinav.dersAdi=snv_dznl_ders_edt.getText().toString();
                sinav.sinavYeri=snv_dznl_yer_edt.getText().toString();

                long id = databaseQueryClass.updateSinav(sinav);

                if(id>0){
                    sinav.id=id;
                    Toast.makeText(getContext(), "Sınav Basariyla Güncellendi.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Ekleme işlemi yapılırken bir sorun oluştu.", Toast.LENGTH_SHORT).show();
                }
                getFragmentManager().popBackStack(null, getFragmentManager().POP_BACK_STACK_INCLUSIVE);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AjandaListFragment()).commit();
            }
        });

        return v;
    }
}

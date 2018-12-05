package tr.edu.sakarya.ogrenciajandasi.fragments.DersProgrami;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tr.edu.sakarya.ogrenciajandasi.R;
import tr.edu.sakarya.ogrenciajandasi.database.DatabaseQueryClass;
import tr.edu.sakarya.ogrenciajandasi.models.DersSaati;

public class DersGunuFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ders_gunu,container,false);


        TextView dersa7 = v.findViewById(R.id.dersa7);
        TextView dersa8 = v.findViewById(R.id.dersa8);
        TextView dersa9 = v.findViewById(R.id.dersa9);
        TextView dersa10= v.findViewById(R.id.dersa10);
        TextView dersa11= v.findViewById(R.id.dersa11);
        TextView dersa12= v.findViewById(R.id.dersa12);
        TextView dersa13= v.findViewById(R.id.dersa13);
        TextView dersa14= v.findViewById(R.id.dersa14);
        TextView dersa15= v.findViewById(R.id.dersa15);
        TextView dersa16= v.findViewById(R.id.dersa16);
        TextView dersa17= v.findViewById(R.id.dersa17);
        TextView dersa18= v.findViewById(R.id.dersa18);
        TextView dersa19= v.findViewById(R.id.dersa19);
        TextView dersa20= v.findViewById(R.id.dersa20);
        TextView dersa21= v.findViewById(R.id.dersa21);
        TextView dersa22= v.findViewById(R.id.dersa22);

        TextView seciliTV = null;

        DatabaseQueryClass repo = new DatabaseQueryClass(getContext());
        List<DersSaati> dersSaatleri = repo.getDersSaatleriByGunId(getArguments().getInt("gun"));
        for (DersSaati dersSaati : dersSaatleri) {
            switch (dersSaati.saat)
            {
                case "07:00":
                    seciliTV = dersa7;
                    break;
                case "08:00":
                    seciliTV = dersa8;
                    break;
                case "09:00":
                    seciliTV = dersa9;
                    break;
                case "10:00":
                    seciliTV = dersa10;
                    break;
                case "11:00":
                    seciliTV = dersa11;
                    break;
                case "12:00":
                    seciliTV = dersa12;
                    break;
                case "13:00":
                    seciliTV = dersa13;
                    break;
                case "14:00":
                    seciliTV = dersa14;
                    break;
                case "15:00":
                    seciliTV = dersa15;
                    break;
                case "16:00":
                    seciliTV = dersa16;
                    break;
                case "17:00":
                    seciliTV = dersa17;
                    break;
                case "18:00":
                    seciliTV = dersa18;
                    break;
                case "19:00":
                    seciliTV = dersa19;
                    break;
                case "20:00":
                    seciliTV = dersa20;
                    break;
                case "21:00":
                    seciliTV = dersa21;
                    break;
                case "22:00":
                    seciliTV = dersa22;
                    break;
                default:
                    break;
            }
            if (seciliTV != null){
                seciliTV.setText(dersSaati.ders.adi);
                seciliTV.setBackgroundResource(R.color.colorDersSaatiTint);
                seciliTV.setTypeface(Typeface.DEFAULT_BOLD);
            }
        }
        return v;
    }
}

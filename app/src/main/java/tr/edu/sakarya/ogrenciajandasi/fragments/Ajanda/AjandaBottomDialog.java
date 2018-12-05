package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import tr.edu.sakarya.ogrenciajandasi.R;
import tr.edu.sakarya.ogrenciajandasi.database.DatabaseQueryClass;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Hatirlaticilar.HatirlaticiDuzenleFragment;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Odevler.OdevDuzenleFragment;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Sinavlar.SinavDuzenleFragment;

public class AjandaBottomDialog extends BottomSheetDialogFragment {
    public long itemId = 0;
    public String itemOzet = "";
    public int itemTipi = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,container,false);
        TextView bottom_sht_sil = (TextView) v.findViewById(R.id.bottom_sht_sil);
        TextView bottom_sht_duzenle = (TextView) v.findViewById(R.id.bottom_sht_duzenle);
        TextView bottom_sht_ozet = (TextView) v.findViewById(R.id.bottom_sht_ozet);
        bottom_sht_ozet.setText(itemOzet);

        bottom_sht_sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemId == 0){
                    return;
                }
                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
                long rowCount =databaseQueryClass.deleteEtkinlikById(itemId);
                if (rowCount >0){
                    Toast.makeText(getContext(), "Etkinlik silindi.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Etkinlik silerken bir hata olustu.", Toast.LENGTH_SHORT).show();
                }
                dismiss();
                getFragmentManager().popBackStack(null, getFragmentManager().POP_BACK_STACK_INCLUSIVE);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AjandaListFragment()).commit();

            }
        });

        bottom_sht_duzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                switch (itemTipi){
                    case 0:
                        fragment = new HatirlaticiDuzenleFragment();
                        break;
                    case 1:
                        fragment = new OdevDuzenleFragment();
                        break;
                    case 2:
                        fragment = new SinavDuzenleFragment();
                        break;
                    default:
                        fragment = new HatirlaticiDuzenleFragment();
                        break;
                }
                Bundle arguments = new Bundle();
                arguments.putString( "itemId" ,String.valueOf(itemId));
                fragment.setArguments(arguments);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,fragment,"hatirlaticiDuzenleFragment");
                transaction.addToBackStack("trsTohatirlaticiDuzenleFragment");
                transaction.commit();
                dismiss();
            }
        });

        return v;
    }
}

package tr.edu.sakarya.ogrenciajandasi.fragments.Dersler;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import tr.edu.sakarya.ogrenciajandasi.R;
import tr.edu.sakarya.ogrenciajandasi.database.DatabaseQueryClass;
import tr.edu.sakarya.ogrenciajandasi.models.Ders;
import tr.edu.sakarya.ogrenciajandasi.models.DersSaati;


public class DersSaatiAdapter extends BaseAdapter {

    private Context context;
    private DersSaati[] dersSaatleri;
    private int count;
    private long dersId;

    public DersSaatiAdapter(Context context,long dersId  ){
        this.context=context;
        DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(context);
        List<DersSaati> list= databaseQueryClass.getDersSaatleriByDersId(dersId);
        this.count=list.size();
        DersSaati[] dersSaatleri = new DersSaati[list.size()];
        list.toArray(dersSaatleri);
        this.dersSaatleri=dersSaatleri;
        this.dersId=dersId;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View row = inflater.inflate(R.layout.ders_saati_list_row,parent,false);

        ImageButton ders_saati_clear_btn = (ImageButton) row.findViewById(R.id.ders_saati_clear_btn);
        TextView ders_saati_row_gun = (TextView) row.findViewById(R.id.ders_saati_row_gun);
        TextView ders_saati_row_saat = (TextView) row.findViewById(R.id.ders_saati_row_saat);

        row.setTag(dersSaatleri[position].id);
        switch ((int)dersSaatleri[position].gun){
            case 0:

                ders_saati_row_gun.setText("Pazartesi");
                break;
            case 1:
                ders_saati_row_gun.setText("Salı");
                break;
            case 2:
                ders_saati_row_gun.setText("Çarşamba");
                break;
            case 3:
                ders_saati_row_gun.setText("Perşembe");
                break;
            case 4:
                ders_saati_row_gun.setText("Cuma");
                break;
            default:
                ders_saati_row_gun.setText("Pazartesi");
                break;
        }
        ders_saati_row_saat.setText(dersSaatleri[position].saat);


        ders_saati_clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(context);
               long deletedRowCount = databaseQueryClass.deleteDersSaatiById(dersSaatleri[position].id);
                if (deletedRowCount > 0 ){
                    Toast.makeText(context, "Ders saati silindi", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Ders saati silinirken bir hata oluştu.", Toast.LENGTH_SHORT).show();
                }
                ((AppCompatActivity)context).getSupportFragmentManager().popBackStack(null, ((AppCompatActivity)context).getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                DersDuzenleFragment dersDuzenleFragment = new DersDuzenleFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "itemId" ,String.valueOf(dersId));
                dersDuzenleFragment.setArguments(arguments);
                FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,dersDuzenleFragment,"dersDuzenleFragment");
                transaction.addToBackStack("trsTodersDuzenleFragment");
                transaction.commit();
            }
        });

        return row;
    }
}

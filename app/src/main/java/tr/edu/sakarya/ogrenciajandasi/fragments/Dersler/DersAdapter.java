package tr.edu.sakarya.ogrenciajandasi.fragments.Dersler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;


import java.util.List;
import tr.edu.sakarya.ogrenciajandasi.R;
import tr.edu.sakarya.ogrenciajandasi.database.DatabaseQueryClass;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Hatirlaticilar.HatirlaticiDuzenleFragment;
import tr.edu.sakarya.ogrenciajandasi.models.Ders;


public class DersAdapter extends BaseAdapter {

    private Context context;
    private Ders[] dersler;
    private int count;

    public DersAdapter(Context context  ){
        this.context=context;
        DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(context);
        List<Ders> list= databaseQueryClass.getAllDersler();
        this.count=list.size();
        Ders[] dersler = new Ders[list.size()];
        list.toArray(dersler);
        this.dersler=dersler;
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
        final View row = inflater.inflate(R.layout.ders_list_row,parent,false);
        Button ders_list_button = (Button) row.findViewById(R.id.ders_row_adi);

        row.setTag(dersler[position].id);
        ders_list_button.setText(dersler[position].adi);


        ders_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DersDuzenleFragment dersDuzenleFragment = new DersDuzenleFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "itemId" ,String.valueOf(dersler[position].id));
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

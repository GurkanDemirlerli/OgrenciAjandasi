package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Sinavlar;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tr.edu.sakarya.ogrenciajandasi.R;
import tr.edu.sakarya.ogrenciajandasi.database.DatabaseQueryClass;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.AjandaBottomDialog;
import tr.edu.sakarya.ogrenciajandasi.models.Hatirlatici;
import tr.edu.sakarya.ogrenciajandasi.models.Sinav;


public class SinavAdapter extends BaseAdapter {

    private Context context;
    private Sinav[] sinavlar;
    private int count;

    public SinavAdapter(Context context  ){
        this.context=context;
        DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(context);
        List<Sinav> list= databaseQueryClass.getAllSinavlar();
        this.count=list.size();
        Sinav[] sinavlar = new Sinav[list.size()];
        list.toArray(sinavlar);
        this.sinavlar=sinavlar;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View row = inflater.inflate(R.layout.sinav_list_row,parent,false);

        TextView snv_baslik= (TextView) row.findViewById(R.id.snv_baslik);
        TextView snv_tarih= (TextView) row.findViewById(R.id.snv_tarih);
        TextView snv_detay= (TextView) row.findViewById(R.id.snv_detay);
        TextView snv_ders= (TextView) row.findViewById(R.id.snv_ders);
        TextView snv_yer= (TextView) row.findViewById(R.id.snv_yer);



        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        String tarih = sinavlar[position].etkinlik.tarih;
        row.setTag(sinavlar[position].etkinlik.id);

        try {
            Date date = format.parse(tarih);
            snv_tarih.setText(  DateFormat.getDateInstance(DateFormat.LONG).format(date));

        } catch (ParseException e) {

            e.printStackTrace();
        }
        snv_baslik.setText(sinavlar[position].etkinlik.baslik);
        snv_detay.setText(sinavlar[position].etkinlik.detay);
        snv_ders.setText(sinavlar[position].dersAdi);
        snv_yer.setText(sinavlar[position].sinavYeri);


        Button opt_button = (Button) row.findViewById(R.id.snv_item_opt_button);
        opt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View bottomSheetView = ((FragmentActivity)context).getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
                AjandaBottomDialog bottomSheet = new AjandaBottomDialog();
                bottomSheet.itemId = Long.valueOf(row.getTag().toString()).longValue();
                bottomSheet.itemOzet = ((TextView) row.findViewById(R.id.snv_baslik)).getText().toString();
                bottomSheet.itemTipi = 2;
                bottomSheet.show(((FragmentActivity) context).getSupportFragmentManager(),"bottomsheet");
            }
        });

        return row;
    }
}

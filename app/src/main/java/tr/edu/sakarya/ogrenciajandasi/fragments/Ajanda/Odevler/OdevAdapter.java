package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Odevler;

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
import tr.edu.sakarya.ogrenciajandasi.models.Odev;


public class OdevAdapter extends BaseAdapter {

    private Context context;
    private Odev[] odevler;
    private int count;

    public OdevAdapter(Context context  ){
        this.context=context;
        DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(context);
        List<Odev> list= databaseQueryClass.getAllOdevler();
        this.count=list.size();
        Odev[] odevler = new Odev[list.size()];
        list.toArray(odevler);
        this.odevler=odevler;
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
        final View row = inflater.inflate(R.layout.odev_list_row,parent,false);

        TextView odv_baslik= (TextView) row.findViewById(R.id.odv_baslik);
        TextView odv_tarih= (TextView) row.findViewById(R.id.odv_tarih);
        TextView odv_detay= (TextView) row.findViewById(R.id.odv_detay);
        TextView odv_ders= (TextView) row.findViewById(R.id.odv_ders);


        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        String tarih = odevler[position].etkinlik.tarih;
        row.setTag(odevler[position].etkinlik.id);

        try {
            Date date = format.parse(tarih);
            odv_tarih.setText(  DateFormat.getDateInstance(DateFormat.LONG).format(date));

        } catch (ParseException e) {

            e.printStackTrace();
        }
        odv_baslik.setText(odevler[position].etkinlik.baslik);
        odv_detay.setText(odevler[position].etkinlik.detay);
        odv_ders.setText(odevler[position].dersAdi);

        Button opt_button = (Button) row.findViewById(R.id.odv_item_opt_button);
        opt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View bottomSheetView = ((FragmentActivity)context).getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
                AjandaBottomDialog bottomSheet = new AjandaBottomDialog();
                bottomSheet.itemId = Long.valueOf(row.getTag().toString()).longValue();
                bottomSheet.itemOzet = ((TextView) row.findViewById(R.id.odv_baslik)).getText().toString();
                bottomSheet.itemTipi = 1;
                bottomSheet.show(((FragmentActivity) context).getSupportFragmentManager(),"bottomsheet");
            }
        });

        return row;
    }
}

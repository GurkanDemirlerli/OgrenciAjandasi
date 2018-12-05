package tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.Hatirlaticilar;

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
import tr.edu.sakarya.ogrenciajandasi.models.Hatirlatici;
import tr.edu.sakarya.ogrenciajandasi.fragments.Ajanda.AjandaBottomDialog;


public class HatirlaticiAdapter extends BaseAdapter {

    private Context context;
    private Hatirlatici[] hatirlaticilar;
    private int count;

    public HatirlaticiAdapter(Context context  ){
        this.context=context;
        DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(context);
        List<Hatirlatici> list= databaseQueryClass.getAllHatirlaticilar();
        this.count=list.size();
        Hatirlatici[] hatirlaticilar = new Hatirlatici[list.size()];
        list.toArray(hatirlaticilar);
        this.hatirlaticilar=hatirlaticilar;
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
        final View row = inflater.inflate(R.layout.hatirlatici_list_row,parent,false);

        TextView hatirlatici_baslik= (TextView) row.findViewById(R.id.hatirlatici_baslik);
        TextView hatirlatici_tarih= (TextView) row.findViewById(R.id.hatirlatici_tarih);
        TextView hatirlatici_detay= (TextView) row.findViewById(R.id.hatirlatici_detay);

        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        String tarih = hatirlaticilar[position].etkinlik.tarih;
        row.setTag(hatirlaticilar[position].etkinlik.id);

        try {
            Date date = format.parse(tarih);
            hatirlatici_tarih.setText(  DateFormat.getDateInstance(DateFormat.LONG).format(date));

        } catch (ParseException e) {

            e.printStackTrace();
        }
        hatirlatici_baslik.setText(hatirlaticilar[position].etkinlik.baslik);
        hatirlatici_detay.setText(hatirlaticilar[position].etkinlik.detay);


        Button opt_button = (Button) row.findViewById(R.id.hatirlatici_item_opt_button);
        opt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View bottomSheetView = ((FragmentActivity)context).getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
                AjandaBottomDialog bottomSheet = new AjandaBottomDialog();
                bottomSheet.itemId = Long.valueOf(row.getTag().toString()).longValue();
                bottomSheet.itemOzet = ((TextView) row.findViewById(R.id.hatirlatici_baslik)).getText().toString();
                bottomSheet.itemTipi = 0;
                bottomSheet.show(((FragmentActivity) context).getSupportFragmentManager(),"bottomsheet");
            }
        });

        return row;
    }
}

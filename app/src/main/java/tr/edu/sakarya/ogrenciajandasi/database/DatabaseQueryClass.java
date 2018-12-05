package tr.edu.sakarya.ogrenciajandasi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tr.edu.sakarya.ogrenciajandasi.models.Ders;
import tr.edu.sakarya.ogrenciajandasi.models.DersSaati;
import tr.edu.sakarya.ogrenciajandasi.models.Etkinlik;
import tr.edu.sakarya.ogrenciajandasi.models.Hatirlatici;
import tr.edu.sakarya.ogrenciajandasi.models.Odev;
import tr.edu.sakarya.ogrenciajandasi.models.Sinav;

import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_AD;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_BASLIK;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_DERS_ADI;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_DERS_ID;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_DETAY;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_ETKINLIKID;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_ETKINLIKTIPI;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_GUN;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_ID;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_OGRETMEN;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_SAAT;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_SINAVYERI;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_SINIF;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.KEY_TARIH;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.TABLE_DERSLER;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.TABLE_DERS_SAATLERI;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.TABLE_ETKINLIKLER;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.TABLE_HATIRLATICILAR;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.TABLE_ODEVLER;
import static tr.edu.sakarya.ogrenciajandasi.util.Constants.TABLE_SINAVLAR;

public class DatabaseQueryClass {
    private Context context;

    public DatabaseQueryClass(Context context){
        this.context = context;
    }

    public long insertHatirlatici(Hatirlatici hatirlatici){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues etkinlikValues = new ContentValues();

        etkinlikValues.put(KEY_BASLIK, hatirlatici.etkinlik.baslik);
        etkinlikValues.put(KEY_DETAY, hatirlatici.etkinlik.detay);
        etkinlikValues.put(KEY_TARIH, hatirlatici.etkinlik.tarih);
        etkinlikValues.put(KEY_ETKINLIKTIPI, hatirlatici.etkinlik.etkinlikTipi);

        long etkinlikId = sqLiteDatabase.insert(TABLE_ETKINLIKLER, null, etkinlikValues);

        ContentValues hatirlaticiValues = new ContentValues();
        hatirlaticiValues.put(KEY_ETKINLIKID,etkinlikId);
        long hatirlaticiId = sqLiteDatabase.insert(TABLE_HATIRLATICILAR, null, hatirlaticiValues);
        return hatirlaticiId;

    }

    public long insertSinav(Sinav sinav){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues etkinlikValues = new ContentValues();

        etkinlikValues.put(KEY_BASLIK, sinav.etkinlik.baslik);
        etkinlikValues.put(KEY_DETAY, sinav.etkinlik.detay);
        etkinlikValues.put(KEY_TARIH, sinav.etkinlik.tarih);
        etkinlikValues.put(KEY_ETKINLIKTIPI, sinav.etkinlik.etkinlikTipi);

        long etkinlikId = sqLiteDatabase.insert(TABLE_ETKINLIKLER, null, etkinlikValues);

        ContentValues sinavValues = new ContentValues();
        sinavValues.put(KEY_ETKINLIKID,etkinlikId);
        sinavValues.put(KEY_DERS_ADI,sinav.dersAdi);
        sinavValues.put(KEY_SINAVYERI,sinav.sinavYeri);

        long sinavId = sqLiteDatabase.insert(TABLE_SINAVLAR, null, sinavValues);
        return etkinlikId;

    }

    public long insertOdev(Odev odev){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues etkinlikValues = new ContentValues();

        etkinlikValues.put(KEY_BASLIK, odev.etkinlik.baslik);
        etkinlikValues.put(KEY_DETAY, odev.etkinlik.detay);
        etkinlikValues.put(KEY_TARIH, odev.etkinlik.tarih);
        etkinlikValues.put(KEY_ETKINLIKTIPI, odev.etkinlik.etkinlikTipi);

        long etkinlikId = sqLiteDatabase.insert(TABLE_ETKINLIKLER, null, etkinlikValues);

        ContentValues odevValues = new ContentValues();
        odevValues.put(KEY_ETKINLIKID,etkinlikId);
        odevValues.put(KEY_DERS_ADI,odev.dersAdi);

        long odevId = sqLiteDatabase.insert(TABLE_ODEVLER, null, odevValues);
        return etkinlikId;

    }

    public long updateHatirlatici(Hatirlatici hatirlatici){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues etkinlikValues = new ContentValues();
        etkinlikValues.put(KEY_BASLIK, hatirlatici.etkinlik.baslik);
        etkinlikValues.put(KEY_DETAY, hatirlatici.etkinlik.detay);
        etkinlikValues.put(KEY_TARIH, hatirlatici.etkinlik.tarih);
        etkinlikValues.put(KEY_ETKINLIKTIPI, hatirlatici.etkinlik.etkinlikTipi);

        rowCount = sqLiteDatabase.update(TABLE_ETKINLIKLER, etkinlikValues,
                KEY_ID + " = ? ",
                new String[] {String.valueOf(hatirlatici.etkinlik.id)});

        ContentValues hatirlaticiValues = new ContentValues();
        hatirlaticiValues.put(KEY_ETKINLIKID,hatirlatici.etkinlikId);
        rowCount = sqLiteDatabase.update(TABLE_HATIRLATICILAR, hatirlaticiValues,
                KEY_ETKINLIKID + " = ? ",
                new String[] {String.valueOf(hatirlatici.etkinlikId)});

        return rowCount;
    }

    public long updateSinav(Sinav sinav){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues etkinlikValues = new ContentValues();
        etkinlikValues.put(KEY_BASLIK, sinav.etkinlik.baslik);
        etkinlikValues.put(KEY_DETAY, sinav.etkinlik.detay);
        etkinlikValues.put(KEY_TARIH, sinav.etkinlik.tarih);
        etkinlikValues.put(KEY_ETKINLIKTIPI, sinav.etkinlik.etkinlikTipi);

        rowCount = sqLiteDatabase.update(TABLE_ETKINLIKLER, etkinlikValues,
                KEY_ID + " = ? ",
                new String[] {String.valueOf(sinav.etkinlik.id)});

        ContentValues sinavValues = new ContentValues();
        sinavValues.put(KEY_ETKINLIKID,sinav.etkinlikId);
        sinavValues.put(KEY_DERS_ADI,sinav.dersAdi);
        sinavValues.put(KEY_SINAVYERI,sinav.sinavYeri);

        rowCount = sqLiteDatabase.update(TABLE_SINAVLAR, sinavValues,
                KEY_ETKINLIKID + " = ? ",
                new String[] {String.valueOf(sinav.etkinlikId)});

        return rowCount;
    }

    public long updateOdev(Odev odev){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues etkinlikValues = new ContentValues();
        etkinlikValues.put(KEY_BASLIK, odev.etkinlik.baslik);
        etkinlikValues.put(KEY_DETAY, odev.etkinlik.detay);
        etkinlikValues.put(KEY_TARIH, odev.etkinlik.tarih);
        etkinlikValues.put(KEY_ETKINLIKTIPI, odev.etkinlik.etkinlikTipi);

        rowCount = sqLiteDatabase.update(TABLE_ETKINLIKLER, etkinlikValues,
                KEY_ID + " = ? ",
                new String[] {String.valueOf(odev.etkinlik.id)});

        ContentValues odevValues = new ContentValues();
        odevValues.put(KEY_ETKINLIKID,odev.etkinlikId);
        odevValues.put(KEY_DERS_ADI,odev.dersAdi);

        rowCount = sqLiteDatabase.update(TABLE_ODEVLER, odevValues,
                KEY_ETKINLIKID + " = ? ",
                new String[] {String.valueOf(odev.etkinlikId)});
        return rowCount;
    }

    public long deleteEtkinlikById(long id) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(TABLE_ETKINLIKLER,
                    KEY_ID + " = ? ",
                    new String[]{ String.valueOf(id)});
        } catch (SQLiteException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public Hatirlatici getHatirlaticiById(long id){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Hatirlatici hatirlatici = null;
        try {

            String SELECT_QUERY = String.format("SELECT * FROM " + TABLE_HATIRLATICILAR + " hatirlatici INNER JOIN "
                    + TABLE_ETKINLIKLER + " etkinlik ON hatirlatici.etkinlikId = etkinlik.id AND etkinlik.id =" + id);

            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);

            if(cursor.moveToFirst()){
                hatirlatici = new Hatirlatici();
                hatirlatici.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                hatirlatici.etkinlikId = cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                hatirlatici.etkinlik = new Etkinlik();
                hatirlatici.etkinlik.baslik = cursor.getString(cursor.getColumnIndex(KEY_BASLIK));
                hatirlatici.etkinlik.detay = cursor.getString(cursor.getColumnIndex(KEY_DETAY));
                hatirlatici.etkinlik.id =cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                hatirlatici.etkinlik.etkinlikTipi = cursor.getString(cursor.getColumnIndex(KEY_ETKINLIKTIPI));
                hatirlatici.etkinlik.tarih = cursor.getString(cursor.getColumnIndex(KEY_TARIH));
            }
        } catch (Exception e){
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return hatirlatici;
    }

    public Sinav getSinavById(long id){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Sinav sinav = null;
        try {

            String SELECT_QUERY = String.format("SELECT * FROM " + TABLE_SINAVLAR + " sinav INNER JOIN "
                    + TABLE_ETKINLIKLER + " etkinlik ON sinav.etkinlikId = etkinlik.id AND etkinlik.id =" + id);

            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);

            if(cursor.moveToFirst()){
                sinav = new Sinav();
                sinav.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                sinav.etkinlikId = cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                sinav.etkinlik = new Etkinlik();
                sinav.etkinlik.baslik = cursor.getString(cursor.getColumnIndex(KEY_BASLIK));
                sinav.etkinlik.detay = cursor.getString(cursor.getColumnIndex(KEY_DETAY));
                sinav.etkinlik.id =cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                sinav.etkinlik.etkinlikTipi = cursor.getString(cursor.getColumnIndex(KEY_ETKINLIKTIPI));
                sinav.etkinlik.tarih = cursor.getString(cursor.getColumnIndex(KEY_TARIH));
                sinav.dersAdi =  cursor.getString(cursor.getColumnIndex(KEY_DERS_ADI));
                sinav.sinavYeri =  cursor.getString(cursor.getColumnIndex(KEY_SINAVYERI));


            }
        } catch (Exception e){
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return sinav;
    }

    public Odev getOdevById(long id){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Odev odev = null;
        try {

            String SELECT_QUERY = String.format("SELECT * FROM " + TABLE_ODEVLER + " odev INNER JOIN "
                    + TABLE_ETKINLIKLER + " etkinlik ON odev.etkinlikId = etkinlik.id AND etkinlik.id =" + id);

            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);

            if(cursor.moveToFirst()){
                odev = new Odev();
                odev.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                odev.etkinlikId = cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                odev.etkinlik = new Etkinlik();
                odev.etkinlik.baslik = cursor.getString(cursor.getColumnIndex(KEY_BASLIK));
                odev.etkinlik.detay = cursor.getString(cursor.getColumnIndex(KEY_DETAY));
                odev.etkinlik.id =cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                odev.etkinlik.etkinlikTipi = cursor.getString(cursor.getColumnIndex(KEY_ETKINLIKTIPI));
                odev.etkinlik.tarih = cursor.getString(cursor.getColumnIndex(KEY_TARIH));
                odev.dersAdi =  cursor.getString(cursor.getColumnIndex(KEY_DERS_ADI));
            }
        } catch (Exception e){
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return odev;
    }



    public List<Hatirlatici> getAllHatirlaticilar(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
           String SELECT_QUERY = String.format("SELECT * FROM " + TABLE_HATIRLATICILAR + " hatirlatici INNER JOIN "
                    + TABLE_ETKINLIKLER + " etkinlik ON hatirlatici.etkinlikId = etkinlik.id");

           // String SELECT_QUERY = String.format("SELECT * FROM " + TABLE_HATIRLATICILAR );


            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


            if(cursor!=null){

                if(cursor.moveToFirst()){
                    List<Hatirlatici> hatirlaticiList = new ArrayList<>();
                    do {

                        Hatirlatici hatirlatici = new Hatirlatici();
                        hatirlatici.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));

                        hatirlatici.etkinlikId = cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                        hatirlatici.etkinlik = new Etkinlik();
                        hatirlatici.etkinlik.baslik = cursor.getString(cursor.getColumnIndex(KEY_BASLIK));
                        hatirlatici.etkinlik.detay = cursor.getString(cursor.getColumnIndex(KEY_DETAY));
                        hatirlatici.etkinlik.id =cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                        hatirlatici.etkinlik.etkinlikTipi = cursor.getString(cursor.getColumnIndex(KEY_ETKINLIKTIPI));
                        hatirlatici.etkinlik.tarih = cursor.getString(cursor.getColumnIndex(KEY_TARIH));
                        hatirlaticiList.add(hatirlatici);
                    }   while (cursor.moveToNext());

                    return hatirlaticiList;
                }

            }
        } catch (Exception e){

            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<Sinav> getAllSinavlar(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            String SELECT_QUERY = String.format("SELECT * FROM " + TABLE_SINAVLAR + " sinav INNER JOIN "
                    + TABLE_ETKINLIKLER + " etkinlik ON sinav.etkinlikId = etkinlik.id ");

            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


            if(cursor!=null){

                if(cursor.moveToFirst()){
                    List<Sinav> sinavList = new ArrayList<>();
                    do {

                        Sinav sinav  = new Sinav();
                        sinav.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                        sinav.etkinlikId = cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                        sinav.etkinlik = new Etkinlik();
                        sinav.etkinlik.baslik = cursor.getString(cursor.getColumnIndex(KEY_BASLIK));
                        sinav.etkinlik.detay = cursor.getString(cursor.getColumnIndex(KEY_DETAY));
                        sinav.etkinlik.id =cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                        sinav.etkinlik.etkinlikTipi = cursor.getString(cursor.getColumnIndex(KEY_ETKINLIKTIPI));
                        sinav.etkinlik.tarih = cursor.getString(cursor.getColumnIndex(KEY_TARIH));
                        sinav.dersAdi =  cursor.getString(cursor.getColumnIndex(KEY_DERS_ADI));
                        sinav.sinavYeri =  cursor.getString(cursor.getColumnIndex(KEY_SINAVYERI));
                        sinavList.add(sinav);

                    }   while (cursor.moveToNext());

                    return sinavList;
                }

            }
        } catch (Exception e){

            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }


    public List<Odev> getAllOdevler(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            String SELECT_QUERY = String.format("SELECT * FROM " + TABLE_ODEVLER + " odev INNER JOIN "
                    + TABLE_ETKINLIKLER + " etkinlik ON odev.etkinlikId = etkinlik.id ");

            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


            if(cursor!=null){

                if(cursor.moveToFirst()){
                    List<Odev> odevList = new ArrayList<>();
                    do {

                        Odev odev  = new Odev();
                        odev.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                        odev.etkinlikId = cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                        odev.etkinlik = new Etkinlik();
                        odev.etkinlik.baslik = cursor.getString(cursor.getColumnIndex(KEY_BASLIK));
                        odev.etkinlik.detay = cursor.getString(cursor.getColumnIndex(KEY_DETAY));
                        odev.etkinlik.id =cursor.getInt(cursor.getColumnIndex(KEY_ETKINLIKID));
                        odev.etkinlik.etkinlikTipi = cursor.getString(cursor.getColumnIndex(KEY_ETKINLIKTIPI));
                        odev.etkinlik.tarih = cursor.getString(cursor.getColumnIndex(KEY_TARIH));
                        odev.dersAdi =  cursor.getString(cursor.getColumnIndex(KEY_DERS_ADI));
                        odevList.add(odev);

                    }   while (cursor.moveToNext());

                    return odevList;
                }

            }
        } catch (Exception e){

            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }


    public long insertDers(Ders ders){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues dersValues = new ContentValues();

        dersValues.put(KEY_AD, ders.adi);
        dersValues.put(KEY_SINIF, ders.sinifi);
        dersValues.put(KEY_OGRETMEN, ders.ogretmen);

        long dersId = sqLiteDatabase.insert(TABLE_DERSLER, null, dersValues);

        return dersId;
    }

    public long updateDers(Ders ders){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues dersValues = new ContentValues();

        dersValues.put(KEY_AD, ders.adi);
        dersValues.put(KEY_SINIF, ders.sinifi);
        dersValues.put(KEY_OGRETMEN, ders.ogretmen);


        rowCount = sqLiteDatabase.update(TABLE_DERSLER, dersValues,
                KEY_ID + " = ? ",
                new String[] {String.valueOf(ders.id)});
        return rowCount;
    }

    public List<Ders> getAllDersler(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_DERSLER, null, null, null, null, null, null, null);

            if(cursor!=null){

                if(cursor.moveToFirst()){
                    List<Ders> dersList = new ArrayList<>();
                    do {

                        Ders ders = new Ders();
                        ders.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                        ders.adi=cursor.getString(cursor.getColumnIndex(KEY_AD));
                        ders.sinifi=cursor.getString(cursor.getColumnIndex(KEY_SINIF));
                        ders.ogretmen=cursor.getString(cursor.getColumnIndex(KEY_OGRETMEN));
                        dersList.add(ders);
                    }   while (cursor.moveToNext());

                    return dersList;
                }

            }
        } catch (Exception e){
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }
        return Collections.emptyList();
    }


    public Ders getDersById(long id){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Ders ders = null;
        try {

            cursor = sqLiteDatabase.query(TABLE_DERSLER, null,
                    KEY_ID + " = ? ", new String[]{String.valueOf(id)},
                    null, null, null);

            if(cursor.moveToFirst()){
                ders = new Ders();
                ders.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                ders.adi=cursor.getString(cursor.getColumnIndex(KEY_AD));
                ders.sinifi=cursor.getString(cursor.getColumnIndex(KEY_SINIF));
                ders.ogretmen=cursor.getString(cursor.getColumnIndex(KEY_OGRETMEN));
            }
        } catch (Exception e){
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return ders;
    }

    public long deleteDersById(long id){
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(TABLE_DERSLER,
                    KEY_ID + " = ? ",
                    new String[]{ String.valueOf(id)});
        } catch (SQLiteException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public long insertDersSaati(DersSaati dersSaati){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues dersSaatiValues = new ContentValues();

        dersSaatiValues.put(KEY_SAAT, dersSaati.saat);
        dersSaatiValues.put(KEY_DERS_ID, dersSaati.dersId);
        dersSaatiValues.put(KEY_GUN, dersSaati.gun);

        long dersSaatiId = sqLiteDatabase.insert(TABLE_DERS_SAATLERI, null, dersSaatiValues);

        return dersSaatiId;
    }


    public List<DersSaati> getDersSaatleriByDersId (long dersId){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_DERS_SAATLERI, null,
                    KEY_DERS_ID + " = ? ", new String[]{String.valueOf(dersId)},
                    null, null, null);

            if(cursor!=null){

                if(cursor.moveToFirst()){
                    List<DersSaati> dersSaatiList = new ArrayList<>();
                    do {

                        DersSaati dersSaati = new DersSaati();
                        dersSaati.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                        dersSaati.dersId = cursor.getInt(cursor.getColumnIndex(KEY_DERS_ID));
                        dersSaati.gun = cursor.getInt(cursor.getColumnIndex(KEY_GUN));
                        dersSaati.saat = cursor.getString(cursor.getColumnIndex(KEY_SAAT));

                        dersSaatiList.add(dersSaati);
                    }   while (cursor.moveToNext());

                    return dersSaatiList;
                }

            }
        } catch (Exception e){

            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public long deleteDersSaatiById(long id){
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(TABLE_DERS_SAATLERI,
                    KEY_ID + " = ? ",
                    new String[]{ String.valueOf(id)});
        } catch (SQLiteException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public List<DersSaati> getDersSaatleriByGunId(long gun){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            String SELECT_QUERY = String.format("SELECT * FROM " + TABLE_DERS_SAATLERI + " saat INNER JOIN "
                    + TABLE_DERSLER + " ders ON saat.dersId = ders.id AND saat.gun =" + gun);

            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


            if(cursor!=null){

                if(cursor.moveToFirst()){
                    List<DersSaati> dersSaatiList = new ArrayList<>();
                    do {

                        DersSaati dersSaati = new DersSaati();
                        dersSaati.ders = new Ders();
                        dersSaati.ders.adi = cursor.getString(cursor.getColumnIndex(KEY_AD));
                        dersSaati.gun = cursor.getInt(cursor.getColumnIndex(KEY_GUN));
                        dersSaati.saat = cursor.getString(cursor.getColumnIndex(KEY_SAAT));
                        dersSaatiList.add(dersSaati);
                    }   while (cursor.moveToNext());

                    return dersSaatiList;
                }

            }
        } catch (Exception e){

            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }
}

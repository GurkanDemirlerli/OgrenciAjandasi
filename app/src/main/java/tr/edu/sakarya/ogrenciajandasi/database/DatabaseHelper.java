package tr.edu.sakarya.ogrenciajandasi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import tr.edu.sakarya.ogrenciajandasi.models.Hatirlatici;

import static tr.edu.sakarya.ogrenciajandasi.util.Constants.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "OgrenciAjandasi";
    private static final int DATABASE_VERSION=1;
    private static DatabaseHelper databaseHelper;

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if(databaseHelper==null){
            synchronized (DatabaseHelper.class) {
                if(databaseHelper==null)
                    databaseHelper = new DatabaseHelper(context);
            }
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_ETKINLIKLER = "CREATE TABLE " + TABLE_ETKINLIKLER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_BASLIK  + " TEXT, "
                + KEY_DETAY + " TEXT, "
                + KEY_ETKINLIKTIPI + " TEXT, "
                + KEY_TARIH + " DATETIME"
                + ")";

        String CREATE_TABLE_DERSLER = "CREATE TABLE " + TABLE_DERSLER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_AD  + " TEXT, "
                + KEY_SINIF + " TEXT, "
                + KEY_OGRETMEN + " TEXT"
                + ")";

        String CREATE_TABLE_DERS_SAATLERI = "CREATE TABLE " + TABLE_DERS_SAATLERI + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_GUN  + " INTEGER, "
                + KEY_SAAT + " TEXT, "
                + KEY_DERS_ID + " INTEGER, "
                + "FOREIGN KEY (" + KEY_DERS_ID + ") REFERENCES " + TABLE_DERSLER + "(" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE "
                + ")";


        String CREATE_TABLE_HATIRLATICILAR = "CREATE TABLE " + TABLE_HATIRLATICILAR + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_ETKINLIKID + " INTEGER, "
                + "FOREIGN KEY (" + KEY_ETKINLIKID + ") REFERENCES " + TABLE_ETKINLIKLER + "(" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE "
                + ")";

        String CREATE_TABLE_SINAVLAR = "CREATE TABLE " + TABLE_SINAVLAR + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_ETKINLIKID + " INTEGER, "
                + KEY_DERS_ADI + " TEXT, "
                + KEY_SINAVYERI + " TEXT, "
                + "FOREIGN KEY (" + KEY_ETKINLIKID + ") REFERENCES " + TABLE_ETKINLIKLER + "(" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE "
                + ")";

        String CREATE_TABLE_ODEVLER = "CREATE TABLE " + TABLE_ODEVLER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_ETKINLIKID + " INTEGER, "
                + KEY_DERS_ADI + " TEXT, "
                + "FOREIGN KEY (" + KEY_ETKINLIKID + ") REFERENCES " + TABLE_ETKINLIKLER + "(" + KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE "
                + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE_ETKINLIKLER);
        sqLiteDatabase.execSQL(CREATE_TABLE_DERSLER);
        sqLiteDatabase.execSQL(CREATE_TABLE_HATIRLATICILAR);
        sqLiteDatabase.execSQL(CREATE_TABLE_SINAVLAR);
        sqLiteDatabase.execSQL(CREATE_TABLE_ODEVLER);
        sqLiteDatabase.execSQL(CREATE_TABLE_DERS_SAATLERI);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HATIRLATICILAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SINAVLAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ODEVLER);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DERSLER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ETKINLIKLER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DERS_SAATLERI);


        // Create tables again
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        db.execSQL("PRAGMA foreign_keys=ON;");
    }
}

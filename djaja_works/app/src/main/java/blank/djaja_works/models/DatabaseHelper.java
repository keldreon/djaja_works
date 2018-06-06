package blank.djaja_works.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "Djaya_backend.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Akun.createTable);
        db.execSQL(Investment.createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Akun.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Investment.TABLE_NAME);
        onCreate(db);
    }

    public void daftarAkun(String email, String pass, String nL, String jK, String umr, String nktp, String nrek, int nom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Akun.COL2, email);
        values.put(Akun.COL3, nL);
        values.put(Akun.COL4, jK);
        values.put(Akun.COL5, umr);
        values.put(Akun.COL6, nktp);
        values.put(Akun.COL7, nrek);
        values.put(Akun.COL8, pass);
        values.put(Akun.COL10, nom);
        Log.d(TAG, "Hasil " +values.get(Akun.COL2));
        db.insert(Akun.TABLE_NAME,null, values);
        db.close();
    }

    public Akun getInfoAkun(String nama, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        //nama == email

        String selectQuery = "SELECT  * FROM "+ Akun.TABLE_NAME + " WHERE "+ Akun.COL2 +" == "+nama+" && "+Akun.COL8+ " == "+pass;
        /*(int id, String email, String pass, String jK, String umur, String nktp, String nrek, String st)*/

        Cursor cr = db.query(Akun.TABLE_NAME,
                new String[]{Akun.COL1, Akun.COL2, Akun.COL3, Akun.COL4, Akun.COL5, Akun.COL6, Akun.COL7, Akun.COL8, Akun.COL10},
                Akun.COL2 + "=?", new String[]{String.valueOf(nama)},null,null,null);

        if(cr != null){
            cr.moveToFirst();
        }
        //public Akun(String email, String pass, String nL, String jK, String umur, String nktp, String nrek, String st)

        Akun akun = new Akun(cr.getInt(cr.getColumnIndex(Akun.COL1)),
                cr.getString(cr.getColumnIndex(Akun.COL2)),
                cr.getString(cr.getColumnIndex(Akun.COL8)),
                cr.getString(cr.getColumnIndex(Akun.COL3)),
                cr.getString(cr.getColumnIndex(Akun.COL4)),
                cr.getString(cr.getColumnIndex(Akun.COL5)),
                cr.getString(cr.getColumnIndex(Akun.COL6)),
                cr.getString(cr.getColumnIndex(Akun.COL7)),
                cr.getInt(cr.getColumnIndex(Akun.COL10)));
        //(int id, String uname, String email, String pass, String jK, String nktp, String nrek, String st)
        cr.close();
        return akun;
    }

    public List<Akun> getAllAkun(){
        List<Akun> akun = new ArrayList<>();
        //Select All Query
        String sQuery = "SELECT * FROM " + Akun.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery(sQuery, null);

        if(cr.moveToFirst()){
            do{
                Akun ak = new Akun();
                ak.setId(cr.getInt(cr.getColumnIndex(Akun.COL1)));
                ak.setEmail(cr.getString(cr.getColumnIndex(Akun.COL2)));
                ak.setJk(cr.getString(cr.getColumnIndex(Akun.COL3)));
                ak.setNoKtp(cr.getString(cr.getColumnIndex(Akun.COL4)));
                ak.setUmur(cr.getString(cr.getColumnIndex(Akun.COL5)));
                ak.setNoRek(cr.getString(cr.getColumnIndex(Akun.COL6)));
                ak.setNom(cr.getInt(cr.getColumnIndex(Akun.COL10)));
                akun.add(ak);
            }while (cr.moveToNext());
        }
        db.close();
        return akun;
    }

    public int getAkunCount(){
        String countQuery = "SELECT * FROM " + Akun.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery(countQuery, null);

        int count = cr.getCount();
        return count;
    }

    public int updateAkun(Akun akun){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(Akun.COL2, akun.getEmail());
        v.put(Akun.COL3, akun.getNama_lengkap());
        v.put(Akun.COL4, akun.getJk());
        v.put(Akun.COL5, akun.getUmur());
        v.put(Akun.COL6, akun.getNoKtp());
        v.put(Akun.COL7, akun.getNoRek());
        v.put(Akun.COL8, akun.getPassword());

        return db.update(Akun.TABLE_NAME, v, Akun.COL2 + " =? ",
                new String[]{String.valueOf(akun.getEmail())});
    }

    public List<Investment> getAllInvest(){
        List<Investment> inv = new ArrayList<>();
        //Select All Query
        String sQuery = "SELECT * FROM " + Investment.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery(sQuery, null);

        if(cr.moveToFirst()){
            //public Investment(int id, String un, String nU, String d, int nom, int gj, String st)
            do{
                Investment ak = new Investment();
                ak.setId(cr.getInt(cr.getColumnIndex(Investment.COL1)));
                ak.setNama_lengkap(cr.getString(cr.getColumnIndex(Investment.COL2)));
                ak.setNamaUsaha(cr.getString(cr.getColumnIndex(Investment.COL3)));
                ak.setDeskripsi(cr.getString(cr.getColumnIndex(Investment.COL4)));
                ak.setgBulanan(cr.getString(cr.getColumnIndex(Investment.COL5)));
                ak.setNominal(cr.getInt(cr.getColumnIndex(Investment.COL6)));
                ak.setStatus(cr.getString(cr.getColumnIndex(Investment.COL7)));
                inv.add(ak);
            }while (cr.moveToNext());
        }
        db.close();
        return inv;
    }

    public Investment getInfoInv(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cr = db.query(Investment.TABLE_NAME,
                new String[]{Investment.COL1, Investment.COL2, Investment.COL3, Investment.COL4, Investment.COL5, Investment.COL6},
                Akun.COL1 + "=?", new String[]{String.valueOf(id)},null,null,null);

        if(cr != null){
            cr.moveToFirst();
        }

        Investment inv = new Investment(cr.getInt(cr.getColumnIndex(Investment.COL1)),
                cr.getString(cr.getColumnIndex(Investment.COL2)),
                cr.getString(cr.getColumnIndex(Investment.COL3)),
                cr.getString(cr.getColumnIndex(Investment.COL4)),
                cr.getString(cr.getColumnIndex(Investment.COL5)),
                cr.getInt(cr.getColumnIndex(Investment.COL6)),
                cr.getString(cr.getColumnIndex(Investment.COL7)));
        cr.close();
        return inv;
    }

    public int getInvCount(){
        String countQuery = "SELECT * FROM " + Investment.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery(countQuery, null);

        int count = cr.getCount();
        return count;
    }

    public void daftarUsaha(String un, String nU, String d, int nom, String gj, String st){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Investment.COL2, un);
        values.put(Investment.COL3, nU);
        values.put(Investment.COL4, d);
        values.put(Investment.COL5, gj);
        values.put(Investment.COL6, nom);
        values.put(Investment.COL7, st);
        Log.d(TAG, "Hasil " +values.get(Investment.COL2)+", "+values.get(Investment.COL3));
        db.insert(Investment.TABLE_NAME,null, values);
        db.close();
    }
}

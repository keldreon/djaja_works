package blank.djaja_works.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Investment {
    //Wilayah Investment
    //data investasi
    protected static final String TABLE_NAME = "investment";
    protected static final String COL1 = "ID_INVESTASI";
    protected static final String COL2 = "USERNAME";
    //protected static final String COL1 = "ID_AKUN";
    protected static final String COL3 = "NAMA_USAHA";
    protected static final String COL4 = "DESKRIPSI";
    protected static final String COL5 = "GAJI_BULANAN";
    protected static final String COL6 = "NOMINAL";
    protected static final String COL7 = "STATUS";

    @SerializedName("id_calon") @Expose private int id;
    @SerializedName("nama_pengusaha") @Expose private String nama_lengkap;
    @SerializedName("nama_usaha") @Expose private String namaUsaha;
    @SerializedName("deskripsi") @Expose private String deskripsi;
    private int gBulanan;
    private int nominal;
    private String status;

    protected static final String createTable = "CREATE TABLE "
            + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY, " + COL2 + " TEXT, "
            + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " INTEGER, " + COL6 + " INTEGER, " + COL7 + " TEXT "+")";

    public Investment(){}

    public Investment(int id, String un, String nU, String d, int gj, int nom, String st){
        this.id = id;
        this.nama_lengkap = un;
        this.namaUsaha = nU;
        this.deskripsi = d;
        this.gBulanan = gj;
        this.nominal = nom;
        this.status = st;
    }

    public Investment(String un, String nU, String d,  int gj, int nom, String st){
        this.nama_lengkap = un;
        this.namaUsaha = nU;
        this.deskripsi = d;
        this.gBulanan = gj;
        this.nominal = nom;
        this.status = st;
    }

    //public Investment(int id, String un, String nU, String d, String nom, String st, String id_akun){

    public void setNama_lengkap(String uname) {
        this.nama_lengkap = uname;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setNamaUsaha(String namaUsaha) {
        this.namaUsaha = namaUsaha;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getNamaUsaha() {
        return namaUsaha;
    }

    public int getNominal() {
        return nominal;
    }

    public String getStatus() {
        return status;
    }

    public int getgBulanan() {
        return gBulanan;
    }

    public void setgBulanan(int gBulanan) {
        this.gBulanan = gBulanan;
    }
}

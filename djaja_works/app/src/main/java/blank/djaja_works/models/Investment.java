package blank.djaja_works.models;

public class Investment {
    //Wilayah Investment
    protected static final String TABLE_NAME = "investment";
    protected static final String COL1 = "ID_INVESTASI";
    protected static final String COL2 = "USERNAME";
    protected static final String COL3 = "NAMA_USAHA";
    protected static final String COL4 = "DESKRIPSI";
    protected static final String COL5 = "NOMINAL";
    protected static final String COL6 = "STATUS";

    private int id;
    private String uname;
    private String namaUsaha;
    private String deskripsi;
    private String nominal;
    private String status;

    protected static final String createTable = "CREATE TABLE "
            + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY, " + COL2 + " TEXT, "
            + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " TEXT, " + ")";

    public Investment(){}

    public Investment(int id, String un, String nU, String d, String nom, String st){
        this.id = id;
        this.uname = un;
        this.namaUsaha = nU;
        this.deskripsi = d;
        this.nominal = nom;
        this.status = st;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getNamaUsaha() {
        return namaUsaha;
    }

    public String getNominal() {
        return nominal;
    }

    public String getStatus() {
        return status;
    }
}

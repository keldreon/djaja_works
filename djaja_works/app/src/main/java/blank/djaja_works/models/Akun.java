package blank.djaja_works.models;

public class Akun {
    //Wilayah User
    protected static final String TABLE_NAME = "user";
    protected static final String COL1 = "ID_USER";
    protected static final String COL2 = "EMAIL";
    protected static final String COL3 = "NAMA_LENGKAP";
    protected static final String COL4 = "JENIS_KELAMIN";
    protected static final String COL5 = "UMUR";
    protected static final String COL6 = "NO_KTP";
    protected static final String COL7 = "NO_REKENING";
    protected static final String COL8 = "PASSWORD";
    //protected static final String COL9 = "STATUS";
    protected static final String COL10 = "NOMINAL";
    //public static String createTable;

    private int id;
    private String email;
    private String nama_lengkap;
    private String password;
    private String jk;
    private String umur;
    private String noKtp;
    private String noRek;
    //private String status;
    private String nom;

    protected static final String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY, " + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " TEXT, " + COL7 + " TEXT, " + COL8 + " TEXT, " +COL10 + " TEXT" +")";

    public Akun(){
    }

    public Akun(int id, String email, String pass, String nL, String jK, String umur, String nktp, String nrek, String nom){
        this.id = id;
        this.email = email;
        this.password = pass;
        this.nama_lengkap = nL;
        this.jk = jK;
        this.umur = umur;
        this.noKtp=nktp;
        this.noRek=nrek;
        //this.status=st;
        this.nom = nom;
    }

    public Akun(String email, String pass, String nL, String jK, String umur, String nktp, String nrek, String nom){
        this.email = email;
        this.password = pass;
        this.nama_lengkap = nL;
        this.jk = jK;
        this.umur = umur;
        this.noKtp=nktp;
        this.noRek=nrek;
        //this.status=st;
        this.nom = nom;
    }

    /*public Akun(int anInt, String string, String string1, String string2, String string3, String string4, String string5) {
    }*/

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getJk() {
        return jk;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public String getNoRek() {
        return noRek;
    }

    /*public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getUmur() {
        return umur;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

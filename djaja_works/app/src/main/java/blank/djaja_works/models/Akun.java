package blank.djaja_works.models;

public class Akun {
    //Wilayah User
    protected static final String TABLE_NAME = "user";
    protected static final String COL1 = "ID_USER";
    protected static final String COL2 = "EMAIL";
    protected static final String COL3 = "JENIS_KELAMIN";
    protected static final String COL4 = "NO_KTP";
    protected static final String COL5 = "UMUR";
    protected static final String COL6 = "NO_REKENING";
    protected static final String COL7 = "PASSWORD";
    protected static final String COL8 = "STATUS";
    //public static String createTable;

    private int id;
    private String email;
    private String password;
    private String jk;
    private String umur;
    private String noKtp;
    private String noRek;
    private String status;

    protected static final String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY, " + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " TEXT, " + COL7 + " TEXT, " + COL8 + " TEXT" +")";

    public Akun(){
    }

    public Akun(int id, String email, String pass, String jK, String umur, String nktp, String nrek, String st){
        this.id = id;
        this.email = email;
        this.password = pass;
        this.jk = jK;
        this.umur = umur;
        this.noKtp=nktp;
        this.noRek=nrek;
        this.status=st;
    }

    public Akun(String email, String pass, String jK, String umur, String nktp, String nrek, String st){
        this.email = email;
        this.password = pass;
        this.jk = jK;
        this.umur = umur;
        this.noKtp=nktp;
        this.noRek=nrek;
        this.status=st;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
}

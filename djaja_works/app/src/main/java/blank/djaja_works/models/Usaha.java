package blank.djaja_works.models;

public class Usaha {
    protected static final String TABLE_NAME = "usaha";
    protected static final String COL1 = "ID_USAHA";
    protected static final String COL2 = "EMAIL";
    protected static final String COL3 = "USERNAME";
    //protected static final String COL1 = "ID_AKUN";
    protected static final String COL4 = "NAMA_USAHA";
    protected static final String COL5 = "DESKRIPSI";
    protected static final String COL6 = "P_BULANAN";//PENGHASILAN BULANAN
    protected static final String COL7 = "NEED_MODAL";
    protected static final String COL8 = "STATUS";//butuh/tidak
    /*
    * isi status : butuh_modal, sudah_dimodali.
    * */

    private int id_usaha;
    private String email;
    private String username;
    private String n_usaha;
    private Long deskripsi;
    private int p_bulanan;
    private int need_modal;
    private String status;

    public Usaha(){}

    public Usaha(int id, String id_akun, String uname, String n_usaha, Long desk, int p_bulanan, int need_modal, String st){
        this.id_usaha = id;
        this.email = id_akun;
        this.username = uname;
        this.n_usaha = n_usaha;
        this.deskripsi = desk;
        this.p_bulanan = p_bulanan;
        this.need_modal = need_modal;
        this.status = st;
    }

    public Usaha(String n_usaha, Long desk, int p_bulanan, int need_modal, String st){
        this.n_usaha = n_usaha;
        this.deskripsi = desk;
        this.p_bulanan = p_bulanan;
        this.need_modal = need_modal;
        this.status = st;
    }

    public Usaha(String st){
        this.status = st;
    }

    public int getId_usaha() {
        return id_usaha;
    }

    public void setId_usaha(int id_usaha) {
        this.id_usaha = id_usaha;
    }

    public String getEmailn() {
        return email;
    }

    public void setEmail(String id_akun) {
        this.email = id_akun;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getN_usaha() {
        return n_usaha;
    }

    public void setN_usaha(String n_usaha) {
        this.n_usaha = n_usaha;
    }

    public Long getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(Long deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getP_bulanan() {
        return p_bulanan;
    }

    public void setP_bulanan(int p_bulanan) {
        this.p_bulanan = p_bulanan;
    }

    public int getNeed_modal() {
        return need_modal;
    }

    public void setNeed_modal(int need_modal) {
        this.need_modal = need_modal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

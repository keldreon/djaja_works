package blank.djaja_works.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topup {
    @SerializedName("id_topup") @Expose private int id;
    @SerializedName("pembeli_coin") @Expose private String email;
    @SerializedName("kode") @Expose private String kode;
    @SerializedName("nominal") @Expose private int nom;
    @SerializedName("status_penggunaan") @Expose private int stat;
    @SerializedName("tanggal_pembelian") @Expose private String tgl;

    public Topup(int id, String mai, String kod, int no, int st, String tgl){
        this.id = id;
        this.email = mai;
        this.kode = kod;
        this.nom=no;
        this.stat=st;
        this.tgl=tgl;
    }

    public Topup(String mai, String kod, int no, int st, String tgl){
        this.email = mai;
        this.kode = kod;
        this.nom=no;
        this.stat=st;
        this.tgl=tgl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public int getNom() {
        return nom;
    }

    public void setNom(int nom) {
        this.nom = nom;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
}

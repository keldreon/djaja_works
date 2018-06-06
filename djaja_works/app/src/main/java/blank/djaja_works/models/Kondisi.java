package blank.djaja_works.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kondisi {
    @SerializedName("status") @Expose
    private String status;
    @SerializedName("code") @Expose private int kode;

    public Kondisi(int kd, String st){
        this.kode = kd;
        this.status = st;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }
}

package blank.djaja_works.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import blank.djaja_works.API.ApiClient;
import blank.djaja_works.API.ApiInterface;
import blank.djaja_works.R;
import blank.djaja_works.models.Akun;
import blank.djaja_works.models.SessionManager;
import blank.djaja_works.other.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class profilakun extends AppCompatActivity {

    private SessionManager sm;
    private TextView tvNama;
    private TextView tvEmail;
    private TextView tvCoin;
    private TextView tvjk;
    private ProgressDialog pDialog;
    private List<Akun> accountsList;
    private String satu;
    private String dua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        sm = new SessionManager(this);

        tvjk = findViewById(R.id.jK_profile);

        tvNama = findViewById(R.id.nama_profile);

        tvEmail = findViewById(R.id.email_profile);

        tvCoin = findViewById(R.id.tvCoin_profile);

        if(NetworkUtils.isNetworkConnected(this)){
            satu = sm.getEmail();
            dua = sm.getPss();
            updateProfile(satu,dua);
        }
        else{
            Toast.makeText(this, "Tidak ada Koneksi", Toast.LENGTH_LONG).show();
            tvCoin.setText(String.valueOf(sm.getSaldo()));
            tvNama.setText(sm.getNama());
            tvEmail.setText(sm.getEmail());
            tvjk.setText(sm.getJK());
        }
    }

    final Context c = this;
    public void updateProfile(String nama, String password){
        showDialog();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Akun>> call = apiService.getUserLogin(nama, password);
        call.enqueue(new Callback<List<Akun>>() {
            @Override
            public void onResponse(Call<List<Akun>> call, Response<List<Akun>> response) {
                //Toast.makeText(getApplicationContext(),)
                accountsList = response.body();
                Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));
                hideDialog();
                if (response.body().size() != 0){
                    int tes = accountsList.get(0).getNom();
                    sm.reWriteAkun(accountsList.get(0).getEmail(),accountsList.get(0).getNama_lengkap(),tes, accountsList.get(0).getUmur(), accountsList.get(0).getJk(), accountsList.get(0).getNoKtp(), accountsList.get(0).getNoRek(), accountsList.get(0).getPassword());
                }
                else{
                    Toast.makeText(c, "Update Gagal", Toast.LENGTH_LONG).show();
                    //dialog.dismiss();
                    return;
                }
            }
            @Override
            public void onFailure(Call<List<Akun>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(c, "Tidak ada Koneksi", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

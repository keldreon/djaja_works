package blank.djaja_works.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import blank.djaja_works.API.ApiClient;
import blank.djaja_works.API.ApiInterface;
import blank.djaja_works.R;
import blank.djaja_works.models.Akun;
import blank.djaja_works.models.DatabaseHelper;
import blank.djaja_works.models.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class topup extends AppCompatActivity {
    private TextView isiNama;
    private TextView isiNominal;
    private Button batal;
    private Button topup;
    private DatabaseHelper db;
    private SessionManager session;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        db = new DatabaseHelper(this);

        session = new SessionManager(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        String nama = "Rp "+session.getNama();
        isiNama = findViewById(R.id.uName);
        isiNama.setText(nama);

        isiNominal = findViewById(R.id.coin);
        String nom = String.valueOf(session.getSaldo());
        isiNominal.setText(nom);
        //isiNominal = findViewById(R.id.nom);
        //db.getA

    }

    public void cekCoin(String kode){
        showDialog();
        String sessEmail = session.getEmail();

    }

    public void konfirmasi(final String kode, final String nama, final int nom){
        AlertDialog.Builder alertConfirmasi = new AlertDialog.Builder(topup.this);
        alertConfirmasi.setTitle("Konfirmasi");
        alertConfirmasi.setMessage("Kode Cocok, Akun : "+nama+" Nominal : "+nom+" .Lanjutkan Pengisian?");
        alertConfirmasi.setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alertDialog = alertConfirmasi.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something here
                alertDialog.hide();
                int val = 1;
                String nam = nama;
                int banding = session.getSaldo();
                int nominal = nom + banding;
                //pinjamkan(iD, val, price);
            }
        });
    }

    private Akun accountsList;
    public void updateAkun(int Hbaru){
        showDialog();
        pDialog.setMessage("Memproses Pengisian");

        String email = session.getEmail();
        String nama = session.getNama();
        String usia = session.getUsia();
        int uia = Integer.valueOf(usia);
        String pss = session.getPss();
        String nPend = session.getNPend();
        String nRek = session.getNrek();
        String jk = session.getJK();
        int uang = Hbaru;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Akun> call = apiService.putAkun(email,pss,nama,uia,jk,nPend,nRek,uang);
        call.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                hideDialog();
                accountsList = response.body();
                Log.e("TAG", "response 33: "+new Gson().toJson(response.body()) );
                session.reWriteAkun(accountsList.getEmail(),accountsList.getNama_lengkap(),
                        accountsList.getNom(),accountsList.getUmur(),
                        accountsList.getJk(),accountsList.getNoKtp(),
                        accountsList.getNoRek(),accountsList.getPassword());
                Toast.makeText(getBaseContext(),"Transaksi Sukses",Toast.LENGTH_LONG);
                finish();
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                hideDialog();
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getBaseContext(), "connection error", Toast.LENGTH_LONG).show();
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

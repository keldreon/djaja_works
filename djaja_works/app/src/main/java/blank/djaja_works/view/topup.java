package blank.djaja_works.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import blank.djaja_works.API.ApiClient;
import blank.djaja_works.API.ApiInterface;
import blank.djaja_works.R;
import blank.djaja_works.models.Akun;
import blank.djaja_works.models.DatabaseHelper;
import blank.djaja_works.models.Kondisi;
import blank.djaja_works.models.SessionManager;
import blank.djaja_works.models.Topup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class topup extends AppCompatActivity {
    private TextView isiNama;
    private TextView isiNominal;
    private Button batal;
    private Button btnTopup;
    private DatabaseHelper db;
    private SessionManager session;
    private ProgressDialog pDialog;
    private List<Topup> tp;
    private String kode;
    private EditText kodeIsi;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        db = new DatabaseHelper(this);

        session = new SessionManager(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        String nama = session.getNama();
        isiNama = findViewById(R.id.uName);
        isiNama.setText(nama);

        isiNominal = findViewById(R.id.coin);
        String nom = "Rp "+String.valueOf(session.getSaldo());
        isiNominal.setText(nom);
        //isiNominal = findViewById(R.id.nom);
        //db.getA
        kodeIsi = findViewById(R.id.kode_topup);

        btnTopup = findViewById(R.id.btnTopup);
        btnTopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kode = kodeIsi.getText().toString().trim();
                if(kode.isEmpty()){
                    Toast.makeText(context,"Kode belum diisi!",Toast.LENGTH_LONG).show();
                }else{
                    cekCoin(kode);
                }
            }
        });

        batal = findViewById(R.id.btnBatal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void cekCoin(String kode){
        showDialog();
        pDialog.setMessage("Mengecek Kode...");
        String sessEmail = session.getEmail();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Topup>> call = apiService.getTopupData(sessEmail,kode);
        call.enqueue(new Callback<List<Topup>>() {
            @Override
            public void onResponse(Call<List<Topup>> call, Response<List<Topup>> response) {
                Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));
                    tp = response.body();
                    hideDialog();
                    String email = session.getEmail();
                    int val = tp.get(0).getNom();
                    String kode = tp.get(0).getKode().trim();
                    konfirmasi(kode, email, val);
            }

            @Override
            public void onFailure(Call<List<Topup>> call, Throwable t) {
                hideDialog();
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getBaseContext(), "connection error", Toast.LENGTH_LONG).show();
            }
        });
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
                lockTopup(kode,banding,nom);
            }
        });
    }

    public Kondisi kons;
    public void lockTopup(final String kode, final int lama, final int dua){
        showDialog();
        pDialog.setMessage("Memproses Pengisian");
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Kondisi> call = apiService.putTopupData(kode);
        call.enqueue(new Callback<Kondisi>() {
            @Override
            public void onResponse(Call<Kondisi> call, Response<Kondisi> response) {
                kons = response.body();
                String st = kons.getStatus();
                if(st.equals("Success")){
                    Toast.makeText(getBaseContext(), "Topup Berhasil", Toast.LENGTH_LONG).show();
                    int nominal = lama + dua;
                    updateAkun(nominal);
                }else{
                    hideDialog();
                    Toast.makeText(getBaseContext(), "Transaksi Gagal", Toast.LENGTH_LONG).show();
                    session.setSaldo(lama);
                }
            }

            @Override
            public void onFailure(Call<Kondisi> call, Throwable t) {
                hideDialog();
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getBaseContext(), "connection error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private Akun accountsList;
    public void updateAkun(int Hbaru){
        String email = session.getEmail();
        String nama = session.getNama();
        String usia = session.getUsia();
        String pss = session.getPss();
        String nPend = session.getNPend();
        String nRek = session.getNrek();
        String jk = session.getJK();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Akun> call = apiService.putAkun(email,pss,nama,usia,jk,nPend,nRek,Hbaru);
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
                Toast.makeText(getBaseContext(),"Transaksi Selesai",Toast.LENGTH_LONG).show();
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

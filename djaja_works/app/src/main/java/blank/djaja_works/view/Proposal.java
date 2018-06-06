package blank.djaja_works.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import blank.djaja_works.API.ApiClient;
import blank.djaja_works.API.ApiInterface;
import blank.djaja_works.R;
import blank.djaja_works.models.Akun;
import blank.djaja_works.models.Investment;
import blank.djaja_works.models.Kondisi;
import blank.djaja_works.models.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class Proposal extends AppCompatActivity {
    public Toolbar toolbar;
    private Intent tent;
    private Bundle bundle;
    private int id_propose;
    private Investment inv;
    TextView tv_nmaUsaha;
    TextView tv_nmaPUsaha;
    TextView tv_Nominal;
    TextView tv_durasi;
    TextView tv_deskripsi;
    private Button pinjam;
    private Context c;
    private int nom;
    private int price;
    private int iD;
    private ProgressDialog pDialog;
    private SessionManager sm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);

        LinearLayout ln = findViewById(R.id.id_ln);
        ln.setClipToOutline(true);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        sm = new SessionManager(this);

        tv_nmaUsaha = findViewById(R.id.nama_usaha);
        tv_Nominal = findViewById(R.id.tot_pinjaman);
        tv_durasi = findViewById(R.id.durasi);
        tv_deskripsi = findViewById(R.id.deskUsaha);
        tv_nmaPUsaha = findViewById(R.id.nama_pengusaha);
        pinjam = findViewById(R.id.btnTunjang);

        toolbar = (Toolbar) findViewById(R.id.toolbar_proposal);
        setSupportActionBar(toolbar);

        tent = getIntent();
        id_propose = tent.getExtras().getInt("data_calon_ID");
        //Toast.makeText(this, "Id = "+id_propose, Toast.LENGTH_LONG).show();
        //id_propose = tent.getExtras().getInt("id_data");

        /*tv_deskripsi.setText(deski);
        tv_durasi.setText(dur);
        tv_nmaPUsaha.setText(namaP);
        tv_nmaUsaha.setText(nU);
        tv_Nominal.setText(baru);*/
        //id_propose = tent.getIntExtra("send_id",0);

        getData(id_propose);

        pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konfirmasi(iD, price);
            }
        });
    }

    public void batal(){
        setResult(RESULT_CANCELED, tent);
        finish();
    }

    private List<Investment> apiInvest;
    public void getData(int kode){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Investment>> call = apiService.getInvestList(kode);
        call.enqueue(new Callback<List<Investment>>() {
            @Override
            public void onResponse(Call<List<Investment>> call, Response<List<Investment>> response) {
                apiInvest = response.body();
                tv_deskripsi.setText(apiInvest.get(0).getDeskripsi());
                tv_nmaPUsaha.setText(apiInvest.get(0).getNama_lengkap());
                tv_durasi.setText(apiInvest.get(0).getgBulanan());
                tv_nmaUsaha.setText(apiInvest.get(0).getNamaUsaha());
                tv_Nominal.setText(String.valueOf(apiInvest.get(0).getNominal()));
                iD = apiInvest.get(0).getId();
                price = apiInvest.get(0).getNominal();
                Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<List<Investment>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(c, "connection error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void konfirmasi(final int iD, final int price){
        AlertDialog.Builder alertConfirmasi = new AlertDialog.Builder(Proposal.this);
        alertConfirmasi.setTitle("Konfirmasi");
        alertConfirmasi.setMessage("Apakah anda yakin mau memberikan modal?");
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
                String val = "1";
                pinjamkan(iD, val, price);
            }
        });
    }

    private Kondisi st;
    public void pinjamkan(int id, String val, final int price){
        showDialog();
        pDialog.setMessage("Sedang Memroses...");
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Kondisi> call = apiService.putPiutang(id,val);
        call.enqueue(new Callback<Kondisi>() {
            @Override
            public void onResponse(Call<Kondisi> call, Response<Kondisi> response) {
                st = response.body();
                Log.e("TAG", "response 33: "+new Gson().toJson(response.body()) );
                String stat = st.getStatus();
                if(stat.equals("Success")){
                    //do_more
                    int aw = sm.getSaldo();
                    if(aw > price){
                        int baru = aw - price;
                        //Toast.makeText(c, "Sukses", Toast.LENGTH_LONG).show();
                        updateAkun(baru);
                    }else{
                        hideDialog();
                        Toast.makeText(c, "Coin tidak cukup. Silakan Toopup.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Kondisi> call, Throwable t) {
                hideDialog();
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(c, "connection error", Toast.LENGTH_LONG).show();
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

    private Akun accountsList;
    public void updateAkun(int Hbaru){
        String email = sm.getEmail();
        String nama = sm.getNama();
        String usia = sm.getUsia();
        int uia = Integer.valueOf(usia);
        String pss = sm.getPss();
        String nPend = sm.getNPend();
        String nRek = sm.getNrek();
        String jk = sm.getJK();
        int uang = Hbaru;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Akun> call = apiService.putAkun(email,pss,nama,uia,jk,nPend,nRek,uang);
        call.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                hideDialog();
                accountsList = response.body();
                Log.e("TAG", "response 33: "+new Gson().toJson(response.body()) );
                sm.reWriteAkun(accountsList.getEmail(),accountsList.getNama_lengkap(),
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
}
